package com.gating.toolconfig.service;

public class PMDConfig {

  public static final String PMD_REPORT_PATH = "reports/pmd_report.xml";
  public static final String PMD_BIN_PATH = "static-code-analyzers/pmd/bin;";
  public static final String OUTPUT_FORMAT = "xml";

  private String ruleSet;

  public PMDConfig() {
    super();
    this.ruleSet = "rulesets/java/quickstart.xml";
  }

  public void setRuleSet(String resultSet) {
    this.ruleSet = resultSet;
  }

  public String getRuleSet() {
    return ruleSet;
  }

}
