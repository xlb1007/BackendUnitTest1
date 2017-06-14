package com.example.java.gettingstarted;

import java.lang.*;
import java.util.List;
import java.util.LinkedList;
import com.example.java.gettingstarted.objects.Gym;
import com.example.java.gettingstarted.objects.Facility;
import com.example.java.gettingstarted.objects.Result;
import com.example.java.gettingstarted.objects.Prediction;
import com.example.java.gettingstarted.objects.Report;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.StringValue;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.cloud.datastore.Transaction;
import com.google.cloud.datastore.QueryResults;

import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;

@SpringBootApplication
@RestController
public class HelloworldApplication {

  @CrossOrigin()
  @RequestMapping("/test/map")
  public Result<Gym> goToMap(@RequestParam(required=false, defaultValue="1") String uid) {

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query = Query.newEntityQueryBuilder().setKind("Gym").build();
    QueryResults<Entity> results =  datastore.run(query);

    List<Gym> temp = new LinkedList<>();
    while (results.hasNext()) {
      Entity e = results.next();
      temp.add(new Gym.Builder()
      .gid(String.valueOf(e.getKey().getId()))
      .name(e.getString(Gym.NAME))
      .lat(String.valueOf(e.getDouble(Gym.LAT)))
      .lon(String.valueOf(e.getDouble(Gym.LON)))
      .address(e.getString(Gym.ADDRESS))
      .description(e.getString(Gym.DESCRIPTION))
      .build());
    }
    return new Result<>(temp);
  }


  @CrossOrigin() // test matchers for JSON
  @GetMapping("/test1")
  public Response1 test1(@RequestParam(required=false, defaultValue="World") String did) {

      return new Response1("1", "2", "3");

  }

  // @RequestMapping("/test3")
  // public Response1 test3(@RequestParam(required=false, defaultValue="World") String did) throws Exception {
  //
  //   DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  //   //Key deviceKey = KeyFactory.createKey("Device");
  //   Entity newDevice = new Entity("Device");
  //   newDevice.setProperty("Type", "iphone");
	// 	datastore.put(newDevice);
  //   Entity returnDevice = datastore.get(newDevice.getKey());
  //   return new Response1((String) returnDevice.getProperty("Type"), "2", "3");
  // }

  @RequestMapping("/")
  public String home() {
    return "Hello Bo!";
  }

  @RequestMapping("/_ah/health")
  public String healthy() {
    // Message body required though ignored
    return "Still surviving.";
  }


  @CrossOrigin()
  @RequestMapping("/test/gym")
  public Result<Facility> goToGym(@RequestParam(required=false, defaultValue="0") String gid) {

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query = Query.newEntityQueryBuilder()
                                .setKind("Facility")
                                .setFilter(PropertyFilter.eq("gid", Long.parseLong(gid)))
                                .build();
    QueryResults<Entity> results =  datastore.run(query);

    List<Facility> temp = new LinkedList<>();
    while (results.hasNext()) {
      Entity e = results.next();
      temp.add(new Facility.Builder()
      .fid(String.valueOf(e.getKey().getId()))
      .gid(String.valueOf(e.getLong(Facility.GID)))
      .tid(String.valueOf(e.getLong(Facility.TID)))
      .description(e.getString(Facility.DESCRIPTION))
      .build());
    }
    return new Result<>(temp);
  }

  @CrossOrigin()
  @RequestMapping("/test/facility")
  public Result<Prediction> goToFacility(@RequestParam(required=false, defaultValue="0") String fid) {

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query = Query.newEntityQueryBuilder()
                                .setKind("Prediction")
                                .setFilter(PropertyFilter.eq("fid", Long.parseLong(fid)))
                                //.setFilter(PropertyFilter.ge("time", Timestamp.now()))
                                .setFilter(PropertyFilter.ge("time", Timestamp.ofTimeMicroseconds(1)))
                                .setOrderBy(OrderBy.asc("time"))
                                .build();
    QueryResults<Entity> results =  datastore.run(query);

    List<Prediction> temp = new LinkedList<>();
    while (results.hasNext()) {
        Entity e = results.next();
        temp.add(new Prediction.Builder()
        .pid(String.valueOf(e.getKey().getId()))
        .fid(fid)
        .time(String.valueOf(e.getTimestamp(Prediction.TIME).getSeconds())) // in JS new Date(time * 1000)
        .waitTime(String.valueOf(e.getLong(Prediction.WAITTIME)))
        .build());
    }
    return new Result<>(temp);
  }

