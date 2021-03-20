package org.yhackday.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Room {
    @JsonProperty("room_id")
    private long roomId;

    @JsonProperty("right_room_id")
    private long right_room_id;

    @JsonProperty("left_room_id")
    private long left_room_id;

    @JsonProperty("top_room_id")
    private long top_room_id;

    @JsonProperty("bottom_room_id")
    private long bottom_room_id;

    @JsonProperty("is_right_partition_set")
    private boolean is_right_partition_set;

    @JsonProperty("is_left_partition_set")
    private boolean is_left_partition_set;

    @JsonProperty("is_top_partition_set")
    private boolean is_top_partition_set;

    @JsonProperty("is_bottom_partition_set")
    private boolean is_bottom_partition_set;
}
