package org.yhackday.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yhackday.controller.Controller;
import org.yhackday.dao.mapper.AccountMapper;
import org.yhackday.dao.mapper.RoomMapper;
import org.yhackday.dao.mapper.TimekeeperMapper;
import org.yhackday.domain.UserStatus;
import org.yhackday.domain.dto.NextActionDto;
import org.yhackday.domain.dto.UserActionDto;

@Service
public class TimeKeeperService {
    private Logger logger = LoggerFactory.getLogger(Controller.class);

    private AccountMapper accountMapper;

    private TimekeeperMapper timekeeperMapper;

    private RoomMapper roomMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TimeKeeperService(AccountMapper accountMapper, RoomMapper roomMapper, TimekeeperMapper timekeeperMapper) {
        this.accountMapper = accountMapper;
        this.roomMapper = roomMapper;
        this.timekeeperMapper = timekeeperMapper;
    }

    public NextActionDto getNextActionInfo(int user_id) {
        UserActionDto userActionDto = accountMapper.getUserStatusInfo(user_id);
        return modelMapper.map(userActionDto, NextActionDto.class);
    }

    /**
     * ターン情報を更新する
     */
    public void nextTurn(int turnId) {
        // if (timekeeperMapper.noActionUsers(1) != -1) {
        logger.info("全員の入力が完了したので、ターンを1つ進めます。");
        timekeeperMapper.incrementNowTurn(turnId);
        // 爆発処理
        roomMapper.setExplosion();

        // ライフを減らす
        accountMapper.updateUserLife();
        // Itemの経過時間を減らす
        roomMapper.decrementItemCount();

        // 爆発の威力の減退
        roomMapper.decrementExplosionCount();
        if (accountMapper.countAliveUser() == 0) { // 全員死亡の時
            this.endGame(1);
        }
        // } else {
        //     logger.info("入力が完了しないのでターンを進めません。");
        // }
    }

    /**
     * ゲームの開始処理
     */
    public void startGame(int timekeeperId, int playerCounts) {
        // 初期化処理をする。
        this.endGame(timekeeperId);
        // 指定した人数分だけユーザを作成する。
        for (int i = 1; i <= playerCounts; i++) {
            UserStatus userStatus = new UserStatus();
            userStatus.setUserId(i);
            userStatus.setLife(10);
            userStatus.setDirection(0);
            userStatus.setRoomId(i);
            userStatus.setNowTurn(0);

            accountMapper.initUser(userStatus);
            accountMapper.initUserImage(i, 1); // 全員に最初は爆弾を持たせる。
        }
        // Timeキーバーを作成する
        timekeeperMapper.initTimekeeper(timekeeperId, 20);
        logger.info("ユーザの作成完了");
    }

    /**
     * ゲームの終了処理
     */
    private void endGame(int timekeeperId) {
        logger.info("アカウントとタイムキーパーを初期化します");
        accountMapper.deleteUserItemAll();
        accountMapper.deleteAll();
        timekeeperMapper.deleteTimekeeper(timekeeperId);
    }
}
