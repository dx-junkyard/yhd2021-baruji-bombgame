package org.yhackday.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.yhackday.domain.Room;

/**
 * ルーム情報取得インターフェース
 */
@Mapper
public interface RoomMapper {
    void setItemOnRoom(int roomId, int itemId);
    void setExplosion(); // itemが1じゃないroomのitemの経過ターン数を-1する
    void decrementExplosionCount(); // ターンの終了時に爆発の威力を減退させる
    void decrementItemCount(); // 爆発カウント(item_set_count=0のitemのexplosion_valueを5にする。)
    Room getRoomInfo(int roomId);
}
