package com.example.java.gettingstarted.objects;

public class Device {
  // this simple Device is used for basic location service
  // TODO: see senarios
  // [START Device]
  private String did;
  private String uid;
  private String type;
// [END Device]
// [START keys]
  public static final String UID = "uid";
  public static final String TYPE = "type";
  public static final String DID = "did";
// [END keys]
// [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Device objects.
  private Device(Builder builder) {
    this.did = builder.did;
    this.uid = builder.uid;
    this.type = builder.type;
  }
// [END constructor]
// [START builder]
  public static class Builder {
    private String did;
    private String uid;
    private String type;

    public Builder did(String did) {
      this.did = did;
      return this;
    }

    public Builder uid(String uid) {
      this.uid = uid;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Device build() {
      return new Device(this);
    }
  }

  public String getdid() {
    return did;
  }

  public void setdid(String did) {
    this.did = did;
  }

  public String getuid() {
    return uid;
  }

  public void setuid(String uid) {
    this.uid = uid;
  }

  public String gettype() {
    return type;
  }

  public void settype(String type) {
    this.type = type;
  }

}
