/*=========================================================================
 * Copyright (c) 2010-2011 VMware, Inc. All rights reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. VMware products are covered by
 * one or more patents listed at http://www.vmware.com/go/patents.
 *=========================================================================
 */
package com.gemstone.gemfire.tutorial;

import java.io.IOException;

import com.gemstone.gemfire.tutorial.storage.GemfireDAO;

/**
 * Main method that connects to the GemFire distributed system as a client and
 * launches a command line user interface for the social networking application.
 * 
 * @author GemStone Systems, Inc.
 */
public class Client {

  public static void main(String[] args) throws IOException {
    GemfireDAO dao = new GemfireDAO();
    dao.initClient("localhost", 55221);
    TextUI ui= new TextUI(dao, System.in, System.out);
    ui.processCommands();
  }

}
