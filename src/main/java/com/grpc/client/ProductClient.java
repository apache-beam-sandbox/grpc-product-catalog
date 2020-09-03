package com.grpc.client;

import java.util.Date;
import java.util.Iterator;
import java.util.PropertyResourceBundle;

import com.deloitte.product.stubs.Product;
import com.deloitte.product.stubs.ProductRequest;
import com.deloitte.product.stubs.ProductResponse;
import com.deloitte.product.stubs.ProductServiceGrpc;
import com.deloitte.product.stubs.ProductServiceGrpc.ProductServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProductClient {

	public static void main(String[] args) {

		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 8080)
				.usePlaintext()
				.build();
		ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(channel);
		ProductRequest request = ProductRequest.newBuilder()
				.setInput(Product.newBuilder().setName("Chair")
						.setType("Furniture").build())
				.build();
		
		long startTime = System.currentTimeMillis();
		System.out.println("Sending product search request at:"+new Date());
		Iterator<ProductResponse> responseIterator = stub.searchProductCatalog(request);
		System.out.println("Received product search response at:"+new Date());
		long endTime = System.currentTimeMillis();
		while(responseIterator.hasNext()) {
			ProductResponse pr = responseIterator.next();
			System.out.println("Response from server:"+pr.getOutput().getName()+":"+pr.getOutput().getPrice());
		}
		
		System.out.println("Time elapsed in Grpc call:"+(endTime-startTime)+" milli seconds");
		channel.shutdown();
	}

}
