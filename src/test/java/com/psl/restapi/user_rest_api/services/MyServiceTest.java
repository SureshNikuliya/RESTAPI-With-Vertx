package com.psl.restapi.user_rest_api.services;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.api.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.psl.restapi.user_rest_api.models.*;

/**
 * MyService Test
 */
@RunWith(VertxUnitRunner.class)
public class MyServiceTest {

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  MyService myService;

  @Before
  public void before(TestContext context) {
    Vertx vertx = rule.vertx();
    

    List<User> userList = new ArrayList<User>();
    int [] id = {101,102,103,104,105,106,107,108,109,110};
    String [] name = {"Brett","Riva","Rick","Ariane","Angeles","Minna","Rema","Jama","Katherine","Noble"};
    for(int i=0;i<10;i++)
    {
    	User user = new User(id[i],name[i]);
    	userList.add(user);
    }
    myService = MyService.create(vertx,userList);
    //TODO add some test initialization code like security token retrieval
  }

  @After
  public void after(TestContext context) {
    //TODO add some test end code like session destroy
  }

  @Test
  public void getUsersListTest(TestContext context) {
    Async async = context.async(3);

    // TODO set parameters for 200 response test
    myService.getUsersList(new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(200, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });

    // TODO set parameters for 500 response test
    myService.getUsersList(new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(500, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });

    // TODO set parameters for 501 response test
    myService.getUsersList(new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(501, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });
  }

  @Test
  public void createUserTest(TestContext context) {
    Async async = context.async(3);
    User body;

    // TODO set parameters for 200 response test
    body = new User(201,"Test case 1");
    myService.createUser(body, new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(200, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });

    // TODO set parameters for 500 response test
    body = new User(201,"Test case 1");;
    myService.createUser(body, new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(500, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });

    // TODO set parameters for 501 response test
    body = new User(201,"Test case 1");
    myService.createUser(body, new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(501, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });
  }

  @Test
  public void getUserTest(TestContext context) {
    Async async = context.async(3);
    Integer userId;

    // TODO set parameters for 200 response test
    userId = 201;
    myService.getUser(userId, new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(200, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });

    // TODO set parameters for 500 response test
    userId = 201;
    myService.getUser(userId, new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(500, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });

    // TODO set parameters for 501 response test
    userId = 201;
    myService.getUser(userId, new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(501, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });
  }

  @Test
  public void updateUserTest(TestContext context) {
    Async async = context.async(2);
    Integer userId;
    User body;

    // TODO set parameters for 200 response test
    userId = 201;
    body = new User(201,"Test case 2");
    myService.updateUser(userId, body, new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(200, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });

    // TODO set parameters for 500 response test
    userId = 201;
    body = new User(201,"Test case 2");
    myService.updateUser(userId, body, new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(500, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });
  }

  @Test
  public void deleteUseeTest(TestContext context) {
    Async async = context.async(2);
    Integer userId;

    // TODO set parameters for 200 response test
    userId = null;
    myService.deleteUsee(userId, new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(200, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });

    // TODO set parameters for 500 response test
    userId = 201;
    myService.deleteUsee(userId, new OperationRequest(), ar -> {
      if (ar.succeeded()) {
        OperationResponse result = ar.result();
        context.assertEquals(500, result.getStatusCode());
        //TODO add your asserts
      } else {
        context.fail("Operation failed with " + ar.cause().toString());
      }
      async.countDown();
    });
  }


}