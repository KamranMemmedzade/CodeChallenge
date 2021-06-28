package com.bestcommerce.model.product;

import lombok.*;

import java.util.List;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductWrapper {


    private List<Product> productList;

}
