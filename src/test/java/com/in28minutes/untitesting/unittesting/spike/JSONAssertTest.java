package com.in28minutes.untitesting.unittesting.spike;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JSONAssertTest {

    String actualResponse = "{\"id\": 1, \"name\": \"Ball\", \"price\": 10, \"quantity\": 100}";

    @Test
    public void jsonAssert_StrictTrue_ExactMatchExceptForSpaces() throws JSONException {
        String expectedResponse = "{\"id\": 1, \"name\": \"Ball\", \"price\": 10, \"quantity\": 100}";
        // when strict is set to true, adding or deleting spaces won't make a difference, but all fields must be present
        JSONAssert.assertEquals(expectedResponse, actualResponse, true);
    }

    @Test
    public void jsonAssert_StrictFalse() throws JSONException {
        String expectedResponse = "{\"id\": 1, \"name\": \"Ball\"}";
        // here, strict is set to false, so only the fields that are present will be asserted
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    @Test
    public void jsonAssert_WithoutEscapeCharacters() throws JSONException {
        // when strict is set to false, you also do not need escape characters for your assert
        // unless your response value has a space in it (i.e. name: Red Ball --> would need quotes and therefore escape characters)
        String expectedResponse = "{id: 1, name: Ball}";
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }
}
