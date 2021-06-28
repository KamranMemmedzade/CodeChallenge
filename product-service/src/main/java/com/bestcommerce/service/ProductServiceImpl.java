package com.bestcommerce.service;

import com.bestcommerce.model.Product;
import com.bestcommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public List<Product> getAllProducts(
            Long merchantId,Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Product> pagedResult = repository.findProductByMerchantId(merchantId,paging)
        .orElseThrow(()->new IllegalStateException(
                String.format("There is no product belong to %s ",merchantId.toString())));

            return pagedResult.getContent();
    }


    public Product saveProduct(Product product) {

        repository.save(product);

        return product;
    }
}
