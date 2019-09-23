package com.psl.restapi.user_rest_api.services;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.api.*;
import io.vertx.ext.web.api.generator.WebApiServiceGen;

import java.util.List;
import java.util.Map;

import com.psl.restapi.user_rest_api.models.*;
import com.psl.restapi.user_rest_api.services.impl.MyServiceImpl;

@WebApiServiceGen
public interface MyService {

  static MyService create(Vertx vertx,List<User> uList) {
    return new MyServiceImpl(vertx,uList);
  }

  void getUsersList(
    OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);

  void createUser(
    User body,
    OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);

  void getUser(
    Integer userId,
    OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);

  void updateUser(
    Integer userId,
    User body,
    OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);

  void deleteUsee(
    Integer userId,
    OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);

}
