package org.yhackday.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yhackday.domain.Item;
import org.yhackday.domain.Room;
import org.yhackday.domain.dto.NextActionDto;
import org.yhackday.domain.dto.UserActionRequestDto;
import org.yhackday.service.TimeKeeperService;
import org.yhackday.service.UserActionService;

@RestController
@RequestMapping("/dummy")
@Slf4j
public class DummyController {
    private Logger logger = LoggerFactory.getLogger(DummyController.class);
    private UserActionService userActionService;
    private TimeKeeperService timeKeeperService;

    @Autowired
    public DummyController(UserActionService userActionService, TimeKeeperService timeKeeperService) {
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
    public NextActionDto addAccountAction(@PathVariable long userId, @RequestBody UserActionRequestDto userActionRequestDto) {
        logger.info("プレイヤーのアクション登録API Dummy, id:{} request body:{}", userId, userActionRequestDto);
        return this.createDemo();
    }

    private NextActionDto createDemo() {
        NextActionDto dummyNextActionDto = new NextActionDto();

        Item item = new Item();
        item.setItemId(1);
        item.setItemName("爆弾");
        item.setItemAction("大爆発");

        Room room = new Room();
        room.setRoomId(1);
        room.setTopRoomId(1);
        room.setBottomRoomId(2);
        room.setRightRoomId(3);
        room.setLeftRoomId(4);
        room.setTopPartition(false);
        room.setBottomPartition(false);
        room.setRightPartition(false);
        room.setLeftPartition(false);

        dummyNextActionDto.setUserItems(item);
        dummyNextActionDto.setRoom(room);

        return dummyNextActionDto;
    }
}
