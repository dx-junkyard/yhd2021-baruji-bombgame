package org.yhackday.service;

import org.apache.ibatis.jdbc.Null;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yhackday.controller.Controller;
import org.yhackday.dao.mapper.AccountMapper;
import org.yhackday.domain.RoomImage;
import org.yhackday.domain.dto.NextActionDto;
import org.yhackday.domain.dto.UserActionDto;
import org.yhackday.domain.dto.UserActionRequestDto;

@Service
public class UserActionService {
    private Logger logger = LoggerFactory.getLogger(Controller.class);

    private AccountMapper accountMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserActionService(AccountMapper accountMapper){
        this.accountMapper = accountMapper;
    }

    /**
     * Nターン目のアカウントの状態を保存する
     */
    public NextActionDto updateUserAction(int userId){
        UserActionDto nowUserStatus = accountMapper.getUserStatusInfo(userId);

        int nowDirection = nowUserStatus.getUserStatus().getDirection();

        // 進みたい方向が壁の時
        if(nowDirection == 0 && nowUserStatus.getRoom().getTopRoomId()==0){
            logger.info("前は壁のため進めません: {}", nowUserStatus);
            return modelMapper.map(nowUserStatus, NextActionDto.class);
        }else if (nowDirection == 1 && nowUserStatus.getRoom().getRightRoomId()==0){
            logger.info("右方向は壁のため進めません: {}", nowUserStatus);
            return modelMapper.map(nowUserStatus, NextActionDto.class);
        }else if (nowDirection == 2 && nowUserStatus.getRoom().getLeftRoomId()==0){
            logger.info("左方向は壁のため進めません: {}", nowUserStatus);
            return modelMapper.map(nowUserStatus, NextActionDto.class);
        }else if (nowDirection == 3 && nowUserStatus.getRoom().getBottomRoomId()==0){
            logger.info("後方向は壁のため進めません: {}", nowUserStatus);
            return modelMapper.map(nowUserStatus, NextActionDto.class);
        }

        int nextRoomId = 0;
        switch(nowDirection){
            case 0: // 前
                nextRoomId = nowUserStatus.getRoom().getTopRoomId();
                break;
            case 1: // 右
                nextRoomId = nowUserStatus.getRoom().getRightRoomId();
                break;
            case 2: // 左
                nextRoomId = nowUserStatus.getRoom().getLeftRoomId();
                break;
            case 3: // 後
                nextRoomId = nowUserStatus.getRoom().getBottomRoomId();
                break;
        }
        accountMapper.updateUserRoom(userId, nextRoomId);
        UserActionDto nextUserStatus = accountMapper.getUserStatusInfo(userId);

        // 表示する画像のURLは別処理で取得する。
        String url = this.getImageUrl(nextUserStatus);
        logger.info("URL: {}", url);
        nextUserStatus.setImageUrl(url);

        logger.info("前進完了: {}", nextUserStatus);
        return modelMapper.map(nextUserStatus, NextActionDto.class);
    }

    /**
     * 自分が向いている先の画像のURLを取得する処理
     * @param userActionDto
     * @return
     */
    private String getImageUrl(UserActionDto userActionDto){
        String imageUrl = "";

        RoomImage roomImage = accountMapper.getImageUrl(userActionDto.getRoom().getRoomId());
        switch (userActionDto.getUserStatus().getDirection()){
            case 0: // 前
                imageUrl = roomImage.getTopRoomImageUrl();
                break;
            case 1: // 右
                imageUrl = roomImage.getRightRoomImageUrl();
                break;
            case 2: // 左
                imageUrl = roomImage.getLeftRoomImageUrl();
                break;
            case 3: // 後
                imageUrl = roomImage.getBottomRoomImageUrl();
                break;
        }
        logger.debug("roomImage Info: {}", roomImage);
        return imageUrl;
    }
}
