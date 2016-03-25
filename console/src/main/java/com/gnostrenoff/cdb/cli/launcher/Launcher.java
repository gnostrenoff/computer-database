package com.gnostrenoff.cdb.cli.launcher;

import com.gnostrenoff.cdb.cli.listener.Listener;

/**
 * The Class Launcher.
 */
public class Launcher {

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {

    Listener listener = new Listener();
    listener.listen();

  }

}
