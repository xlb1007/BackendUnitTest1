package com.example.java.gettingstarted.objects;

public class Type {
  // this simple Type is used for basic location service
  // TODO: see senarios
  // [START Type]
  private String tid;
  private String name;

// [END Type]
// [START keys]
  public static final String NAME = "name";
  public static final String TID = "tid";
// [END keys]
// [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Type objects.
  private Type(Builder builder) {
    this.tid = builder.tid;
    this.name = builder.name;
  }
// [END constructor]
// [START builder]
  public static class Builder {
    private String tid;
    private String name;

    public Builder tid(String tid) {
      this.tid = tid;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Type build() {
      return new Type(this);
    }
  }

  public String gettid() {
    return tid;
  }

  public void settid(String tid) {
    this.tid = tid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
