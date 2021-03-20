package org.yhackday.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserActionRequestDto {
//
//    @JsonProperty("room_id")
//    private String room_id;
//
//    @JsonProperty("direction")
//    private int direction;
//
//    @JsonProperty("now_turn")
//    private int now_turn;

    @JsonProperty("actionId")
    private int actionId;

}
