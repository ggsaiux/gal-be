package com.asociatialocatari.gal.base.models;

import lombok.Data;

@Data
public class AntTableColumn {
    public AntTableColumn(String title, String dataIndex, String key) {
        this.title = title;
        this.dataIndex = dataIndex;
        this.key = key;
    }

    private String title;

    private String dataIndex;

    private String key;

}
