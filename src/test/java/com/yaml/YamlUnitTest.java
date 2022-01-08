package com.yaml;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;

public class YamlUnitTest {
    private ObjectMapper mapper;
    private File orderOutput;

    @Before
    public void setup() {
        mapper = new ObjectMapper(new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER));
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        orderOutput = new File("src/test/resources/yaml/orderOutput.yaml");
    }
    
    @After
    public void cleanup() {
        orderOutput.deleteOnExit();
    }

    @Test
    public void givenYamlInput_ObjectCreated() throws JsonParseException, JsonMappingException, IOException {
        mapper = new ObjectMapper(new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER));
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        orderOutput = new File("src/test/resources/yaml/orderOutput.yaml");
        Order order = mapper.readValue(new File("src/test/resources/yaml/orderInput.yaml"), Order.class);
        assertEquals("A001", order.getOrderNo());
        assertEquals("Customer, Joe", order.getCustomerName());
        assertEquals(2, order.getOrderLines()
            .size());
    }

    @Test
    public void givenYamlMap() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        YamlMap ym2 = mapper.readValue(new File("src/test/resources/yaml/props.yml"), YamlMap.class);
        System.out.println(ym2.toString());
    }
}
