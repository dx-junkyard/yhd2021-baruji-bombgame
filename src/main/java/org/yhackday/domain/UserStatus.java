package org.yhackday.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserStatus {
    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("room_id")
    private int roomId;

    @JsonProperty("life")
    private int life;

    @JsonProperty("direction")
    private int direction;
}
