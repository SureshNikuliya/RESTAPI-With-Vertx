package com.psl.restapi.user_rest_api.models;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.json.JsonObject;
import java.util.List;
import java.util.Map;

@DataObject(generateConverter = true, publicConverter = false)
public class User {

  private Integer id;
  private String name;

  public User (
    Integer id,
    String name
  ) {
    this.id = id;
    this.name = name;
  }

  public User(JsonObject json) {
    UserConverter.fromJson(json, this);
  }

  public User(User other) {
    this.id = other.getId();
    this.name = other.getName();
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    UserConverter.toJson(this, json);
    return json;
  }

  @Fluent public User setId(Integer id){
    this.id = id;
    return this;
  }
  public Integer getId() {
    return this.id;
  }

  @Fluent public User setName(String name){
    this.name = name;
    return this;
  }
  public String getName() {
    return this.name;
  }

}