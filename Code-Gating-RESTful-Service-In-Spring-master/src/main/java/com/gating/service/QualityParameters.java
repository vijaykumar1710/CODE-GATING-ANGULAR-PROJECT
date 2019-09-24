/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.service;

public class QualityParameters {

  private String projectPath;
  private double timeToRunTest;
  private int noOfWarnings;
  private float codeCoverage;
  private int cyclomaticComplexity;
  private int codeDuplication;
  private int securityIssuesCount;
  private String finalDecision;


  public QualityParameters() {
    super();
  }
  public double getTimeToRunTests() {
    return timeToRunTest;
  }

  public void setTimeToRunTests(double time) {
    this.timeToRunTest = time;
  }

  public int getNoOfWarnings() {
    return noOfWarnings;
  }

  public void setNoOfWarnings(int noOfWarnings) {
    this.noOfWarnings = noOfWarnings;
  }

  public float getCodeCoverage() {
    return codeCoverage;
  }

  public void setCodeCoverage(float codeCoverage) {
    this.codeCoverage = codeCoverage;
  }

  public int getCyclomaticComplexity() {
    return cyclomaticComplexity;
  }

  public void setCyclomaticComplexity(int cyclomaticComplexity) {
    this.cyclomaticComplexity = cyclomaticComplexity;
  }

  public int getCodeDuplication() {
    return codeDuplication;
  }

  public void setCodeDuplication(int integer) {
    this.codeDuplication = integer;
  }

  public int getSecurityIssuesCount() {
    return securityIssuesCount;
  }

  public void setSecurityIssuesCount(int securityIssuesCount) {
    this.securityIssuesCount = securityIssuesCount;
  }

  public String getFinalDecision() {
    return finalDecision;
  }

  public void setFinalDecision(String finalDecision) {
    this.finalDecision = finalDecision;
  }

  public String getProjectPath() {
    return projectPath;
  }

  public void setProjectPath(String projectPath) {
    this.projectPath = projectPath;
  }
}
