package com.carrental.controller;

import com.carrental.dto.JWTDTO;
import com.carrental.dto.UserDTO;
import com.carrental.entity.JWTEntity;
import com.carrental.entity.UserEntity;
import com.carrental.enums.UserStatus;
import com.carrental.requestmodel.LoginRequest;
import com.carrental.requestmodel.SignUpFormRequest;
import com.carrental.responsemodel.APIResponse;
import com.carrental.responsemodel.AuthenticationResponse;
import com.carrental.service.CustomUserDetailsService;
import com.carrental.service.IHttpHeaderReader;
import com.carrental.service.IJWTService;
import com.carrental.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomUserDetailsService customUserDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IJWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IHttpHeaderReader httpHeaderReader;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/sign-in")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String usernameErrorMessage = bindingResult.getFieldError("username").getDefaultMessage();
            String passwordErrorMessage = bindingResult.getFieldError("password").getDefaultMessage();
            if (usernameErrorMessage != null || passwordErrorMessage != null) {
                APIResponse<String> response = new APIResponse<>("Username hoặc password không được để trống", HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.ok(response);
            }
        }
        UserDetails userDetails;
        userDetails = this.customUserDetailService.loadUserByUsername(loginRequest.getUsername());
        if (userDetails != null && this.userService.checkPassword(userDetails, loginRequest.getPassword())) {
            // create JWT
            String accessToken = this.jwtService.generateToken(userDetails, "access");
            Date expiredAccessToken = this.jwtService.getExpirationDateFromToken(accessToken);
            String refreshToken = this.jwtService.generateToken(userDetails, "refresh");
            Date expiredRefreshToken = this.jwtService.getExpirationDateFromToken(refreshToken);
            JWTEntity accessJWTEntity = new JWTEntity(accessToken, expiredAccessToken);
            JWTEntity refreshJWTEntity = new JWTEntity(refreshToken, expiredRefreshToken);
            JWTDTO accessJWTDTO = jwtService.save(accessJWTEntity);
            JWTDTO refreshJWTDTO = jwtService.save(refreshJWTEntity);
            UserDTO userDTO = this.userService.findByUsernameDTO(userDetails.getUsername());
            AuthenticationResponse data = new AuthenticationResponse(userDTO, accessJWTDTO, refreshJWTDTO);
            APIResponse<AuthenticationResponse> response = new APIResponse<>(data, HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } else {
            APIResponse<String> response = new APIResponse<>(HttpStatus.BAD_REQUEST.getReasonPhrase(), "Sai username hoặc password", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(response);
        }

    }

    @PostMapping("/validate-sign-up")
    public ResponseEntity validSignUp(@RequestBody SignUpFormRequest signUpRequest) {
        boolean isValidPassword = this.userService.checkValidPassword(signUpRequest.getPassword());
        boolean isExistUser = this.userService.checkExistUser(signUpRequest.getUsername());
        if(isExistUser == true){
            UserEntity foundUser = this.userService.findByUsername(signUpRequest.getUsername());
            if(foundUser.getStatus().equals(UserStatus.UNACTIVATED)){
                foundUser.setPassword(signUpRequest.getPassword());
                foundUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
                foundUser.setPhone(signUpRequest.getPhone());
                foundUser.setFullName(signUpRequest.getFullName());
                foundUser.setEmail(signUpRequest.getUsername());
                this.userService.save(foundUser);
                APIResponse<String> response = new APIResponse<String>("Thông tin hợp lệ", HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value());
                return ResponseEntity.ok(response);
            }else{
                APIResponse<String> response = new APIResponse<String>("Tài khoản đã tồn tại", HttpStatus.CONFLICT.getReasonPhrase(), HttpStatus.CONFLICT.value());
                return ResponseEntity.ok(response);
            }
        }else{
            if(isValidPassword == true){
                APIResponse<String> response = new APIResponse<String>("Thông tin hợp lệ", HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value());
                UserEntity nonActiveUser = new UserEntity();
                nonActiveUser.setUsername(signUpRequest.getUsername());
                nonActiveUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
                nonActiveUser.setPhone(signUpRequest.getPhone());
                nonActiveUser.setFullName(signUpRequest.getFullName());
                nonActiveUser.setEmail(signUpRequest.getUsername());
                nonActiveUser.setStatus(UserStatus.UNACTIVATED);
                this.userService.save(nonActiveUser);
                return ResponseEntity.ok(response);
            }else{
                APIResponse<String> response = new APIResponse<String>("Mật khẩu không hợp lệ", HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.ok(response);
            }
        }
    }
    @GetMapping("/refresh-access-token")
    public ResponseEntity refreshAccessToken(HttpServletRequest request) {
        String refreshToken = this.httpHeaderReader.getTokenFromHeader(request);

        if(StringUtils.hasText(refreshToken) && this.jwtService.validateToken(refreshToken)){
            String username = this.jwtService.getUsernameFromToken(refreshToken);
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
            UserEntity userEntity = this.userService.findByUsername(username);
            String newAccessToken = this.jwtService.generateToken(userDetails, "access");
            String newRefreshToken = this.jwtService.generateToken(userDetails, "refresh");
            Date expiredNewAccessToken = this.jwtService.getExpirationDateFromToken(newAccessToken);
            Date expiredNewRefreshToken = this.jwtService.getExpirationDateFromToken(newRefreshToken);
            JWTEntity newAccessTokenEntity = new JWTEntity(newAccessToken, expiredNewAccessToken);
            JWTEntity newRefreshTokenEntity = new JWTEntity(newRefreshToken, expiredNewRefreshToken);
            JWTDTO newAccessTokenDTO = jwtService.save(newAccessTokenEntity);
            JWTDTO newRefreshTokenDTO = jwtService.save(newRefreshTokenEntity);
            UserDTO userDTO = this.userService.findByUsernameDTO(userDetails.getUsername());
            AuthenticationResponse data = new AuthenticationResponse(userDTO, newAccessTokenDTO, newRefreshTokenDTO);
            APIResponse<AuthenticationResponse> response = new APIResponse<>(data, HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        }else{
            APIResponse<String> response = new APIResponse<String>("Phiên đăng nhập hết hạn. Vui lòng đăng nhập lại", HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(response);
        }
    }
    @PostMapping(value = "/revoke-token")
    public ResponseEntity revokeToken(HttpServletRequest request, @RequestBody String refreshToken) {
        try{
            String accessToken = this.httpHeaderReader.getTokenFromHeader(request);
            Long removedAccess = this.jwtService.removeByToken(accessToken);
            Long removedRefresh = this.jwtService.removeByToken(refreshToken);
            if (removedAccess.longValue() != -1 && removedAccess.longValue() != -1) {
                APIResponse<String> response = new APIResponse<String>("Thu hồi thành công", HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value());
                return ResponseEntity.ok(response);
            }else{
                APIResponse<String> response = new APIResponse<String>("Thu hồi thất bại", HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.ok(response);
            }
        }catch(Exception e){
            APIResponse<String> response = new APIResponse<String>("Thu hồi thất bại", HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(response);
        }
    }
}
