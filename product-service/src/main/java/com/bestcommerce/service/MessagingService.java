package com.bestcommerce.service;

import com.bestcommerce.model.Product;
import com.bestcommerce.util.ProductWrapper;
import com.bestcommerce.util.Request;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class MessagingService {

    private final ProductService service;

    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = {"signup"})
    public Product saveListener(Product product) {

        service.saveProduct(product);

        return product;
    }

    @RabbitListener(queues = {"products"})
    public ProductWrapper idListener(Request request) {

      List<Product> lst= service.getAllProducts(
              request.getMerchantId(),
              request.getPageNo(),
              request.getPageSize(),
              request.getSortBy());


      return new ProductWrapper(lst);
    }
}
