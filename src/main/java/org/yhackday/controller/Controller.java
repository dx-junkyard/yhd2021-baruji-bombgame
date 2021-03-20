package org.yhackday.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yhackday.domain.dto.NextActionDto;
import org.yhackday.domain.dto.UserActionRequestDto;
import org.yhackday.service.TimeKeeperService;
import org.yhackday.service.UserActionService;

@RestController
@RequestMapping("/v1")
@Slf4j
public class Controller {
    private Logger logger = LoggerFactory.getLogger(Controller.class);
    private UserActionService userActionService;
    private TimeKeeperService timeKeeperService;

    @Autowired
    public Controller(UserActionService userActionService, TimeKeeperService timeKeeperService){
        this.userActionService = userActionService;
        this.timeKeeperService = timeKeeperService;
    }

    /**
     * Nターン目のユーザXのアクションを登録して、それによって得られる結果を返す。
     * @param user_id
     * @param userActionRequestDto
     * @return
     */
    @PostMapping("/action/{user_id}")
    @ResponseBody
    public NextActionDto addAccountAction(@PathVariable int user_id, @RequestBody UserActionRequestDto userActionRequestDto){
        logger.info("プレイヤーのアクション登録API: {}", user_id);

        // アクションIDによって処理を変更
        switch(userActionRequestDto.getActionId()){
            case 1: // 前進
                logger.info("User: {}, アクション: {}", user_id, "前進");
                userActionService.addAccountAction(user_id, userActionRequestDto);
                break;
            case 2: // 右向く
                logger.info("User: {}, アクション: {}", user_id, "右向く");
                break;
            case 3: // 左向く
                logger.info("User: {}, アクション: {}", user_id, "左向く");
                break;
            case 4: // 反転
                logger.info("User: {}, アクション: {}", user_id, "反転");
                break;
            case 5: // 左向く
                logger.info("User: {}, アクション: {}", user_id, "爆弾Aの設置");
                break;
        }
        return timeKeeperService.getNextActionInfo(user_id);
    }

    /**
     * 全員のユーザがNターン目のアクションを完了し、N+1ターンに移動可能な状態かを判別する。
     * N+1ターンに移動可能な状態の場合、N+1ターン目で描画する画像、ユーザの情報を返す。
     */
    @GetMapping("/timekeeper/{user_id}")
    @ResponseBody
    public NextActionDto getNextActionInfo(@PathVariable int user_id){
        return timeKeeperService.getNextActionInfo(user_id);
    }
}
