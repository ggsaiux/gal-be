package com.asociatialocatari.gal.apart;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApartDto {

    private Long id;

    private Integer number;

    private String firstName;

    private String lastName;

    private Integer tenantsNumber;

    private BigDecimal m2;

    private Long idBuildStair;

}
