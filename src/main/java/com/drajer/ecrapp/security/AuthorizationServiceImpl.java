package com.drajer.ecrapp.security;

import com.drajer.sof.model.LaunchDetails;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthorizationServiceImpl implements AuthorizationService {

  private final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

  public String getAuthorizationHeader(LaunchDetails launchDetails) {
    String access_token = "";
    RestTemplate restTemplate = new RestTemplate();
    JSONObject requestBody = new JSONObject();
    requestBody.put("client_id", launchDetails.getClientId());
    requestBody.put("client_secret", launchDetails.getClientSecret());
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<String> request = new HttpEntity<String>(requestBody.toString(), headers);

    logger.info(
        "Sending Authorization request to Endpoint::::: {}",
        launchDetails.getEhrAuthorizationUrl());
    ResponseEntity<String> response =
        restTemplate.exchange(
            launchDetails.getEhrAuthorizationUrl(), HttpMethod.POST, request, String.class);

    JSONObject responseObj = new JSONObject(response.getBody());
    access_token = responseObj.getString("access_token");
    logger.info("received Access_token====>" + access_token);
    return access_token;
  }
}
