package com.psl.restapi.user_rest_api.services.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.psl.restapi.user_rest_api.models.*;
import com.psl.restapi.user_rest_api.services.MyService;

public class MyServiceImpl implements MyService {

  private Vertx vertx;
  private List<User> userList = new ArrayList<User>();

  public MyServiceImpl(Vertx vertx,List<User> uList) {
    this.vertx = vertx;
    this.userList = uList;
    
  }

  @Override
  public void getUsersList(
    OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler){
	  JsonArray userArray = new JsonArray();
	  for(User user: userList)
	  {
		  userArray.add(user.toJson());
	  }
	  
	  resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(userArray)));
  }


  @Override
  public void createUser(
    User body,
    OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler){
	  
	  boolean flag = false;
	  for(User user :userList)
	  {
		  if(user.getId().equals(body.getId()))
		  {
			  flag = true;
			  break;
		  }			  
	  }
	 if(flag)
	 {
		 resultHandler.handle(Future.succeededFuture(new OperationResponse().setStatusMessage("User with ID : "+body.getId() + " Already Exists")));
	 }
	 else
	 {
		 userList.add(body);
		 resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(body.toJson())));
	 }
	  	

  }

  @Override
  public void getUser(
    Integer userId,
    OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler){
	  
	  boolean flag = false;
	  for(User user :userList)
	  {
		  if(user.getId().equals(userId))
		  {
			  resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(user.toJson())));
			  flag = true;
		  }			  
	  }
	 if(!flag)
	 {
		 resultHandler.handle(Future.succeededFuture(new OperationResponse().setStatusMessage("User with ID : "+userId + " not Found!!")));
	 }
  }

  @Override
  public void updateUser(
    Integer userId,
    User body,
    OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler){
	  boolean flag = false;
	  for(User user :userList)
	  {
		  if(user.getId().equals(userId))
		  {
			  user.setId(body.getId());
			  user.setName(body.getName());
			  resultHandler.handle(Future.succeededFuture(new OperationResponse().setStatusMessage("User Updated Successfully!!")));
			  flag = true;
		  }			  
	  }
	  if(!flag)
		 {
			 resultHandler.handle(Future.succeededFuture(new OperationResponse().setStatusMessage("User with ID : "+userId + " not Found!!")));
		 }
    
  }

  @Override
  public void deleteUsee(
    Integer userId,
    OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler){
	  boolean flag = false;
	  for(User user :userList)
	  {
		  if(user.getId().equals(userId))
		  {
			  userList.remove(user);
			  resultHandler.handle(Future.succeededFuture(new OperationResponse().setStatusMessage("User Deleted Successfully!!")));
			  flag = true;
		  }			  
	  }
	  if(!flag)
	  {
		  resultHandler.handle(Future.succeededFuture(new OperationResponse().setStatusMessage("User with ID : "+userId + " not Found!!")));
	  }
  }

}
