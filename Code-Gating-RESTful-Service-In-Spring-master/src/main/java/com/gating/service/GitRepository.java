/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.gating.utility.CreateCloneDir;

public class GitRepository {
  static final Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  public static CloneResponse cloneRepo(String repoUrl) throws InterruptedException{
    final String cloneDirectoryPath = CreateCloneDir.getDir("LocalGitRepo");
    String status = null;
    String sourcePath = null;
    try {
      final ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command("cmd.exe", "/c", " git clone -v " + repoUrl + " "+ cloneDirectoryPath);
      processBuilder.redirectErrorStream(true);
      final Process process = processBuilder.start();

      logger.log(Level.INFO, "Started");
      final BufferedReader reader = new BufferedReader(
          new InputStreamReader(process.getInputStream()));

      String line = null;
      while ((line = reader.readLine()) != null) {
        logger.log(Level.INFO,line);
      }
      final int exitVal = process.waitFor();
      if (exitVal == 0) {
        status = "Success";
        sourcePath = cloneDirectoryPath;
        process.destroy();
      } else {
        status = "fail";
        sourcePath = "fail";
      }


    } catch (final IOException e ) {
      logger.log(Level.WARNING, e.getMessage());
    }

    return new CloneResponse(status, sourcePath);
  }
  private GitRepository() {}

}

