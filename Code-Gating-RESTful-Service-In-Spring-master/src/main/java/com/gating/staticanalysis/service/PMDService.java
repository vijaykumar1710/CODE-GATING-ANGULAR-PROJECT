/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.staticanalysis.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.gating.service.ProcessUtility;
import com.gating.toolconfig.service.PMDConfig;
import com.gating.toolconfig.service.PMDConfigService;
import com.gating.toolconfig.service.ThresholdConfigService;
import com.gating.toolconfig.service.ToolResponse;
import com.gating.utility.DocBuilderFactoryWithSecurity;
import com.gating.utility.InternalServiceException;
import com.gating.utility.InvalidInputException;
import com.gating.utility.Utility;

@Service
public class PMDService {

  @Autowired
  ProcessUtility processUtility;

  @Autowired
  ThresholdConfigService thresholdConfService;

  @Autowired
  PMDConfigService pmdConfigService;

  static final Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  public List<String> getCommand(String srcPath, PMDConfig params, String outputFormat,
      String pmdReportPath) {

    if (!Pattern.matches(".*\\.xml", params.getRuleSet())) {
      throw new InvalidInputException("ruleset required by pmd must be an xml file, input given ",
          params.getRuleSet());
    }

    final StringJoiner pmdCommand = new StringJoiner(" ");
    pmdCommand.add("pmd -d");
    pmdCommand.add(srcPath);
    pmdCommand.add("-f");
    pmdCommand.add(outputFormat);
    pmdCommand.add("-R");
    pmdCommand.add(params.getRuleSet());
    pmdCommand.add(">");
    pmdCommand.add(pmdReportPath);

    final List<String> command = new ArrayList<>();
    command.add("cmd");
    command.add("/c");
    command.add(pmdCommand.toString());
    return command;
  }

  public int parsePMDXMLReport(String pmdreportpath) {


    final DocumentBuilderFactory factory = DocBuilderFactoryWithSecurity.docBuilder();
    DocumentBuilder builder = null;
    Document doc = null;


    try {
      builder = factory.newDocumentBuilder();
      if (builder != null) {
        doc = builder.parse(pmdreportpath);
      }
      if (doc != null && doc.getElementsByTagName("violation") != null) {
        final NodeList violationList = doc.getElementsByTagName("violation");
        return violationList.getLength();
      }
    }

    catch (final ParserConfigurationException | SAXException e) {
      throw new InternalServiceException("PMD parser could not parse report", e);
    } catch (final IOException e) {
      throw new InternalServiceException("PMD could not read report ", e);
    }

    return -1;
  }


  public ToolResponse<Integer> run(String srcPath) {
    final PMDConfig params = pmdConfigService.getConfig();
    List<String> command;
    String decision;

    command = getCommand(srcPath, params, PMDConfig.OUTPUT_FORMAT, PMDConfig.PMD_REPORT_PATH);
    processUtility.runProcess(command, PMDConfig.PMD_BIN_PATH);

    final int warnings = parsePMDXMLReport(PMDConfig.PMD_REPORT_PATH);
    final int warningsThreshold = thresholdConfService.getThresholds().getNoOfWarnings();

    if (Utility.isLessThan(warnings, warningsThreshold)) {
      decision = "Go";
    } else {
      decision = "No Go";
    }

    return new ToolResponse<>(srcPath, warnings, warningsThreshold, decision);
  }

}
