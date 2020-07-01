package com.example.inventory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
public class InventoryController_getInventoryItemByIdTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private InventoryController inventoryController;

	@Test
	public void getAll_expect_Status200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/inventory")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void getInventoryItemById_expect_Status200() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/inventory/{id}", 1)
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void getInventoryItemById_badURL_expect_Status404() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/inventory/badurl/{id}", 1)
				.contentType("application/json"))
				.andExpect(status().isNotFound());
	}
}