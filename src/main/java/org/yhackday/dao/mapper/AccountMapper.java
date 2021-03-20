package org.yhackday.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.yhackday.domain.RoomImage;
import org.yhackday.domain.dto.NextActionDto;
import org.yhackday.domain.dto.UserActionDto;
import org.yhackday.domain.dto.UserActionRequestDto;

@Mapper
public interface AccountMapper {
    void updateUserRoom(int userId, int roomId);
    UserActionDto getUserStatusInfo(int userId);
    RoomImage getImageUrl(int roomId);
}
