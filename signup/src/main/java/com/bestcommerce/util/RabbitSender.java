package com.bestcommerce.util;

import com.bestcommerce.model.product.Product;
import com.bestcommerce.model.product.ProductWrapper;
import com.bestcommerce.util.Request;
import com.rabbitmq.client.ConnectionFactory;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.net.ConnectException;

@Service
public class RabbitSender {

    private static final String QUEUE_NAME="signup";
    private static final String ID_QUEUE_NAME="products";

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    public Product sendProduct(Product product){
        return  rabbitTemplate
                .convertSendAndReceiveAsType(
                        QUEUE_NAME,product,
                        ParameterizedTypeReference.forType(Product.class)
                );
    }

    public ProductWrapper sendId(Long merchantId,int pageNo,int pageSize,String sortBy){
        Request request=new Request(merchantId,pageNo,pageSize,sortBy);

        ProductWrapper pw=rabbitTemplate
                .convertSendAndReceiveAsType(
                        ID_QUEUE_NAME,request,
                        ParameterizedTypeReference.forType(ProductWrapper.class));
        return  pw;
    }


}
