/*=========================================================================
 * Copyright (c) 2010-2011 VMware, Inc. All rights reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. VMware products are covered by
 * one or more patents listed at http://www.vmware.com/go/patents.
 *=========================================================================
 */
package com.gemstone.gemfire.tutorial.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A profile for a user of the gemstone social networking application. The
 * profile contains the list of friends of this person.
 * 
 * Profiles are used as the value in the people region. They will be distributed
 * to other VMs, so they must be serializable.
 * 
 * @author GemStone Systems, Inc.
 */
public class Profile implements Serializable {
  private static final long serialVersionUID = -3569243165627161127L;
  
  private final Set<String> friends = new HashSet<String>();
  
  public boolean addFriend(String name) {
    return this.friends.add(name);
  }
  
  public boolean removeFriend(String name) {
    return this.friends.remove(name);
  }
  
  public Set<String> getFriends() {
    return Collections.unmodifiableSet(friends);
  }

  @Override
  public String toString() {
    return "Profile [friends=" + friends + "]";
  }
  
  
}
