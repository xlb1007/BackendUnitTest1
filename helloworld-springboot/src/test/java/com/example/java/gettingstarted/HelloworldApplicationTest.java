package com.example.java.gettingstarted;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit; // new

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After; // new

import static org.hamcrest.Matchers.*; //try
import static org.mockito.Mockito.*;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = HelloworldApplication.class)
@SpringApplicationConfiguration(classes = HelloworldApplication.class)
@WebAppConfiguration
public class HelloworldApplicationTest {

  @Autowired // skip configuration
  WebApplicationContext ctx;

  MockMvc mockMvc = null; // Main entry point for server-side Spring MVC test support.

  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    helper.setUp(); // new
  }

  @After
  public void tearDown() {
    helper.tearDown(); // new
  }

  @Test // Test case 1: simple assert
  public void contextLoads() {
    assertEquals(3, 1 + 2);
  }

  @Test // Test case 2: simple return
  public void thatTheContextRootWorks() throws Exception {
    ResultActions m = mockMvc.perform(get("/"));
    m.andExpect(MockMvcResultMatchers.status().isOk());
    m.andExpect(content().string(startsWith("Hello ")));
  }

  // @Test // Test case 3: datastore testcase with app engine
  // public void testInsert1() {
  //   DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
  //   assertEquals(0, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
  //   ds.put(new Entity("yam")); // key: "yam"
  //   ds.put(new Entity("yam"));
  //   assertEquals(2, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
  // }
  //
  // @Test // Test case 4: matchers for json object as return
  // public void testStartup1() throws Exception {
  //   mockMvc.perform(get("/test1?did=1"))
  //          .andExpect(MockMvcResultMatchers.status().isOk())
  //          .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
  //                                           MediaType.APPLICATION_JSON.getSubtype(),
  //                                           Charset.forName("utf8")
  //                                           )))
  //          .andExpect(jsonPath("$.lat", is("1")));
  // }

  // @Test // Test case 5: datastore testcase with datastore basic api: not working
  // public void testStartup2() throws Exception {
  //   mockMvc.perform(get("/test2?did=1"))
  //          .andExpect(MockMvcResultMatchers.status().isOk())
  //          .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
  //                                           MediaType.APPLICATION_JSON.getSubtype(),
  //                                           Charset.forName("utf8")
  //                                           )))
  //          .andExpect(jsonPath("$.lat", is("1")));
  // }

  // @Test // Test case 6: request - process - return JSON
  // public void testStartup3() throws Exception {
  //   mockMvc.perform(get("/test3?did=1"))
  //          .andExpect(MockMvcResultMatchers.status().isOk())
  //          .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
  //                                           MediaType.APPLICATION_JSON.getSubtype(),
  //                                           Charset.forName("utf8")
  //                                           )))
  //          .andExpect(jsonPath("$.lat", is("iphone")));
  // }

}
