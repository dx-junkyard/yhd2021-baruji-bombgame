package org.yhackday.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.yhackday.domain.Item;
import org.yhackday.domain.Room;
import org.yhackday.domain.UserStatus;

@Data
public class UserActionDto {
    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("user_status")
    private UserStatus userStatus;

    @JsonProperty("room")
    private Room room;

    @JsonProperty("item")
    private Item item;

    @JsonProperty("limit_time")
    private int limitTime;

    @JsonProperty("hit_point")
    private int hitPoint;

    @JsonProperty("alert")
    private int alert;

    @JsonProperty("map")
    private int map;
}
