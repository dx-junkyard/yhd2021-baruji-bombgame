package org.yhackday.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Room {
    @JsonProperty("room_id")
    private int roomId;

    @JsonProperty("right_room_id")
    private int rightRoomId;

    @JsonProperty("left_room_id")
    private int leftRoomId;

    @JsonProperty("top_room_id")
    private int topRoomId;

    @JsonProperty("bottom_room_id")
    private int bottomRoomId;

    @JsonProperty("is_right_partition_set")
    private boolean rightPartition;

    @JsonProperty("is_left_partition_set")
    private boolean leftPartition;

    @JsonProperty("is_top_partition_set")
    private boolean topPartition;

    @JsonProperty("is_bottom_partition_set")
    private boolean bottomPartition;
}
