package com.bestcommerce.service;

import com.bestcommerce.exception.EmailAlreadyExistsException;
import com.bestcommerce.util.DTOConverter;
import com.bestcommerce.model.Merchant;
import com.bestcommerce.model.MerchantDTO;
import com.bestcommerce.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final MerchantRepository merchantRepository;

    private final PasswordEncoder passwordEncoder;

    private final DTOConverter dtoConverter;

    public Merchant signUp(MerchantDTO merchantDTO){

        if(!merchantRepository.findAll().isEmpty()) {
            if( merchantRepository.findByEmail(merchantDTO.getEmail()).isPresent()){
                throw new EmailAlreadyExistsException("User already exists");
            }
        }


        Merchant merchant=dtoConverter.convertDtoToEntity(merchantDTO);

        merchant.setPassword(passwordEncoder.encode(merchant.getPassword()));

        merchantRepository.save(merchant);

        return merchant;

    }

}
