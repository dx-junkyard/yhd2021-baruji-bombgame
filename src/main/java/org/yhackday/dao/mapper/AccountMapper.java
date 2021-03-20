package org.yhackday.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.yhackday.domain.RoomImage;
import org.yhackday.domain.dto.UpdateUserDto;
import org.yhackday.domain.dto.UserActionDto;

@Mapper
public interface AccountMapper {
    void updateUserStatus(int userId, UpdateUserDto updateUserDto);

    UserActionDto getUserStatusInfo(int userId);

    RoomImage getImageUrl(int roomId);
}
