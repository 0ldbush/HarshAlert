package com.alnt.platform.application.logger.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.ILoggerFactory;
import play.Environment;
import play.LoggerConfigurator;
import play.Mode;
import play.api.PlayException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.config.Configurator;

public class Log4J2LoggerConfigurator implements LoggerConfigurator {

  private ILoggerFactory factory;

  @Override
  public void init(File rootPath, Mode mode) {
    Map<String, String> properties = new HashMap<>();
    properties.put("application.home", rootPath.getAbsolutePath());

    String resourceName = "log4j2.xml";
    URL resourceUrl = this.getClass().getClassLoader().getResource(resourceName);
    configure(properties, Optional.ofNullable(resourceUrl));
  }

  @Override
  public void configure(Environment env) {
    Map<String, String> properties =
        LoggerConfigurator.generateProperties(env, ConfigFactory.empty(), Collections.emptyMap());
    URL resourceUrl = env.resource("log4j2.xml");
    configure(properties, Optional.ofNullable(resourceUrl));
  }

  @Override
  public void configure(
      Environment env, Config configuration, Map<String, String> optionalProperties) {
    // LoggerConfigurator.generateProperties enables play.logger.includeConfigProperties=true
    Map<String, String> properties =
        LoggerConfigurator.generateProperties(env, configuration, optionalProperties);
    URL resourceUrl = env.resource("log4j2.xml");
    configure(properties, Optional.ofNullable(resourceUrl));
  }

  @Override
  public void configure(Map<String, String> properties, Optional<URL> config) {
    try {
      LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
      loggerContext.setConfigLocation(config.get().toURI());

      factory = org.slf4j.impl.StaticLoggerBinder.getSingleton().getLoggerFactory();
    } catch (URISyntaxException ex) {
      throw new PlayException(
          "log4j2.xml resource was not found",
          "Could not parse the location for log4j2.xml resource",
          ex);
    }
  }

  @Override
  public ILoggerFactory loggerFactory() {
    return factory;
  }

  @Override
  public void shutdown() {
    LoggerContext loggerContext = (LoggerContext) LogManager.getContext();
    Configurator.shutdown(loggerContext);
  }
}
