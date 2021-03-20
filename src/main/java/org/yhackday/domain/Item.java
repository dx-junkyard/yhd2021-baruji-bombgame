package org.yhackday.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {
    @JsonProperty("item_id")
    private int item_id;

    @JsonProperty("item_name")
    private String item_name;

    @JsonProperty("item_action")
    private String item_action;
}
