/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.utility;

import java.io.File;
import java.util.List;

public class Utility {

  private Utility() {}

  public static boolean isLessThan(double timeToRunTests, float threshold) {
    return timeToRunTests <= threshold;
  }

  public static boolean isGreaterThan(float value, float threshold) {
    return value >= threshold;
  }

  public static void searchFilesInDirectory(final String pattern, final File folder, List<String> result) {

    for (final File file : folder.listFiles()) {
      if (file.isDirectory()) {
        searchFilesInDirectory(pattern, file, result);
      }
      if (file.getName().matches(pattern)) {
        result.add(file.getAbsolutePath());
      }
    }
  }

}
