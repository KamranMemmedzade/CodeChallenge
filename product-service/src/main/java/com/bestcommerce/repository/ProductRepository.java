package com.bestcommerce.repository;

import com.bestcommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long>
{
   Optional<Page<Product>> findProductByMerchantId(Long id, Pageable pageable);
}
