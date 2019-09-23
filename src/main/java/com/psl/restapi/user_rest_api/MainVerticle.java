package com.psl.restapi.user_rest_api;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.contract.RouterFactoryOptions;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.Router;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ServiceBinder;


import com.psl.restapi.user_rest_api.services.*;
import com.psl.restapi.user_rest_api.models.*;


import java.util.ArrayList;
import java.util.List;

public class MainVerticle extends AbstractVerticle {

  HttpServer server;
  ServiceBinder serviceBinder;

  List<MessageConsumer<JsonObject>> registeredConsumers;
  
  
  //Main method to start application
  public static void main(String[] args) {

      Vertx vertx = Vertx.vertx();

      VertxOptions options = new VertxOptions();

      options.setMaxEventLoopExecuteTime(120000000000L);

      vertx = Vertx.vertx(options);

      vertx.deployVerticle(new MainVerticle());

  } 

  /**
   * This method starts all services
   */
  private void startServices() {
    this.serviceBinder = new ServiceBinder(vertx);
    this.registeredConsumers = new ArrayList<>();

    MyService myService = MyService.create(vertx);
    registeredConsumers.add(
      serviceBinder
        .setAddress("users_service.myapp")
        .register(MyService.class, myService)
    );
  }

  /**
   * This method constructs the router factory, mounts services and handlers and starts the http server with built router
   * @return
   */
  private Future<Void> startHttpServer() {
    Future<Void> future = Future.future();
    OpenAPI3RouterFactory.create(this.vertx, "src/main/resources/openAPI3.json", openAPI3RouterFactoryAsyncResult -> {
      if (openAPI3RouterFactoryAsyncResult.succeeded()) {
        OpenAPI3RouterFactory routerFactory = openAPI3RouterFactoryAsyncResult.result();

        // Enable automatic response when ValidationException is thrown
        routerFactory.setOptions(new RouterFactoryOptions().setMountValidationFailureHandler(true));

        // Mount services on event bus based on extensions
        routerFactory.mountServicesFromExtensions();





        // Generate the router
        Router router = routerFactory.getRouter();
        server = vertx.createHttpServer(new HttpServerOptions().setPort(8080).setHost("10.244.32.121"));
        server.requestHandler(router).listen();
        
        future.complete();
      } else {
        // Something went wrong during router factory initialization
        future.fail(openAPI3RouterFactoryAsyncResult.cause());
      }
    });
    return future;
  }

  @Override
  public void start(Future<Void> future) {
    startServices();
    startHttpServer().setHandler(future.completer());
  }

  /**
   * This method closes the http server and unregister all services loaded to Event Bus
   */
  @Override
  public void stop(){
    this.server.close();
    registeredConsumers.forEach(c -> serviceBinder.unregister(c));
  }

}
