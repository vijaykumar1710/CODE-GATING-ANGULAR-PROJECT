/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.staticanalysis.service;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.gating.Application;
import com.gating.toolconfig.service.ToolResponse;
import com.gating.utility.InternalServiceException;
import com.gating.utility.InvalidInputException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class SimianServiceTest {

  @Autowired
  SimianService simianService;

  @Test
  public void testRun() throws IOException, InterruptedException, InvalidInputException {

    final String SourceCodePath = "C:\\Users\\320052131\\Videos\\junitmavenexample";
    final ToolResponse<Integer> actual =  simianService.run(SourceCodePath);
    final ToolResponse<Integer> expected = new ToolResponse<>(SourceCodePath, 0, 0, "Go");

    assertEquals(expected.getValue(), actual.getValue());
  }

  @Test
  public void testParseSimianReport() throws IOException, InvalidInputException {

    final String SIMIAN_REPORT_PATH =
        System.getProperty("user.dir") + "\\reports\\simian_report.txt";

    final int  duplication = simianService.parseSimianTextReport(SIMIAN_REPORT_PATH);
    assertEquals(0,duplication);
  }


  @Test(expected = InternalServiceException.class)
  public void testParseSimianReportThrowsExceptionWhenItsUnableToFindReport() throws InvalidInputException, IOException {

    final String SIMIAN_REPORT_PATH =
        System.getProperty("user.dir") + "\\reports\\invalid_report.txt";

    simianService.parseSimianTextReport(SIMIAN_REPORT_PATH);
  }

}
