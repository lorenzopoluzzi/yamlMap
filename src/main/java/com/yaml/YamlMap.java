package com.yaml;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class YamlMap {

    @JsonProperty("mp")
    Map<String, Map<String, List<String>>> mp;

    Map<String,List<String>> get(String key) {
        return this.mp.get(key);
    }
}
