package org.yhackday.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.yhackday.domain.RoomImage;
import org.yhackday.domain.UserStatus;
import org.yhackday.domain.dto.UpdateUserDto;
import org.yhackday.domain.dto.UserActionDto;

import java.util.List;

@Mapper
public interface AccountMapper {
    int initUser(UserStatus userStatus);

    int initUserImage(int userId, int itemId);

    void updateUserStatus(int userId, UpdateUserDto updateUserDto);

    void updateUserLife(); //ユーザがいる部屋で爆発が起きている(explosion_value>0)の時、ユーザのライフを減らす。

    UserActionDto getUserStatusInfo(int userId);

    RoomImage getImageUrl(int roomId);

    int countAliveUser(); // 生存しているユーザ一覧を取得

    int deleteAll();

    int deleteUserItemAll();
}
