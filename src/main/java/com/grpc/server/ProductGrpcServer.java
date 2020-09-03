package com.grpc.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ProductGrpcServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Server server = ServerBuilder.forPort(8080)
				.addService(new ProductServiceImpl())
				.build();
		
		System.out.println("Starting server....");
		server.start();
		System.out.println("Server started and listening at port:"+server.getPort());
		server.awaitTermination();
	}

}
