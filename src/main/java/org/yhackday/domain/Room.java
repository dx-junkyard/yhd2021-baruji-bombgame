package org.yhackday.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Room {
    private Integer roomId;

    @JsonProperty("")
    private Integer rightRoomId;

    private Integer leftRoomId;

    private Integer topRoomId;

    private Integer bottomRoomId;

    private boolean rightPartition;

    private boolean leftPartition;

    private boolean topPartition;

    private boolean bottomPartition;

    private Integer itemId;

    private Integer explosionValue;

    private Integer explosionDirection;
}
