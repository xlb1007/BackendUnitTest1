package com.example.java.gettingstarted.objects;

public class Report {
  // this simple Report is used for basic location service
  // TODO: see senarios
  // [START Report]
  private String rid;
  private String fid;
  private String uid;
  private String time;
  private String waitTime;
// [END Report]
// [START keys]
  public static final String FID = "fid";
  public static final String UID = "uid";
  public static final String TIME = "time";
  public static final String WAITTIME = "waitTime";
  public static final String RID = "rid";
// [END keys]
// [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Report objects.
  private Report(Builder builder) {
    this.rid = builder.rid;
    this.fid = builder.fid;
    this.uid = builder.uid;
    this.time = builder.time;
    this.waitTime = builder.waitTime;
  }
// [END constructor]
// [START builder]
  public static class Builder {
    private String rid;
    private String fid;
    private String uid;
    private String time;
    private String waitTime;

    public Builder rid(String rid) {
      this.rid = rid;
      return this;
    }

    public Builder fid(String fid) {
      this.fid = fid;
      return this;
    }

    public Builder uid(String uid) {
      this.uid = uid;
      return this;
    }

    public Builder time(String time) {
      this.time = time;
      return this;
    }

    public Builder waitTime(String waitTime) {
      this.waitTime = waitTime;
      return this;
    }

    public Report build() {
      return new Report(this);
    }
  }

  public String getrid() {
    return rid;
  }

  public void setrid(String rid) {
    this.rid = rid;
  }

  public String getfid() {
    return fid;
  }

  public void setfid(String fid) {
    this.fid = fid;
  }

  public String getuid() {
    return uid;
  }

  public void setuid(String uid) {
    this.uid = uid;
  }

  public String gettime() {
    return time;
  }

  public void settime(String time) {
    this.time = time;
  }

  public String getwaitTime() {
    return waitTime;
  }

  public void setwaitTime(String waitTime) {
    this.waitTime = waitTime;
  }

}
