package com.example.inventory.entity;

import lombok.Data;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Data
@Embeddable
public class Manufacturer {

    @NotEmpty(message = "man_name is mandatory")
    private String man_name;

    @NotEmpty(message = "url is mandatory")
    private String url;

    @NotEmpty(message = "phone is mandatory")
    private String phone;

    public Manufacturer() {}

    public Manufacturer(String man_name, String url, String phone) {
        this.man_name = man_name;
        this.url = url;
        this.phone = phone;
    }

}
