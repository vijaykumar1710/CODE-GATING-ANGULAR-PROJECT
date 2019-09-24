/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditorContentToFile {

  static final Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private EditorContentToFile() {}

  public static String editorContentTofile(String content) throws IOException {
    final String path = System.getProperty("user.dir");
    final File file = new File(path+File.separator+"test//src//Test.java");
    if(file.exists()) {
      try {
        file.delete();
        final FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
      } catch (final IOException e) {
        logger.log(Level.WARNING,"error",e);
      }
    }else {
      final FileWriter writer = new FileWriter(file);
      writer.write(content);
      writer.close();
    }

    return file.getAbsolutePath();
  }
}
