package com.gating.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.gating.utility.InternalServiceException;

@Service
public class ProcessUtility {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessUtility.class);

  public int runProcess(List<String> command, String toolBinPath){

    final ProcessBuilder processBuilder = new ProcessBuilder();
    final Map<String, String> envMap = processBuilder.environment();
    String path = envMap.get("Path");
    path += toolBinPath;
    envMap.put("Path", path);

    processBuilder.command(command);
    Process process = null;

    try{
      process = processBuilder.start();
      process.waitFor();
      return process.exitValue();
    }
    catch(final IOException e) {
      throw new InternalServiceException("Process could not be started", e);
    }
    catch(final InterruptedException e) {
      LOGGER.error("Process was interrupted");
      Thread.currentThread().interrupt();
    }

    return 1;


  }
}
