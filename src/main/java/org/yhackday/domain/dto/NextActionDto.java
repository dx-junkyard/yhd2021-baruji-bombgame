package org.yhackday.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.yhackday.domain.Item;
import org.yhackday.domain.Room;

@Data
public class NextActionDto {
    @JsonProperty("image_url")
    private String image_url;

    @JsonProperty("room")
    private Room room;

    @JsonProperty("item")
    private Item itemEntity;

    @JsonProperty("limit_time")
    private int limit_time;

    @JsonProperty("hit_point")
    private int hitpoint;

    @JsonProperty("alert")
    private int alert;

    @JsonProperty("map")
    private int map;
}
