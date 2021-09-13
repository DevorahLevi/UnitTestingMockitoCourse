package com.in28minutes.untitesting.unittesting.controller;

import com.in28minutes.untitesting.unittesting.business.ItemBusinessService;
import com.in28minutes.untitesting.unittesting.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemBusinessService businessService;

    @Test
    public void dummyItem_basic() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                // you can technically leave a field out (i.e. quantity) when using .json() - it'll only check for the fields you specify
                // JSONAssert.assertEquals(expected, actual / result.getResponse().getContentAsString(), (strict) false); --> This is what is being called under the hood for the .json()
                .andExpect(content().json("{\"id\": 1, \"name\": \"Ball\", \"price\": 10, \"quantity\": 100}"))
                .andReturn();
    }

    @Test
    public void itemFromBusinessService_basic() throws Exception {
        when(businessService.retrieveHardCodedItem()).thenReturn(new Item(2, "Item2", 10, 100));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 2, name: Item2, price: 10}"))
                .andReturn();
    }
}