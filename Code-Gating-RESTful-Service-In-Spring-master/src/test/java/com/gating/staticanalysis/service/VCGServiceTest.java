/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.staticanalysis.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.gating.Application;
import com.gating.toolconfig.service.ToolResponse;
import com.gating.utility.InternalServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class VCGServiceTest {

  @Autowired
  VCGService vcgService;

  @Test(expected = InternalServiceException.class)
  public void testGetIssuesCountThrowsExceptionInputReportIsNotXML(){
    final int expected = 23;
    final int actual = vcgService.getIssuesCountFromXML(System.getProperty("user.dir") + "\\reports\\vcg_reports.txt");
    assertEquals(expected, actual);
  }

  @Test
  public void testRun(){
    final String SourceCodePath = "C:\\Users\\320052131\\Videos\\junitmavenexample";
    final ToolResponse<Integer> actual = vcgService.run(SourceCodePath);
    final ToolResponse<Integer> expected = new ToolResponse<>(SourceCodePath ,3, 0, "Go");
    assertEquals(expected.getValue(), actual.getValue());
  }

}
