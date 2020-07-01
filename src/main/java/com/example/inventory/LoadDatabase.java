package com.example.inventory;

import com.example.inventory.entity.InventoryItem;
import com.example.inventory.entity.Manufacturer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.List;

/**
 * Loads a dummy database of InventoryItem objects on app startup
 *
 */
@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(InventoryRepository repository) {

        Manufacturer manufacturer = new Manufacturer("Big Fat Corp", "666-666", "http://www.bigfatcorp.com");

        List<InventoryItem> list = Arrays.asList(
                new InventoryItem("apple", manufacturer, "10/10/20"),
                new InventoryItem("pair", manufacturer, "11/10/20"),
                new InventoryItem("coconut", manufacturer, "12/10/20"),
                new InventoryItem("banana", manufacturer, "13/10/20"),
                new InventoryItem("orange", manufacturer, "14/10/20"),
                new InventoryItem("lemon", manufacturer, "15/10/20"),
                new InventoryItem("pineapple", manufacturer, "16/10/20"),
                new InventoryItem("peach", manufacturer, "17/10/20"),
                new InventoryItem("colliflower", manufacturer, "18/10/20"),
                new InventoryItem("tangerine", manufacturer, "19/10/20"));

        return args -> {
            log.info("Preloading yummy InventoryItem data into H2 in-memory database" + repository.saveAll(list));
        };
    }
}