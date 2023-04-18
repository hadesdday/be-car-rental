package com.carrental.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface IHttpHeaderReader {
    public String getTokenFromHeader(HttpServletRequest request);

}
