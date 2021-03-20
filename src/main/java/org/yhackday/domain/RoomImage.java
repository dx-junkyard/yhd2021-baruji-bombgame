package org.yhackday.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoomImage {
    private String rightRoomImageUrl;
    private String leftRoomImageUrl;
    private String topRoomImageUrl;
    private String bottomRoomImageUrl;
}
