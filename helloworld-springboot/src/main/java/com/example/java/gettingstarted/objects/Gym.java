package com.example.java.gettingstarted.objects;

public class Gym {
  // this simple gym is used for basic location service
  // TODO: see senarios
  // [START Gym]
  private String gid;
  private String name;
  private String lat;
  private String lon;
  private String address;
  private String description;
// [END Gym]
// [START keys]
  public static final String NAME = "name";
  public static final String LAT = "lat";
  public static final String LON = "lon";
  public static final String DESCRIPTION = "description";
  public static final String ADDRESS = "address";
  public static final String GID = "gid";
// [END keys]
// [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Gym objects.
  private Gym(Builder builder) {
    this.gid = builder.gid;
    this.name = builder.name;
    this.lat = builder.lat;
    this.lon = builder.lon;
    this.address = builder.address;
    this.description = builder.description;
  }
// [END constructor]
// [START builder]
  public static class Builder {
    private String gid;
    private String name;
    private String lat;
    private String lon;
    private String address;
    private String description;

    public Builder gid(String gid) {
      this.gid = gid;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder lat(String lat) {
      this.lat = lat;
      return this;
    }

    public Builder lon(String lon) {
      this.lon = lon;
      return this;
    }

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Gym build() {
      return new Gym(this);
    }
  }

  public String getgid() {
    return gid;
  }

  public void setgid(String gid) {
    this.gid = gid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLon() {
    return lon;
  }

  public void setLon(String lon) {
    this.lon = lon;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
