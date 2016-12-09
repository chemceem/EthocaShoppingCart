package com.ethoca.shoppingcart;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class EthocaShoppingCartApplicationTests {

	/*@Autowired
	ProductService productService;

	@Autowired
	MockMvc mockMvc;*/

	@Test
	public void contextLoads() {

	}

	/*@Test
	public void checkProducts() throws Exception
	{
		this.mockMvc.perform(get("/proudcts/all").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));

	}*/

}
