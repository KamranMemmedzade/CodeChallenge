package com.bestcommerce.controller;

import com.bestcommerce.model.Merchant;
import com.bestcommerce.model.product.Product;
import com.bestcommerce.model.product.ProductWrapper;
import com.bestcommerce.service.MerchantService;
import com.bestcommerce.util.RabbitSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RestController
@RequiredArgsConstructor
public class MerchantController {

    private final RabbitSender rabbitSender;

    private  final MerchantService merchantService;

    @PostMapping(value = "/saveproduct")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {

        if(product.getInventory()<5){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Products inventory should be at least 5");
        }
        else {
            product.setMerchantId(currentLoggedInUser().getId());


            return ResponseEntity.status(HttpStatus.OK).body(rabbitSender.sendProduct(product));
        }
    }

    @GetMapping("/merchant/products")
    public ResponseEntity<?> getMerchantProducts(
            @RequestParam(defaultValue = "0") Long merchantId,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){

        merchantId = currentLoggedInUser().getId();

        if (sortBy.equals("unitPrice") || sortBy.equals("inventory") || sortBy.equals("id")) {
            ProductWrapper productWrapper=rabbitSender.sendId(merchantId,pageNo,pageSize,sortBy);

            return ResponseEntity.status(HttpStatus.OK).body(productWrapper);
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Products cannot be listed by "+sortBy);
        }

    }

    private Merchant currentLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser=auth.getName();
        return merchantService.findByName(currentUser);
    }
}