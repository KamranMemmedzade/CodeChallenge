package com.bestcommerce.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request implements Serializable {
    private static final Long serialVersionUID=12345L;
    Long merchantId;
    Integer pageNo;
    Integer pageSize;
    String sortBy;
}
