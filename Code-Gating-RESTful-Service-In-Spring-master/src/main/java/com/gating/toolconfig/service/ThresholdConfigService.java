/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.toolconfig.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.springframework.stereotype.Service;
import com.gating.utility.InternalServiceException;
import com.gating.utility.InvalidInputException;

@Service
public class ThresholdConfigService {

  public ThresholdConfig getThresholds() {

    final File propFile = new File("src/main/resources/threshold.config.properties");

    try {
      if (propFile.exists()) {
        try (FileInputStream fileInput = new FileInputStream(propFile)) {

          final Properties prop = new Properties();
          prop.load(fileInput);
          final ThresholdConfig thresholdConfig = new ThresholdConfig();

          thresholdConfig
          .setCyclomaticComplexity(Integer.valueOf(prop.getProperty("cyclomaticComplexity")));
          thresholdConfig.setCodeCoverage(Float.valueOf(prop.getProperty("codeCoverage")));
          thresholdConfig.setTimeToRunTests(Float.valueOf(prop.getProperty("timeToRunTests")));
          thresholdConfig.setNoOfWarnings(Integer.valueOf(prop.getProperty("noOfWarnings")));
          thresholdConfig
          .setSecurityIssuesCount(Integer.valueOf(prop.getProperty("securityIssuesCount")));

          return thresholdConfig;
        }

      } else {
        throw new InvalidInputException("Server Error : threshold config properties file not found",
            null);
      }
    }

    catch (final IOException e) {
      throw new InternalServiceException("Error occured while reading thresholds", e);
    }

  }



  public void setThresholds(ThresholdConfig newThresholds) {


    final Properties properties = new Properties();
    properties.setProperty("cyclomaticComplexity",
        String.valueOf(newThresholds.getCyclomaticComplexity()));
    properties.setProperty("codeCoverage", String.valueOf(newThresholds.getCodeCoverage()));
    properties.setProperty("timeToRunTests", String.valueOf(newThresholds.getTimeToRunTests()));
    properties.setProperty("noOfWarnings", String.valueOf(newThresholds.getNoOfWarnings()));
    properties.setProperty("securityIssuesCount",
        String.valueOf(newThresholds.getSecurityIssuesCount()));


    try (FileOutputStream fileOut =
        new FileOutputStream(new File("src/main/resources/threshold.config.properties"))) {
      properties.store(fileOut, null);
    } catch (final IOException e) {
      throw new InternalServiceException("Error occurred while saving thresholds ", e);
    }
  }

}
