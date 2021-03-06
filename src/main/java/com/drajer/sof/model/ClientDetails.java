package com.drajer.sof.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import jline.internal.Log;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "client_details")
@DynamicUpdate
public class ClientDetails {

  @Transient private final Logger logger = LoggerFactory.getLogger(ClientDetails.class);

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "is_provider_launch", nullable = false)
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean isProvider;

  @Column(name = "is_system_launch", nullable = false)
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean isSystem;

  @Column(name = "clientId", nullable = false, columnDefinition = "TEXT")
  private String clientId;

  @Column(name = "clientSecret", nullable = true, columnDefinition = "TEXT")
  private String clientSecret;

  @Column(name = "fhir_server_base_url", nullable = false, unique = true)
  private String fhirServerBaseURL;

  @Column(name = "token_url", nullable = true, columnDefinition = "TEXT")
  private String tokenURL;

  @Column(name = "scopes", nullable = false, columnDefinition = "TEXT")
  private String scopes;

  @Column(name = "is_direct", nullable = false)
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean isDirect;

  @Column(name = "is_xdr", nullable = false)
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean isXdr;

  @Column(name = "is_restapi", nullable = false)
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean isRestAPI;

  @Column(name = "direct_host", nullable = true, columnDefinition = "TEXT")
  private String directHost;

  @Column(name = "direct_user", nullable = true)
  private String directUser;

  @Column(name = "direct_pwd", nullable = true)
  private String directPwd;

  @Column(name = "smtp_port", nullable = true)
  private String smtpPort;

  @Column(name = "imap_port", nullable = true)
  private String imapPort;

  @Column(name = "direct_recipient_address", nullable = true)
  private String directRecipientAddress;

  @Column(name = "xdr_recipient_address", nullable = true)
  private String xdrRecipientAddress;

  @Column(name = "rest_api_url", nullable = true)
  private String restAPIURL;

  @Column(name = "aa_id", nullable = true)
  private String assigningAuthorityId;

  @Column(name = "encounter_start_time", nullable = true)
  private String encounterStartThreshold;

  @Column(name = "encounter_end_time", nullable = true)
  private String encounterEndThreshold;

  @Column(name = "is_covid19", nullable = false)
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean isCovid;

  @Column(name = "is_full_ecr", nullable = false)
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean isFullEcr;

  @Column(name = "is_logging_enabled", nullable = false)
  @Type(type = "org.hibernate.type.NumericBooleanType")
  private Boolean isLoggingEnabled;

  @Column(name = "token_introspection_url", nullable = true)
  private String tokenIntrospectionURL;

  @Column(name = "ehr_client_id", nullable = true)
  private String ehrClientId;

  @Column(name = "ehr_client_secret", nullable = true)
  private String ehrClientSecret;

  @Column(name = "ehr_authorization_url", nullable = true)
  private String ehrAuthorizationUrl;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Boolean getIsProvider() {
    return isProvider;
  }

  public void setIsProvider(Boolean isProvider) {
    this.isProvider = isProvider;
  }

  public Boolean getIsSystem() {
    return isSystem;
  }

  public void setIsSystem(Boolean isSystem) {
    this.isSystem = isSystem;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getFhirServerBaseURL() {
    return fhirServerBaseURL;
  }

  public void setFhirServerBaseURL(String fhirServerBaseURL) {
    this.fhirServerBaseURL = fhirServerBaseURL;
  }

  public String getTokenURL() {
    return tokenURL;
  }

  public void setTokenURL(String tokenURL) {
    this.tokenURL = tokenURL;
  }

  public String getScopes() {
    return scopes;
  }

  public void setScopes(String scopes) {
    this.scopes = scopes;
  }

  public Boolean getIsDirect() {
    return isDirect;
  }

  public void setIsDirect(Boolean isDirect) {
    this.isDirect = isDirect;
  }

  public Boolean getIsXdr() {
    return isXdr;
  }

  public Boolean getIsRestAPI() {
    return isRestAPI;
  }

  public void setIsRestAPI(Boolean isRestAPI) {
    this.isRestAPI = isRestAPI;
  }

  public String getRestAPIURL() {
    return restAPIURL;
  }

  public void setRestAPIURL(String restAPIURL) {
    this.restAPIURL = restAPIURL;
  }

  public void setIsXdr(Boolean isXdr) {
    this.isXdr = isXdr;
  }

  public String getDirectHost() {
    return directHost;
  }

  public void setDirectHost(String directHost) {
    this.directHost = directHost;
  }

  public String getDirectUser() {
    return directUser;
  }

  public void setDirectUser(String directUser) {
    this.directUser = directUser;
  }

  public String getDirectPwd() {
    return directPwd;
  }

  public void setDirectPwd(String directPwd) {
    this.directPwd = directPwd;
  }

  public String getSmtpPort() {
    return smtpPort;
  }

  public void setSmtpPort(String smtpPort) {
    this.smtpPort = smtpPort;
  }

  public String getImapPort() {
    return imapPort;
  }

  public void setImapPort(String imapPort) {
    this.imapPort = imapPort;
  }

  public String getDirectRecipientAddress() {
    return directRecipientAddress;
  }

  public void setDirectRecipientAddress(String directRecipientAddress) {
    this.directRecipientAddress = directRecipientAddress;
  }

  public String getXdrRecipientAddress() {
    return xdrRecipientAddress;
  }

  public void setXdrRecipientAddress(String xdrRecipientAddress) {
    this.xdrRecipientAddress = xdrRecipientAddress;
  }

  public String getAssigningAuthorityId() {
    return assigningAuthorityId;
  }

  public void setAssigningAuthorityId(String assigningAuthorityId) {
    this.assigningAuthorityId = assigningAuthorityId;
  }

  public String getEncounterStartThreshold() {
    return encounterStartThreshold;
  }

  public void setEncounterStartThreshold(String encounterStartThreshold) {
    this.encounterStartThreshold = encounterStartThreshold;
  }

  public String getEncounterEndThreshold() {
    return encounterEndThreshold;
  }

  public void setEncounterEndThreshold(String encounterEndThreshold) {
    this.encounterEndThreshold = encounterEndThreshold;
  }

  public Boolean getIsCovid() {
    return isCovid;
  }

  public void setIsCovid(Boolean isCovid) {
    this.isCovid = isCovid;
  }

  public Boolean getIsFullEcr() {
    return isFullEcr;
  }

  public void setIsFullEcr(Boolean isFullEcr) {
    this.isFullEcr = isFullEcr;
  }

  public Boolean getIsLoggingEnabled() {
    return isLoggingEnabled;
  }

  public void setIsLoggingEnabled(Boolean isLoggingEnabled) {
    this.isLoggingEnabled = isLoggingEnabled;
  }

  public String getTokenIntrospectionURL() {
    return tokenIntrospectionURL;
  }

  public void setTokenIntrospectionURL(String tokenIntrospectionURL) {
    this.tokenIntrospectionURL = tokenIntrospectionURL;
  }

  public String getEhrClientId() {
    return ehrClientId;
  }

  public void setEhrClientId(String ehrClientId) {
    this.ehrClientId = ehrClientId;
  }

  public String getEhrClientSecret() {
    return ehrClientSecret;
  }

  public void setEhrClientSecret(String ehrClientSecret) {
    this.ehrClientSecret = ehrClientSecret;
  }

  public String getEhrAuthorizationUrl() {
    return ehrAuthorizationUrl;
  }

  public void setEhrAuthorizationUrl(String ehrAuthorizationUrl) {
    this.ehrAuthorizationUrl = ehrAuthorizationUrl;
  }

  public void print() {

    logger.info(" **** Printing Client Details **** ");

    Log.info(" Id = " + id);
    Log.info(" Provider Launch = " + isProvider);
    Log.info(" System Launch = " + isSystem);
    Log.info(" Client Id = " + clientId);
    Log.info(" Client Secret = " + clientSecret);
    Log.info(" FHIR Server URL = " + fhirServerBaseURL);
    Log.info(" Token URL = " + tokenURL);
    Log.info(" Scopes = " + scopes);
    Log.info(" Is Direct ? = " + isDirect);
    Log.info(" Is XDR = " + isXdr);
    Log.info(" Is Rest API = " + isRestAPI);
    Log.info(" Direct Host = " + directHost);
    Log.info(" Direct pwd = " + directPwd);
    Log.info(" Direct Recipient Address = " + directRecipientAddress);
    Log.info(" XDR Recipient Address = " + xdrRecipientAddress);
    Log.info(" Rest API URL = " + restAPIURL);
    Log.info(" Assigning Authority Id = " + assigningAuthorityId);
    Log.info(" Encounter Start Threshold = " + encounterStartThreshold);
    Log.info(" Encounter End Threshold = " + encounterEndThreshold);
    Log.info(" Is Covid = " + isCovid);
    Log.info(" Is Full ECR = " + isFullEcr);
    Log.info(" Is Logging Enabled " + isLoggingEnabled);

    logger.info(" **** End Printing Client Details **** ");
  }
}
