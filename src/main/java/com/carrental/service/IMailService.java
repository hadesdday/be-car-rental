package com.carrental.service;

import javax.mail.MessagingException;

public interface IMailService {
    public void sendOTPMessage(String to, String subject, String message) throws MessagingException;
}
