package com.example.java.gettingstarted.objects;

public class User {
  // this simple User is used for basic location service
  // TODO: see senarios
  // [START User]
  private String uid;
  private String name;
  private String preGym;
  private String preFacility;
  private String googleID;
// [END User]
// [START keys]
  public static final String NAME = "name";
  public static final String PREGYM = "preGym";
  public static final String PREFACILITY = "preFacility";
  public static final String GOOGLEID = "googleID";
  public static final String UID = "uid";
// [END keys]
// [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of User objects.
  private User(Builder builder) {
    this.uid = builder.uid;
    this.name = builder.name;
    this.preGym = builder.preGym;
    this.preFacility = builder.preFacility;
    this.googleID = builder.googleID;
  }
// [END constructor]
// [START builder]
  public static class Builder {
    private String uid;
    private String name;
    private String preGym;
    private String preFacility;
    private String googleID;

    public Builder uid(String uid) {
      this.uid = uid;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder preGym(String preGym) {
      this.preGym = preGym;
      return this;
    }

    public Builder preFacility(String preFacility) {
      this.preFacility = preFacility;
      return this;
    }

    public Builder googleID(String googleID) {
      this.googleID = googleID;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }

  public String getuid() {
    return uid;
  }

  public void setuid(String uid) {
    this.uid = uid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getpreGym() {
    return preGym;
  }

  public void setpreGym(String preGym) {
    this.preGym = preGym;
  }

  public String getpreFacility() {
    return preFacility;
  }

  public void setpreFacility(String preFacility) {
    this.preFacility = preFacility;
  }

  public String getgoogleID() {
    return googleID;
  }

  public void setgoogleID(String googleID) {
    this.googleID = googleID;
  }


}
