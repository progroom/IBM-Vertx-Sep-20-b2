package com.ibm.vertx.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.example.util.Runner;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class BasicRouters extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(BasicRouters.class);
  }

  public void home(RoutingContext routingContext) {
    //using routing context , you can get request and response objects
    routingContext.response().putHeader("content-type", "text/html").end("<h1>Home</h1>");
  }

  public void createServer() {
    //create server object
    HttpServer httpServer = vertx.createHttpServer();

    //Routers : method and url mapping using vertx-web module
    //router is entry, which has collection of routes
    Router router = Router.router(vertx);
    //routes
    //router.HTTPmethod("url)
    router.get("/").handler(this::home);
    router.get("/api/hello").handler(routingContext -> {
      //using routing context , you can get request and response objects
      routingContext.response().putHeader("content-type", "text/html").end("<h1>Hello!!</h1>");
    });
    router.get("/api/hai").handler(routingContext -> {
      //using routing context , you can get request and response objects
      routingContext.response().putHeader("content-type", "text/html").end("<h1>Hai!!</h1>");
    });
    //request handling
    httpServer.requestHandler(router);
    //start the server
    httpServer.listen(3000, serverHandler -> {
      if (serverHandler.succeeded()) {
        System.out.println("Server is ready at " + serverHandler.result().actualPort());
      } else {
        System.out.println(serverHandler.cause());
      }
    });
  }


  @Override
  public void start() throws Exception {
    super.start();
    createServer();
  }
}
