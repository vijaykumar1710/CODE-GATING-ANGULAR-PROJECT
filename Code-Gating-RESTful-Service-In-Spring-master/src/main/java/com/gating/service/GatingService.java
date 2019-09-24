package com.gating.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gating.staticanalysis.service.CyvisService;
import com.gating.staticanalysis.service.JacocoResponse;
import com.gating.staticanalysis.service.JacocoService;
import com.gating.staticanalysis.service.PMDService;
import com.gating.staticanalysis.service.SimianService;
import com.gating.staticanalysis.service.VCGService;
import com.gating.toolconfig.service.ThresholdConfig;
import com.gating.toolconfig.service.ThresholdConfigService;
import com.gating.utility.InternalServiceException;
import com.gating.utility.InvalidInputException;
import com.gating.utility.Utility;

@Service
public class GatingService {

  @Autowired
  PMDService pmdService;

  @Autowired
  SimianService simianService;

  @Autowired
  VCGService vcgService;

  @Autowired
  CyvisService cyvisService;

  @Autowired
  JacocoService jacocoService;

  @Autowired
  ThresholdConfigService thresholdService;


  private void determineCodeQuality(QualityParameters response,
      Boolean usePreviousResultsAsThreshold) {

    ThresholdConfig thresholds;


    if (usePreviousResultsAsThreshold) {
      thresholds = getLastRunResults();
    } else {
      thresholds = thresholdService.getThresholds();
    }

    final List<Boolean> allResults = new ArrayList<>();

    allResults.add(Utility.isLessThan(response.getNoOfWarnings(), thresholds.getNoOfWarnings()));
    allResults.add(response.getCodeDuplication() == 0);
    allResults.add(
        Utility.isLessThan(response.getSecurityIssuesCount(), thresholds.getSecurityIssuesCount()));
    allResults.add(Utility.isLessThan(response.getCyclomaticComplexity(),
        thresholds.getCyclomaticComplexity()));
    allResults.add(Utility.isGreaterThan(response.getCodeCoverage(), thresholds.getCodeCoverage()));
    allResults
    .add(Utility.isLessThan(response.getTimeToRunTests(), thresholds.getTimeToRunTests()));

    for (final Boolean result : allResults) {
      if (!result) {
        response.setFinalDecision("No Go");
        return;
      }
    }

    response.setFinalDecision("Go");
  }


  private static void saveResults(QualityParameters response, String resultsLogPath){

    try (BufferedWriter csvWriter =
        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultsLogPath, true)))) {

      final String CSV_SEPARATOR = ",";
      final StringBuilder responseLine = new StringBuilder();

      responseLine.append(response.getTimeToRunTests());
      responseLine.append(CSV_SEPARATOR);
      responseLine.append(response.getNoOfWarnings());
      responseLine.append(CSV_SEPARATOR);
      responseLine.append(response.getCodeCoverage());
      responseLine.append(CSV_SEPARATOR);
      responseLine.append(response.getCyclomaticComplexity());
      responseLine.append(CSV_SEPARATOR);
      responseLine.append(response.getSecurityIssuesCount());
      responseLine.append(CSV_SEPARATOR);
      responseLine.append(response.getCodeDuplication());
      responseLine.append(CSV_SEPARATOR);
      responseLine.append(response.getFinalDecision());

      csvWriter.newLine();
      csvWriter.write(responseLine.toString());
    }
    catch(final IOException e) {
      throw new InternalServiceException("Error occurred while saving  current run results", e);
    }
  }


  private static ThresholdConfig getLastRunResults() {

    String currentRow;
    String lastRow = null;

    try (BufferedReader reader =
        new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\resultsLog.csv"))) {

      currentRow = reader.readLine();

      if ((currentRow = reader.readLine()) == null) {
        throw new InvalidInputException("No previous results found", null);
      }

      while ((currentRow = reader.readLine()) != null) {
        lastRow = currentRow;
      }
    } catch (final IOException e) {
      throw new InternalServiceException("Error occured retrieving previous run results", e);
    }

    final ThresholdConfig lastResult = new ThresholdConfig();
    if (lastRow != null)

    {
      final String[] lastRowArray = lastRow.split(",");
      lastResult.setTimeToRunTests(Float.valueOf(lastRowArray[0]));
      lastResult.setNoOfWarnings(Integer.valueOf(lastRowArray[1]));
      lastResult.setCodeCoverage(Float.valueOf(lastRowArray[2]));
      lastResult.setCyclomaticComplexity(Integer.valueOf(lastRowArray[3]));
      lastResult.setSecurityIssuesCount(Integer.valueOf(lastRowArray[4]));
      lastResult.setCodeDuplication(Integer.valueOf(lastRowArray[5]));
    }
    return lastResult;
  }


  public QualityParameters gateCode(String srcPath, Boolean usePreviousResultsAsThreshold) {

    jacocoService.buildProject(srcPath);

    final QualityParameters response = new QualityParameters();
    response.setProjectPath(srcPath);
    response.setNoOfWarnings(pmdService.run(srcPath).getValue());
    response.setCodeDuplication(simianService.run(srcPath).getValue());
    response.setSecurityIssuesCount(vcgService.run(srcPath).getValue());
    response.setCyclomaticComplexity(cyvisService.run(srcPath).getValue());

    if (!(new File(srcPath + "/target/test-classes")).exists()) {
      throw new InternalServiceException(
          "Cannot run jacoco since project does not contain testcase classes,", null);
    } else {

      final JacocoResponse res = jacocoService.run(srcPath);
      response.setCodeCoverage(res.getCodeCoverage());
      response.setTimeToRunTests(res.getTimeToRunTest());
    }

    determineCodeQuality(response, usePreviousResultsAsThreshold);
    saveResults(response, "resultsLog.csv");
    return response;
  }
}
