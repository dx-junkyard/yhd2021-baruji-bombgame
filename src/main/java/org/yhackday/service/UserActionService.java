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
import org.yhackday.domain.RoomImage;
import org.yhackday.domain.TimeKeeper;
import org.yhackday.domain.dto.NextActionDto;
import org.yhackday.domain.dto.UpdateUserDto;
import org.yhackday.domain.dto.UserActionDto;

@Service
public class UserActionService {
    private Logger logger = LoggerFactory.getLogger(Controller.class);

    private AccountMapper accountMapper;

    private RoomMapper roomMapper;

    private TimekeeperMapper timekeeperMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserActionService(AccountMapper accountMapper, RoomMapper roomMapper, TimekeeperMapper timekeeperMapper) {
        this.accountMapper = accountMapper;
        this.roomMapper = roomMapper;
        this.timekeeperMapper = timekeeperMapper;
    }

    /**
     * 前進する処理
     *
     * @param userId
     * @return
     */
    public NextActionDto stepForward(int userId) {
        UserActionDto nowUserStatus = accountMapper.getUserStatusInfo(userId);

//        if(isTurnActionDone(nowUserStatus)){
//            return modelMapper.map(nowUserStatus, NextActionDto.class);
//        }

        int nowDirection = nowUserStatus.getUserStatus().getDirection();

        // 進みたい方向が壁の時
        if (nowDirection == 0 && nowUserStatus.getRoom().getTopRoomId() == null) {
            logger.info("前は壁のため進めません: {}", nowUserStatus);
            return modelMapper.map(nowUserStatus, NextActionDto.class);
        } else if (nowDirection == 1 && nowUserStatus.getRoom().getRightRoomId() == null) {
            logger.info("右方向は壁のため進めません: {}", nowUserStatus);
            return modelMapper.map(nowUserStatus, NextActionDto.class);
        } else if (nowDirection == 3 && nowUserStatus.getRoom().getLeftRoomId() == null) {
            logger.info("左方向は壁のため進めません: {}", nowUserStatus);
            return modelMapper.map(nowUserStatus, NextActionDto.class);
        } else if (nowDirection == 2 && nowUserStatus.getRoom().getBottomRoomId() == null) {
            logger.info("後方向は壁のため進めません: {}", nowUserStatus);
            return modelMapper.map(nowUserStatus, NextActionDto.class);
        }

        int nextRoomId = 0;
        switch (nowDirection) {
            case 0: // 前
                nextRoomId = nowUserStatus.getRoom().getTopRoomId();
                break;
            case 1: // 右
                nextRoomId = nowUserStatus.getRoom().getRightRoomId();
                break;
            case 2: // 後ろ
                nextRoomId = nowUserStatus.getRoom().getBottomRoomId();
                break;
            case 3: // 左
                nextRoomId = nowUserStatus.getRoom().getLeftRoomId();
                break;
        }
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setRoomId(nextRoomId);

        return modelMapper.map(updateUserStatus(userId, updateUserDto), NextActionDto.class);

    }

    /**
     * 向きを変更する処理
     *
     * @param userId
     * @param changeDirectionNumber
     * @return
     */
    public NextActionDto changeDirection(int userId, int changeDirectionNumber) {
        UserActionDto nowUserStatus = accountMapper.getUserStatusInfo(userId);

//        if(isTurnActionDone(nowUserStatus)){
//            return modelMapper.map(nowUserStatus, NextActionDto.class);
//        }

        // 1周回ったら向きを修正する
        int directionNumber = nowUserStatus.getUserStatus().getDirection();
        directionNumber += changeDirectionNumber;
        directionNumber = directionNumber > 3 ? directionNumber - 4 : directionNumber;
        directionNumber = directionNumber < 0 ? directionNumber + 4 : directionNumber;

        // ユーザの向き情報を変更する。
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setDirection(directionNumber);

        return modelMapper.map(this.updateUserStatus(userId, updateUserDto), NextActionDto.class);
    }


    /**
     * アイテムを設置する。(現状は爆弾のみ)
     *
     * @param userId
     * @return
     */
    public NextActionDto setItems(int userId) {
        UserActionDto nowUserStatus = accountMapper.getUserStatusInfo(userId);

//        if(isTurnActionDone(nowUserStatus)){
//            return modelMapper.map(nowUserStatus, NextActionDto.class);
//        }

        // Itemが設置されていない時、ItemをRoomに設置する
        if (nowUserStatus.getRoomItems().getItemId() == 0) {
            logger.debug("itemの設置");
            roomMapper.setItemOnRoom(nowUserStatus.getRoom().getRoomId(), 1);
        } else {
            logger.debug("何もしない");
        }

        UserActionDto nextUserStatus = accountMapper.getUserStatusInfo(userId);

        // 表示する画像のURLは別処理で取得する。
        String url = this.getImageUrl(nextUserStatus);
        nextUserStatus.setImageUrl(url);

        // accountMapper.updateUserTurn(userId);

        return modelMapper.map(nextUserStatus, NextActionDto.class);
    }


    /**
     * ユーザの情報を更新しレスポンスボディを返す。
     *
     * @param userId
     * @param updateUserDto
     * @return
     */
    private UserActionDto updateUserStatus(int userId, UpdateUserDto updateUserDto) {
        // ユーザ情報のアップデート
        accountMapper.updateUserStatus(userId, updateUserDto);
        UserActionDto nextUserStatus = accountMapper.getUserStatusInfo(userId);

        // 表示する画像のURLは別処理で取得する。
        String url = this.getImageUrl(nextUserStatus);
        nextUserStatus.setImageUrl(url);

        // accountMapper.updateUserTurn(userId);
        logger.info("Next User Status, id: {}, status", userId, nextUserStatus);
        return nextUserStatus;
    }

    /**
     * 自分が向いている先の画像のURLを取得する処理
     *
     * @param userActionDto
     * @return
     */
    private String getImageUrl(UserActionDto userActionDto) {
        String imageUrl = "";

        RoomImage roomImage = accountMapper.getImageUrl(userActionDto.getRoom().getRoomId());
        switch (userActionDto.getUserStatus().getDirection()) {
            case 0: // 前
                imageUrl = roomImage.getTopRoomImageUrl();
                break;
            case 1: // 右
                imageUrl = roomImage.getRightRoomImageUrl();
                break;
            case 2: // 後
                imageUrl = roomImage.getBottomRoomImageUrl();
                break;
            case 3: // 左
                imageUrl = roomImage.getLeftRoomImageUrl();
                break;
        }
        logger.debug("roomImage Info: {}", roomImage);
        return imageUrl;
    }

    /**
     * そのターンの行動の選択が終了したことを確認
     *
     * @param userActionDto
     * @return
     */
    private boolean isTurnActionDone(UserActionDto userActionDto) {
        int timekeeperId = 1;
        TimeKeeper nowTurn = timekeeperMapper.getNowTurn(timekeeperId);

        if (nowTurn.getNowTurn() == userActionDto.getUserStatus().getNowTurn()) { // そのターンの行動が完了した時
            return true;
        } else {
            return false;
        }
    }
}
