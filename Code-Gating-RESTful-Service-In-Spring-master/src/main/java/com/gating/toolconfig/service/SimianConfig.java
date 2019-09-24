package com.gating.toolconfig.service;

public class SimianConfig {

  public static final String SIMIAN_JAR_PATH = "static-code-analyzers/simian/bin/simian-2.5.10.jar";

  private int duplicateLinesThreshold;




  public SimianConfig() {
    super();
    this.duplicateLinesThreshold = 3;
  }

  public int getDuplicateLinesThreshold() {
    return duplicateLinesThreshold;
  }

  public void setDuplicateLinesThreshold(int duplicateLinesThreshold) {
    this.duplicateLinesThreshold = duplicateLinesThreshold;
  }

}
