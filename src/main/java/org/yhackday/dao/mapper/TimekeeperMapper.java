package org.yhackday.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.yhackday.domain.TimeKeeper;

/**
 * タイムキーパー取得インターフェース
 */
@Mapper
public interface TimekeeperMapper {
    int initTimekeeper(int timekeeperId, int limitTurn);
    void incrementNowTurn(int timekeeperId);
    int noActionUsers(int timekeeperId);
    TimeKeeper getNowTurn(int timekeeperId);
    int deleteTimekeeper(int timekeeperId);
}
