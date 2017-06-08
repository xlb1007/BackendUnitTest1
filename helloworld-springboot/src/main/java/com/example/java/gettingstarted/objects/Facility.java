package com.example.java.gettingstarted.objects;

public class Facility {
  // this simple Facility is used for basic location service
  // TODO: see senarios
  // [START Facility]
  private String fid;
  private String gid;
  private String tid;
  private String description;
// [END Facility]
// [START keys]
  public static final String GID = "gid";
  public static final String TID = "tid";
  public static final String DESCRIPTION = "description";
  public static final String FID = "fid";
// [END keys]
// [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Facility objects.
  private Facility(Builder builder) {
    this.fid = builder.fid;
    this.gid = builder.gid;
    this.tid = builder.tid;
    this.description = builder.description;
  }
// [END constructor]
// [START builder]
  public static class Builder {
    private String fid;
    private String gid;
    private String tid;
    private String description;

    public Builder fid(String fid) {
      this.fid = fid;
      return this;
    }

    public Builder gid(String gid) {
      this.gid = gid;
      return this;
    }

    public Builder tid(String tid) {
      this.tid = tid;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Facility build() {
      return new Facility(this);
    }
  }

  public String getfid() {
    return fid;
  }

  public void setfid(String fid) {
    this.fid = fid;
  }

  public String getgid() {
    return gid;
  }

  public void setgid(String gid) {
    this.gid = gid;
  }

  public String gettid() {
    return tid;
  }

  public void settid(String tid) {
    this.tid = tid;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
