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

@CrossOrigin
@RestController
@RequestMapping("/v1")
@Slf4j
public class Controller {
    private Logger logger = LoggerFactory.getLogger(Controller.class);
    private UserActionService userActionService;
    private TimeKeeperService timeKeeperService;

    @Autowired
    public Controller(UserActionService userActionService, TimeKeeperService timeKeeperService) {
        this.userActionService = userActionService;
        this.timeKeeperService = timeKeeperService;
    }

    /**
     * Nターン目のユーザXのアクションを登録して、それによって得られる結果を返す。
     *
     * @param userId
     * @param userActionRequestDto
     * @return
     */
    @PostMapping("/action/{userId}")
    @ResponseBody
    public NextActionDto addAccountAction(@PathVariable int userId, @RequestBody UserActionRequestDto userActionRequestDto) {
        logger.info("プレイヤーのアクション登録API: {}", userId);

        NextActionDto nextActionDto = null;
        // アクションIDによって処理を変更
        switch (userActionRequestDto.getActionId()) {
            case 1: // 前進
                logger.info("User: {}, アクション: {}", userId, "前進");
                nextActionDto = userActionService.stepForward(userId);
                break;
            case 2: // 右向く
                logger.info("User: {}, アクション: {}", userId, "右向く");
                nextActionDto = userActionService.changeDirection(userId, 1);
                break;
            case 3: // 左向く
                logger.info("User: {}, アクション: {}", userId, "左向く");
                nextActionDto = userActionService.changeDirection(userId, -1);
                break;
            case 4: // 反転
                logger.info("User: {}, アクション: {}", userId, "反転");
                nextActionDto = userActionService.changeDirection(userId, 2);
                break;
            case 5: // 爆弾をおく
                logger.info("User: {}, アクション: {}", userId, "爆弾Aの設置");
                nextActionDto = userActionService.setItems(userId);
                break;
        }
        return nextActionDto;
    }

    /**
     * 全員のユーザがNターン目のアクションを完了し、N+1ターンに移動可能な状態かを判別する。
     * N+1ターンに移動可能な状態の場合、N+1ターン目で描画する画像、ユーザの情報を返す。
     */
    @GetMapping("/timekeeper/{userId}")
    @ResponseBody
    public NextActionDto getNextActionInfo(@PathVariable int userId) {
        logger.info("User: {}", userId);
        return timeKeeperService.getNextActionInfo(userId);
    }
}
