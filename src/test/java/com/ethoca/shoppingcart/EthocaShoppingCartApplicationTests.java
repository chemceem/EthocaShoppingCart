package com.ethoca.shoppingcart;

import com.ethoca.shoppingcart.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EthocaShoppingCartApplicationTests {

	@Autowired
	ProductService productService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void contextLoads() {

	}

	@Test
	public void checkProducts() throws Exception
	{
		this.mockMvc.perform(get("/proudcts/all").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));

	}

}
