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
    public DummyController(UserActionService userActionService, TimeKeeperService timeKeeperService){
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
    public NextActionDto addAccountAction(@PathVariable long user_id, @RequestBody UserActionRequestDto userActionRequestDto){
        logger.info("プレイヤーのアクション登録API Dummy, id:{} request body:{}", user_id, userActionRequestDto);
        return this.createDemo();
    }

    private NextActionDto createDemo(){
        NextActionDto dummyNextActionDto = new NextActionDto();

        Item item = new Item();
        item.setItem_id(1);
        item.setItem_name("爆弾");
        item.setItem_action("大爆発");

        Room room = new Room();
        room.setRoomId(1);
        room.setTop_room_id(1);
        room.setBottom_room_id(2);
        room.setRight_room_id(3);
        room.setLeft_room_id(4);
        room.set_top_partition_set(false);
        room.set_bottom_partition_set(false);
        room.set_right_partition_set(false);
        room.set_left_partition_set(false);

        dummyNextActionDto.setItemEntity(item);
        dummyNextActionDto.setRoom(room);

        return dummyNextActionDto;
    }
}
