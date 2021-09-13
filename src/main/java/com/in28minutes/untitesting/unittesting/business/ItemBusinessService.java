package com.in28minutes.untitesting.unittesting.business;

import com.in28minutes.untitesting.unittesting.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemBusinessService {
    public Item retrieveHardCodedItem() {
        return new Item(1, "Ball", 10, 100);
    }
}
