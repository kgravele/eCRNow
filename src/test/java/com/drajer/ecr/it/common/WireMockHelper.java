package com.drajer.ecr.it.common;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.drajer.test.util.TestUtils;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.UrlMatchingStrategy;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WireMockHelper {
  private String baseUrl;
  private int wireMockPort;
  WireMockServer wireMockServer;

  private static final Logger logger = LoggerFactory.getLogger(WireMockHelper.class);

  public WireMockHelper(String baseUrl, int wireMockPort) {
    super();
    this.baseUrl = baseUrl;
    this.wireMockPort = wireMockPort;
    try {
      wireMockServer = new WireMockServer(this.wireMockPort);
      wireMockServer.start();
      configureFor("localhost", wireMockPort);
      logger.info("Initialized wiremock on http server: " + wireMockPort);
    } catch (Exception e) {
      logger.error("Error in initializing wiremock" + e);
    }
  }

  public void stopMockServer() {
    wireMockServer.stop();
  }

  public void stubResources(Map<String, ?> stubMapping) {

    Set<String> stubResources = stubMapping.keySet();

    for (String resource : stubResources) {

      stubMapping.get(resource);
      List<LinkedHashMap> stubVOs = (List<LinkedHashMap>) stubMapping.get(resource);

      for (LinkedHashMap map : stubVOs) {
        StubVO vo = new StubVO(map.get("params"), (String) map.get("responseFilePath"));
        callStubber(resource, vo);
      }
    }
  }

  private void callStubber(String resource, StubVO stubVO) {
    boolean isQueryParam = stubVO.getParams().getClass().getSimpleName().equals("LinkedHashMap");

    if (isQueryParam) {
      StringBuilder params = new StringBuilder("");
      Map<String, String> paramMap = (Map<String, String>) stubVO.getParams();
      Set<String> pramKeys = paramMap.keySet();
      int size = pramKeys.size();
      for (String key : pramKeys) {
        params.append(key).append("=").append(paramMap.get(key));
        size--;
        if (size > 0) params.append("&");
      }
      stubResource(resource, true, params.toString(), stubVO.getResponseFilePath());
    } else {
      stubResource(resource, false, (String) stubVO.getParams(), stubVO.getResponseFilePath());
    }
  }

  private void stubResource(
      String resourceName, boolean isQueryParam, String params, String responseFilePath) {
    String url = "";
    String param = "";

    if (isQueryParam) param = "?" + params;
    else param = "/" + params;

    url = baseUrl + "/" + resourceName + param;

    String responseStr = TestUtils.getFileContentAsString(responseFilePath);

    // Mapping for our resources
    logger.info("Creating wiremock stub for uri: " + url);
    stubFor(
        get(urlEqualTo(url))
            .atPriority(1)
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withBody(responseStr)
                    .withHeader("Content-Type", "application/fhir+json; charset=utf-8")));
  }

  public void stubAuthAndMetadata(Map<String, ?> otherMappings) {

    Set<String> mappingKey = otherMappings.keySet();
    for (String key : mappingKey) {
      String url = "";
      if (key.equalsIgnoreCase("default")) {
        UrlMatchingStrategy urlMatchingStrategy = new UrlMatchingStrategy();
        urlMatchingStrategy.setUrlPattern(baseUrl + "/.*");
        stubFor(
            any(urlMatchingStrategy)
                .atPriority(10)
                .willReturn(
                    aResponse()
                        .withStatus(404)
                        .withBody(TestUtils.getFileContentAsString((String) otherMappings.get(key)))
                        .withHeader("Content-Type", "application/fhir+json; charset=utf-8")));
        logger.info("Stub Created for default: " + "/FHIR/.*");
      } else if (key.equalsIgnoreCase("metadata")) {
        url = baseUrl + "/" + key;
        stubFor(
            get(urlEqualTo(url))
                .atPriority(1)
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(TestUtils.getFileContentAsString((String) otherMappings.get(key)))
                        .withHeader("Content-Type", "application/fhir+json; charset=utf-8")));
        logger.info("Stub Created for Metadata uri: " + url);
      } else if (key.equalsIgnoreCase("token")) {
        url = baseUrl + "/" + key;
        stubFor(
            post(urlEqualTo(url))
                .atPriority(1)
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(TestUtils.getFileContentAsString((String) otherMappings.get(key)))
                        .withHeader("Content-Type", "application/fhir+json; charset=utf-8")));
        logger.info("Stub Created for AsscessToken uri: " + url);
      }
    }
  }
}
