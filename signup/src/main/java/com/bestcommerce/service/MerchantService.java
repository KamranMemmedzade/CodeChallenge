package com.bestcommerce.service;


import com.bestcommerce.model.Merchant;


public interface MerchantService {

    public Merchant findByName(String name);
}
