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
public class PMDConfigService {

  public PMDConfig getConfig() {

    final File propFile = new File("src/main/resources/pmd.config.properties");

    if (propFile.exists()) {
      try (FileInputStream fileInput = new FileInputStream(propFile)) {
        final Properties prop = new Properties();
        prop.load(fileInput);
        final PMDConfig pmdConfig = new PMDConfig();
        pmdConfig.setRuleSet(String.valueOf(prop.getProperty("ruleSet")));
        return pmdConfig;

      }
      catch (final IOException e)
      {
        throw new InternalServiceException("Error reading pmd config file", e);
      }
    }
    else
    {
      throw new InvalidInputException("Server Error : pmd config properties file not found", null);
    }
  }



  public void setConfig(PMDConfig pmdConfig) throws IOException {

    final Properties properties = new Properties();
    properties.setProperty("ruleSet", String.valueOf(pmdConfig.getRuleSet()));

    final FileOutputStream fileOut =
        new FileOutputStream(new File("src/main/resources/pmd.config.properties"));
    properties.store(fileOut, null);
    fileOut.close();
  }
}
