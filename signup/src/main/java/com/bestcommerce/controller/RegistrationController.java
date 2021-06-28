package com.bestcommerce.controller;


import com.bestcommerce.model.Merchant;
import com.bestcommerce.model.MerchantDTO;
import com.bestcommerce.model.RegistrationResponse;
import com.bestcommerce.service.MerchantService;
import com.bestcommerce.service.RegistrationService;
import com.bestcommerce.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Validated
public class RegistrationController {

    private final RegistrationService registrationService;

    private final MerchantService merchantService;

    private final DTOConverter dtoConverter;

    private final ModelMapper modelMapper;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody MerchantDTO merchantDTO, BindingResult result){

        Merchant merchant= registrationService.signUp(merchantDTO);

      //  merchantDTO=dtoConverter.convertEntityToDto((merchant));

        RegistrationResponse registrationResponse=modelMapper.map(merchant, RegistrationResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(registrationResponse);


    }
}






