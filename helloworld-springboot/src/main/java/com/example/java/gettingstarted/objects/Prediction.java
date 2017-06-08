package com.example.java.gettingstarted.objects;

public class Prediction {
  // this simple Prediction is used for basic location service
  // TODO: see senarios
  // [START Prediction]
  private String pid;
  private String fid;
  private String time;
  private String waitTime;
// [END Prediction]
// [START keys]
  public static final String FID = "fid";
  public static final String TIME = "time";
  public static final String WAITTIME = "waitTime";
  public static final String PID = "pid";
// [END keys]
// [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Prediction objects.
  private Prediction(Builder builder) {
    this.pid = builder.pid;
    this.fid = builder.fid;
    this.time = builder.time;
    this.waitTime = builder.waitTime;
  }
// [END constructor]
// [START builder]
  public static class Builder {
    private String pid;
    private String fid;
    private String time;
    private String waitTime;

    public Builder pid(String pid) {
      this.pid = pid;
      return this;
    }

    public Builder fid(String fid) {
      this.fid = fid;
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

    public Prediction build() {
      return new Prediction(this);
    }
  }

  public String getpid() {
    return pid;
  }

  public void setpid(String pid) {
    this.pid = pid;
  }

  public String getfid() {
    return fid;
  }

  public void setfid(String fid) {
    this.fid = fid;
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
