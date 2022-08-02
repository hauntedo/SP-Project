package com.technokratos.service;

import java.util.Map;

public interface EmailService {

    void send(Map<String, String> emailData, String subject);
    String generateTemplateUsingFreemarker(String firstName, String confirmCode);

}