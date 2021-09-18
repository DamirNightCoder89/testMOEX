package com.damirkan.shareservice.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Share {

    private Integer secid;
    private BigDecimal last;
}
