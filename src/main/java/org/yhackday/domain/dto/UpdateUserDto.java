package org.yhackday.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateUserDto {

    @JsonProperty("room_id")
    private Integer roomId;

    @JsonProperty("direction")
    private Integer direction;

    @JsonProperty("now_turn")
    private Integer nowTurn;
}
