/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.utility;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class CreateCloneDir {
  static final Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  public static String getDir(String folderName) {

    final String sysDir =System.getProperty("user.dir");
    File directory=new File(sysDir);
    directory = new File(directory.getParent());
    final String cloneDir = directory.getParent()+File.separator+folderName;
    final File cloneDirectory=new File(cloneDir);
    cloneDirectory.mkdir();
    try {
      FileUtils.cleanDirectory(cloneDirectory);
    } catch (final IOException e) {
      logger.log(Level.WARNING, e.getMessage());
    }
    return cloneDir;
  }
  private CreateCloneDir() {}


}
