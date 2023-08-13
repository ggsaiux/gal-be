package com.asociatialocatari.gal.asso;

import lombok.Data;

@Data
public class AssoInDto {

    private Long id;

    private String name;

    private String description;

    private String address;

    private long province;

    private long city;

    private long district;
}
