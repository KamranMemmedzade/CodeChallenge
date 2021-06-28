package com.bestcommerce.util;

import com.bestcommerce.model.Merchant;
import com.bestcommerce.model.MerchantDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DTOConverter {


    private final ModelMapper modelMapper;

    public Merchant convertDtoToEntity(MerchantDTO merchantDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper.map(merchantDTO,Merchant.class);
    }


    public MerchantDTO convertEntityToDto(Merchant merchant){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper.map(merchant,MerchantDTO.class);
    }
}
