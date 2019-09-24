/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.toolconfig.service;

public class ToolResponse<T> {

  private String srcProject;
  private T value;
  private T threshold;
  private String finalResult;


  public ToolResponse(String srcProject, T value, T threshold, String finalResult) {
    super();
    this.srcProject = srcProject;
    this.value = value;
    this.threshold = threshold;
    this.finalResult = finalResult;
  }
  @Override
  public String toString() {
    return "ToolResponse [value=" + value + ", threshold=" + threshold + ", finalResult="
        + finalResult + "]";
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public T getThreshold() {
    return threshold;
  }

  public void setThreshold(T threshold) {
    this.threshold = threshold;
  }

  public String getFinalResult() {
    return finalResult;
  }

  public void setFinalResult(String finalResult) {
    this.finalResult = finalResult;
  }

  public String getSrcProject() {
    return srcProject;
  }

  public void setSrcProject(String srcProject) {
    this.srcProject = srcProject;
  }


}
