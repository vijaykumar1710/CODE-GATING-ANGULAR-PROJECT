/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.service;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;
import com.gating.Application;
import com.gating.staticanalysis.service.CyvisService;
import com.gating.staticanalysis.service.JacocoService;
import com.gating.staticanalysis.service.PMDService;
import com.gating.staticanalysis.service.SimianService;
import com.gating.staticanalysis.service.VCGService;
import com.gating.toolconfig.service.ThresholdConfigService;
import com.gating.utility.InternalServiceException;
import com.gating.utility.InvalidInputException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class GatingServiceTest {

  @Autowired
  GatingService gatingService;

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



  @Test(expected = InternalServiceException.class)
  public void testJacocoDoesNotRunWhenProjectDoesNotContainTestCases() throws IOException,
  InterruptedException, InvalidInputException, SAXException, ParserConfigurationException {
    final String srcPath = "C:\\Users\\320052131\\Videos\\junitmavenexample withouttestcases";
    gatingService.gateCode(srcPath, false);
  }


  @Test
  public void testGateCode(){

    final String SourceCodePath = "C:\\Users\\320052131\\Videos\\junitmavenexample";
    final QualityParameters actual = gatingService.gateCode(SourceCodePath,false);
    assert(actual.getCodeDuplication() == 0);
  }


}
