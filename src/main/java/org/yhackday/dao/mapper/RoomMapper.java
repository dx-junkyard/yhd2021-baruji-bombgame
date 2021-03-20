package org.yhackday.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.yhackday.domain.Room;

/**
 * ルーム情報取得インターフェース
 */
@Mapper
public interface RoomMapper {
    void setItemOnRoom(int roomId, int itemId);

    Room getRoomInfo(int roomId);
}
