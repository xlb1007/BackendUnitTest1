package com.example.java.gettingstarted;

import java.lang.*;
import java.util.List;
import java.util.LinkedList;
import com.example.java.gettingstarted.objects.Gym;
import com.example.java.gettingstarted.objects.Facility;
import com.example.java.gettingstarted.objects.Result;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// try appengine datastore api
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;

@SpringBootApplication
@RestController
public class HelloworldApplication {

  @CrossOrigin()
  @RequestMapping("/test/map")
  public Result<Gym> goToMap(@RequestParam(required=false, defaultValue="1") String uid) {

    // DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    // Query query = new Query("Gym");
    //
    // PreparedQuery pq = datastore.prepare(query);
    // List<Entity> gyms = pq.asList(FetchOptions.Builder.withLimit(2));



    List<Gym> temp = new LinkedList<>();

    // for (Entity e : gyms) {
    //   temp.add(new Gym.Builder()
    //   .gid(String.valueOf(e.getKey().getId()))
    //   .name((String)e.getProperty(Gym.NAME))
    //   .lat((String)e.getProperty(Gym.LAT))
    //   .lon((String)e.getProperty(Gym.LON))
    //   .address((String)e.getProperty(Gym.ADDRESS))
    //   .description((String)e.getProperty(Gym.DESCRIPTION))
    //   .build());
    // }

      temp.add(new Gym.Builder()
      .gid("g1")
      .name("SCU")
      .lat("37.3492264")
      .lon("-121.9371648")
      .address("sc,ca,us")
      .description("This is a good gym.")
      .build());

      temp.add(new Gym.Builder()
      .gid("g2")
      .name("Malley Center")
      .lat("37.3501577")
      .lon("-121.936603")
      .address("sc1,ca,us")
      .description("This is anothre good gym.")
      .build());
    return new Result<>(temp);
  }


  @CrossOrigin() // test matchers for JSON
  @GetMapping("/test1")
  public Response1 test1(@RequestParam(required=false, defaultValue="World") String did) {

      return new Response1("1", "2", "3");

  }

  @RequestMapping("/test3")
  public Response1 test3(@RequestParam(required=false, defaultValue="World") String did) throws Exception {

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    //Key deviceKey = KeyFactory.createKey("Device");
    Entity newDevice = new Entity("Device");
    newDevice.setProperty("Type", "iphone");
		datastore.put(newDevice);
    Entity returnDevice = datastore.get(newDevice.getKey());
    return new Response1((String) returnDevice.getProperty("Type"), "2", "3");
  }

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
  public Result<Facility> goToGym(@RequestParam(required=false, defaultValue="g0") String gid) {

    List<Facility> temp = new LinkedList<>();

      temp.add(new Facility.Builder()
      .fid("f1")
      .gid("g1")
      .tid("t1")
      .description("Weight Room.")
      .build());

      temp.add(new Facility.Builder()
      .fid("f2")
      .gid("g1")
      .tid("t2")
      .description("Swimming Pool.")
      .build());
    return new Result<>(temp);
  }


  public static void main(String[] args) {
    SpringApplication.run(HelloworldApplication.class, args);
  }
}
