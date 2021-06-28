package com.bestcommerce.util;

import com.bestcommerce.model.Product;
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
