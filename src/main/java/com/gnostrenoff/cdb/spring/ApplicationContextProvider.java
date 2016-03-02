package com.gnostrenoff.cdb.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * The Class ApplicationContextProvider.
 */
public class ApplicationContextProvider implements ApplicationContextAware {
  
  /** The context. */
  private static ApplicationContext context;

  /**
   * Gets the application context.
   *
   * @return the application context
   */
  public static ApplicationContext getApplicationContext() {
    return context;
  }

  @Override
  public void setApplicationContext(ApplicationContext ctx) {
    context = ctx;
  }
}