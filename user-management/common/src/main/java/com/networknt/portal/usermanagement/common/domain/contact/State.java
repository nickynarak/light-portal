

package com.networknt.portal.usermanagement.common.domain.contact;


/**
 * Created by lcsontos on 4/24/17.
 */

public enum State {

  AK("Alaska"),
  AL("Alabama"),
  AR("Arkansas"),
  AZ("Arizona"),
  CA("California"),
  CO("Colorado"),
  CT("Connecticut"),
  DE("Delaware"),
  FL("Florida"),
  GA("Georgia"),
  HI("Hawaii"),
  IA("Iowa"),
  ID("Idaho"),
  IL("Illinois"),
  IN("Indiana"),
  KS("Kansas"),
  KY("Kentucky"),
  LA("Louisiana"),
  MA("Massachusetts"),
  MD("Maryland"),
  ME("Maine"),
  MI("Michigan"),
  MN("Minnesota"),
  MO("Missouri"),
  MS("Mississippi"),
  MT("Montana"),
  NC("North Carolina"),
  ND("North Dakota"),
  NE("Nebraska"),
  NH("New Hampshire"),
  NJ("New Jersey"),
  NM("New Mexico"),
  NV("Nevada"),
  NY("New York"),
  OH("Ohio"),
  OK("Oklahoma"),
  OR("Oregon"),
  PA("Pennsylvania"),
  RI("Rhode Island"),
  SC("South Carolina"),
  SD("South Dakota"),
  TN("Tennessee"),
  TX("Texas"),
  UT("Utah"),
  VA("Virginia"),
  VT("Vermont"),
  WA("Washington"),
  WI("Wisconsin"),
  WV("West Virginia"),
  WY("Wyoming"),
  NA("N/A");

  private String state;

  State(String state) {
    this.state = state;
  }

}
