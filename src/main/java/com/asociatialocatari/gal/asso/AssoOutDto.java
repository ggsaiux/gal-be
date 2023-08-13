package com.asociatialocatari.gal.asso;

import lombok.Data;

@Data
public class AssoOutDto {

    private Long id;

    private String name;

    private String description;

    private String address;

    private String province;

    private String city;

    private String district;
}
