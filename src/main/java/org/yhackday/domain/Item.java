package org.yhackday.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {
    @JsonProperty("item_id")
    private int itemId;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("item_action")
    private String itemAction;
}