  @CrossOrigin()
  @RequestMapping("/test/report")
  public String reportTraffic(@RequestParam String fid,
    @RequestParam String uid, @RequestParam String waitTime) {

      Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
      KeyFactory keyFactory = datastore.newKeyFactory().setKind("Report");
      Key key = datastore.allocateId(keyFactory.newKey());

      Entity e = Entity.newBuilder(key)
        .set("fid", Long.parseLong(fid))
        .set("uid", Long.parseLong(uid))
        .set("time", Timestamp.now())
        .set("waitTime", Long.parseLong(waitTime))
        .build();

      datastore.put(e);
      return Boolean.TRUE.toString();
  }

  @CrossOrigin()
  @RequestMapping("/test/facility1")
  public Result<Prediction> goToFacility1(@RequestParam(required=false, defaultValue="0") String fid) {

    // TODO: update prediction array in the facility with fid if it's not updated within 2 hours

    // 2. update the first prediction after current time based on the recent report
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query = Query.newEntityQueryBuilder()
                                .setKind("Prediction")
                                .setFilter(PropertyFilter.eq("fid", Long.parseLong(fid)))
                                //.setFilter(PropertyFilter.ge("time", Timestamp.now()))
                                .setFilter(PropertyFilter.ge("time", Timestamp.ofTimeMicroseconds(1)))
                                .setOrderBy(OrderBy.asc("time"))
                                .build();
    QueryResults<Entity> results =  datastore.run(query);

    // 3. return a list of prediction after current time
    Calendar cal = Calendar.getInstance();
    TimeZone tz = TimeZone.getTimeZone("PST");
    cal.setTimeZone(tz);
    int hours = cal.get(Calendar.HOUR_OF_DAY);
    int size = 3;

    List<Prediction> temp = new LinkedList<>();
    if (hours < 6 || hours > 22) {
      while (results.hasNext() && size > 0) {
          size--;
          Entity e = results.next();
          temp.add(new Prediction.Builder()
          .pid(String.valueOf(e.getKey().getId()))
          .fid(fid)
          .time(String.valueOf(e.getTimestamp(Prediction.TIME).getSeconds())) // in JS new Date(time * 1000)
          .waitTime(String.valueOf(e.getLong(Prediction.WAITTIME)))
          .build());
      }
    } else {
        hours -= 6;
        while (results.hasNext() && size > 0) {
            if (hours != 0) {
              Entity e1 = results.next();
              hours--;
              continue;
            }
            size--;
            Entity e = results.next();
            temp.add(new Prediction.Builder()
            .pid(String.valueOf(e.getKey().getId()))
            .fid(fid)
            .time(String.valueOf(e.getTimestamp(Prediction.TIME).getSeconds())) // in JS new Date(time * 1000)
            .waitTime(String.valueOf(e.getLong(Prediction.WAITTIME)))
            .build());
        }
    }
    return new Result<>(temp);
  }

