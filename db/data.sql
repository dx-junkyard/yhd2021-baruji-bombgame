INSERT INTO `yhackday`.`ITEM` (`ITEM_NAME`, `ITEM_ACTION`) VALUES ('爆弾A', '大爆発');

INSERT INTO `yhackday`.`ROOM_IMAGE` (`RIGHT_ROOM_IMAGE_URL`, `LEFT_ROOM_IMAGE_URL`, `TOP_ROOM_IMAGE_URL`, `BOTTOM_ROOM_IMAGE_URL`) VALUES ('right_test_url', 'left_test_url', 'top_test_url', 'bottom_test_url');

INSERT INTO `yhackday`.`ROOM` (`RIGHT_ROOM_ID`, `LEFT_ROOM_ID`, `TOP_ROOM_ID`, `BOTTOM_ROOM_ID`, `IS_RIGHT_PARTITION_SET`, `IS_LEFT_PARTITION_SET`, `IS_TOP_PARTITION_SET`, `IS_BOTTOM_PARTITION_SET`, `ITEM_ID`, `ROOM_IMAGE_ID`) VALUES ('2', '3', '4', '5', b'0', b'0', b'0', b'0', '1', '1');

INSERT INTO  `yhackday`.`USER` SET `ROOM_ID` = '1', `LIFE` = '5', `DIRECTION` = '0' WHERE `USER_ID` = '1';

INSERT INTO `yhackday`.`USER_ITEM` (`USER_ID`, `ITEM_ID`) VALUES ('1', '1');