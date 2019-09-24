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
public class SimianConfigService {

  public SimianConfig getConfig()
  {

    final File propFile = new File("src/main/resources/simian.config.properties");

    if(propFile.exists()) {
      try(FileInputStream fileInput = new FileInputStream(propFile)){

        final Properties prop = new Properties();
        prop.load(fileInput);
        final SimianConfig simianConfig = new SimianConfig();
        simianConfig
        .setDuplicateLinesThreshold(Integer.valueOf(prop.getProperty("duplicateLinesThreshold")));

        return simianConfig;
      }
      catch(final IOException e) {
        throw new InternalServiceException("Error occured while reading simian config", e);
      }
    }
    else {
      throw new InvalidInputException("Server Error : simian config properties file not found", null);
    }
  }



  public void setConfig(SimianConfig simianConfig) throws IOException {

    final Properties properties = new Properties();
    properties.setProperty("duplicateLinesThreshold",
        String.valueOf(simianConfig.getDuplicateLinesThreshold()));

    final FileOutputStream fileOut =
        new FileOutputStream(new File("src/main/resources/simian.config.properties"));
    properties.store(fileOut, null);
    fileOut.close();

  }
}



