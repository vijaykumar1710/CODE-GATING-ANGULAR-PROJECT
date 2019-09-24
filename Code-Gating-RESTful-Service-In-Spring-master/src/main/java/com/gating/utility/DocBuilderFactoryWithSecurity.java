/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.gating.utility;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class DocBuilderFactoryWithSecurity {
  static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private DocBuilderFactoryWithSecurity() {}
  public static DocumentBuilderFactory docBuilder() {
    final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(false);
    factory.setNamespaceAware(true);
    try {
      factory.setFeature("http://xml.org/sax/features/namespaces", false);
      factory.setFeature("http://xml.org/sax/features/validation", false);
      factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
      factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    } catch (final ParserConfigurationException e) {
      logger.log(Level.WARNING, e.getMessage());
    }
    return factory;

  }

}
