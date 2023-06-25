package com.decskill.exerciciodeteste.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailService {

    @Value("${email.apikey}")
    private String smtp2goApiKey;

    private RestTemplate restTemplate;
    private String apiUrl = "https://api.smtp2go.com/v3/email/send";

    @Autowired
    public EmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmail(List<String> recipients, String subject, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Get API key from application properties

        // Format the request body to match the expected format of SMTP2GO's API
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("api_key", smtp2goApiKey);
        requestBody.put("to", recipients);
        requestBody.put("subject", subject);
        requestBody.put("html_body", body);
        requestBody.put("text_body", body);
        requestBody.put("sender", "Decskill <no-reply@gustavo.pro>");


        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, request, String.class);
            String responseBody = responseEntity.getBody();
            if (responseEntity.getStatusCode().isError()) {
                // Handle any error responses from SMTP2GO's API
                log.error("Failed to send email: " + responseEntity.getBody());
            } else if (responseEntity.getStatusCode().is2xxSuccessful() && StringUtils.hasLength(responseBody) && responseBody.contains("succeeded\": 1")) {
                log.debug(new StringBuilder().append("Success sent message to ").append(recipients).toString());
            } else {
                log.error("Failed to send email: " + responseEntity.getBody());
            }
        } catch (RestClientException e) {
            // Handle any exceptions that may have occurred while sending the email
            e.printStackTrace();
        }
    }

}
