package com.grpc.server;
import com.deloitte.product.stubs.Product;
import com.deloitte.product.stubs.ProductRequest;
import com.deloitte.product.stubs.ProductResponse;
import com.deloitte.product.stubs.ProductServiceGrpc;

import io.grpc.stub.StreamObserver;

public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

	@Override
	public void searchProductCatalog(ProductRequest request
			,StreamObserver<ProductResponse> responseObserver) {
		
		Product product = Product.newBuilder()
								 .setName("Chair")
								 .setType("Furniture")
								 .setPrice(1500)
								 .build();
		for(int i=0;i<10000;i++) {
			
			if(request.getInput().getName().contentEquals("Chair")
					&& request.getInput().getType().contentEquals("Furniture")) {
				
			responseObserver.onNext(ProductResponse.newBuilder()
							.setOutput(product)
							.build());
			}
		}
		
		responseObserver.onCompleted();
	}
}
