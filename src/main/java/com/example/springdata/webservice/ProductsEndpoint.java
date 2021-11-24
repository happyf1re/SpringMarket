package com.example.springdata.webservice;

import com.example.springdata.entity.Product;
import com.example.springdata.service.ProductService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class ProductsEndpoint {

    public static final String NAMESPACE_URL = "http://polozov.com/springeshop/ws/products";

    private final ProductService productService;

    public ProductsEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getGreeting(@RequestPayload GetProductsRequest request)
            throws DatatypeConfigurationException {
        GetProductsResponse response = new GetProductsResponse();
        productService.getAllProduct()
                .forEach(product -> response.getProducts().add(createProductWS(product)));
        return response;
    }

    private ProductsWS createProductWS(Product product){
        ProductsWS ws = new ProductsWS();
        ws.setId(product.getId());
        ws.setPrice(product.getPrice());
        ws.setTitle(product.getTitle());
        return ws;
    }
}
