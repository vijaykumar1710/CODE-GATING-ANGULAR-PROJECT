/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.service;

public class CloneResponse {
  private String status;
  private String sourcePath;
  public CloneResponse(String status, String sourcePath) {
    super();
    this.status = status;
    this.sourcePath = sourcePath;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getSourcePath() {
    return sourcePath;
  }
  public void setSourcePath(String sourcePath) {
    this.sourcePath = sourcePath;
  }
  @Override
  public String toString() {
    return "CloneResponse [status=" + status + ", sourcePath=" + sourcePath + "]";
  }

}
