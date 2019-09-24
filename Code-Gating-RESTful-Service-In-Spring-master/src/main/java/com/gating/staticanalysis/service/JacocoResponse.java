/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.staticanalysis.service;

public class JacocoResponse {

  private final double timeToRunTest;
  private double timeThreshold;
  private float codeCoverage;
  private float threshold;
  private String finalResult;

  public float getThreshold() {
    return threshold;
  }
  public float getCodeCoverage() {
    return codeCoverage;
  }
  public void setThreshold(float threshold) {
    this.threshold = threshold;
  }
  public void setCodeCoverage(float codeCoverage) {
    this.codeCoverage = codeCoverage;
  }

  public double getTimeToRunTest() {
    return timeToRunTest;
  }

  public String getFinalResult() {
    return finalResult;
  }

  public JacocoResponse(double timeToRunTest, double timeThreshold, float codeCoverage,
      float threshold, String finalResult) {
    super();
    this.timeToRunTest = timeToRunTest;
    this.timeThreshold = timeThreshold;
    this.codeCoverage = codeCoverage;
    this.threshold = threshold;
    this.finalResult = finalResult;
  }

  public void setFinalResult(String finalResult) {
    this.finalResult = finalResult;
  }

  public double getTimeThreshold() {
    return timeThreshold;
  }

  public void setTimeThreshold(double timeThreshold) {
    this.timeThreshold = timeThreshold;
  }

}
