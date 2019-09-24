/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.staticanalysis.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.gating.service.ProcessUtility;
import com.gating.toolconfig.service.ThresholdConfigService;
import com.gating.toolconfig.service.ToolResponse;
import com.gating.toolconfig.service.VCGConfig;
import com.gating.utility.DocBuilderFactoryWithSecurity;
import com.gating.utility.InternalServiceException;
import com.gating.utility.Utility;

@Service
public class VCGService {
  public static final String VCG_REPORT_PATH =
      System.getProperty("user.dir") + "//reports//vcg_report.xml";

  @Autowired
  ProcessUtility processUtility;

  @Autowired
  ThresholdConfigService thresholdConfigService;

  public List<String> getCommand(String srcPath) {
    final StringJoiner vcgCommand = new StringJoiner(" ");
    vcgCommand.add("Visualcodegrepper.exe");
    vcgCommand.add("-c");
    vcgCommand.add("-l");
    vcgCommand.add("Java");
    vcgCommand.add("-t");
    vcgCommand.add(srcPath);
    vcgCommand.add("-x");
    vcgCommand.add(VCG_REPORT_PATH);

    final List<String> command = new ArrayList<>();
    command.add("cmd");
    command.add("/c");
    command.add(vcgCommand.toString());
    return command;
  }

  public int getIssuesCountFromXML(String vcgReportPath) {

    if (!Pattern.matches(".*\\.xml", vcgReportPath)) {
      throw new InternalServiceException("VCG could not find xml report :", null);
    }


    final DocumentBuilderFactory factory = DocBuilderFactoryWithSecurity.docBuilder();
    DocumentBuilder builder = null;
    Document doc = null;

    try {
      builder = factory.newDocumentBuilder();
      doc = builder.parse(vcgReportPath);
    } catch (final ParserConfigurationException | SAXException | IOException e) {
      throw new InternalServiceException("Error occured while parsing vcg report", e);
    }

    int securityIssuesCount = 0;
    if (doc != null && doc.getElementsByTagName("CodeIssueCollection") != null) {
      final NodeList issueCollection = doc.getElementsByTagName("CodeIssueCollection");
      for (int i = 0; i < issueCollection.getLength(); i++) {
        final Node p = issueCollection.item(i);
        if (p.getNodeType() == Node.ELEMENT_NODE) {
          final Element file = (Element) p;
          final NodeList codeIssueList = file.getChildNodes();
          securityIssuesCount += codeIssueList.getLength();
        }
      }
    }

    return securityIssuesCount;
  }

  public ToolResponse<Integer> run(String srcPath) {

    String finalDecision;

    processUtility.runProcess(getCommand(srcPath), VCGConfig.VCG_BIN_PATH);
    final int securityIssues = getIssuesCountFromXML(VCG_REPORT_PATH);
    final int threshold = thresholdConfigService.getThresholds().getSecurityIssuesCount();

    if (Utility.isLessThan(securityIssues, threshold)) {
      finalDecision = "Go";
    } else {
      finalDecision = "No Go";
    }

    return new ToolResponse<>(srcPath, securityIssues, threshold, finalDecision);
  }

}
