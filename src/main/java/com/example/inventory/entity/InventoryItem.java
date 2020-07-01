package com.example.inventory.entity;

import lombok.Data;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class InventoryItem {

    @Id @GeneratedValue
    private Long id;

    @NotEmpty(message = "name is mandatory")
    private String name;

    @Embedded
    @Valid
    private Manufacturer manufacturer;

    @NotEmpty(message = "releaseDate is mandatory")
    private String releaseDate;

    public InventoryItem() {}

    public InventoryItem(String name, Manufacturer manufacturer, String releaseDate) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.releaseDate = releaseDate;
    }
}