  @CrossOrigin()
  @RequestMapping("/test/facility2")
  public Result<Prediction> goToFacility2(@RequestParam(required=false, defaultValue="0") String fid) {

    // TODO: update prediction array in the facility with fid if it's not updated within 2 hours
    update(fid);

    // 2. update the first prediction after current time based on the recent report
    int instantTraffic = caculate(fid);
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query = Query.newEntityQueryBuilder()
                                .setKind("Prediction")
                                .setFilter(PropertyFilter.eq("fid", Long.parseLong(fid)))
                                //.setFilter(PropertyFilter.ge("time", Timestamp.now()))
                                .setFilter(PropertyFilter.ge("time", Timestamp.ofTimeMicroseconds(1)))
                                .setOrderBy(OrderBy.asc("time"))
                                .build();
    QueryResults<Entity> results =  datastore.run(query);

    // 3. return a list of prediction after current time
    Calendar cal = Calendar.getInstance();
    TimeZone tz = TimeZone.getTimeZone("PST");
    cal.setTimeZone(tz);
    int hours = cal.get(Calendar.HOUR_OF_DAY);
    int size = 3;

    List<Prediction> temp = new LinkedList<>();
    if (hours < 6 || hours > 22) {
      while (results.hasNext() && size > 0) {
          size--;
          Entity e = results.next();
          temp.add(new Prediction.Builder()
          .pid(String.valueOf(e.getKey().getId()))
          .fid(fid)
          .time(String.valueOf(e.getTimestamp(Prediction.TIME).getSeconds())) // in JS new Date(time * 1000)
          .waitTime(String.valueOf(e.getLong(Prediction.WAITTIME)))
          .build());
      }
    } else {
        hours -= 6;
        while (results.hasNext() && size > 0) {
            if (hours != 0) {
              Entity e1 = results.next();
              hours--;
              continue;
            }
            size--;
            Entity e = results.next();
            temp.add(new Prediction.Builder()
            .pid(String.valueOf(e.getKey().getId()))
            .fid(fid)
            .time(String.valueOf(e.getTimestamp(Prediction.TIME).getSeconds())) // in JS new Date(time * 1000)
            .waitTime(String.valueOf(e.getLong(Prediction.WAITTIME)))
            .build());
        }
    }
    if (instantTraffic != -1) {
      temp.get(0).setwaitTime(String.valueOf(instantTraffic));
      Timestamp base = Timestamp.ofTimeMicroseconds((Timestamp.now().getSeconds() - 1800) * 1000 * 1000);
      temp.get(0).settime(String.valueOf(base.getSeconds()));
    }
    return new Result<>(temp);
  }


  private int caculate(String fid) {
    // calculate the waitTime for current period
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Timestamp base = Timestamp.ofTimeMicroseconds((Timestamp.now().getSeconds() - 1800)* 1000 * 1000);
    Query<Entity> query = Query.newEntityQueryBuilder()
                                .setKind("Report")
                                .setFilter(PropertyFilter.eq("fid", Long.parseLong(fid)))
                                //.setFilter(PropertyFilter.ge("time", base))
                                .setFilter(PropertyFilter.gt("time", base))
                                .setOrderBy(OrderBy.desc("time"))
                                .setLimit(10)
                                .build();
    QueryResults<Entity> results =  datastore.run(query);
    int sum = -1;
    int size = 0;
    while (results.hasNext()) {
        Entity e = results.next();
        sum += (int) e.getLong(Report.WAITTIME);
        size++;
    }
    if (size != 0) {
      sum++;
      sum /= size;
    }
    return sum;
  }

  private void update(String fid) {
      // TODO: 1 check the updateTime (timestamp)
      // 2. if updateTime is less than the current time for more than 1 days
      // we update the prediction array for the facility with fid
      // for each facility:
      //    for each hour (-30 mins to 30 mins)
      //      1. order reports by days
      //      2. get the average for each day
      //      3. get a weighted average: as days back, weight decreased, like 0.7 0.2 0.1
      //      4. current = (9 * (weighted averaged of reports ) + current * 1)   /  10
  }


  public static void main(String[] args) {
    SpringApplication.run(HelloworldApplication.class, args);
  }
}


// uid = 3
// time = 1497247200000
// fid = 5685265389584384
// waitTime = 20

// https://datastoretest-164219.appspot.com/test/report?fid=5685265389584384&uid=3&waitTime=20
// https://datastoretest-164219.appspot.com/test/facility2?fid=5685265389584384
// https://datastoretest-164219.appspot.com/test/report?fid=5685265389584384&uid=3&waitTime=20
