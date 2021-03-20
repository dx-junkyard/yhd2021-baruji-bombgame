CREATE TABLE if not exists ROOM_IMAGE(
    ROOM_IMAGE_ID INT AUTO_INCREMENT PRIMARY KEY,
    RIGHT_ROOM_IMAGE_URL NVARCHAR(1000),
    LEFT_ROOM_IMAGE_URL NVARCHAR(1000),
    TOP_ROOM_IMAGE_URL NVARCHAR(1000),
    BOTTOM_ROOM_IMAGE_URL NVARCHAR(1000)
);

CREATE TABLE if not exists ITEM(
    ITEM_ID INT AUTO_INCREMENT PRIMARY KEY,
    ITEM_NAME NVARCHAR(1000),
    ITEM_ACTION NVARCHAR(1000)
);

CREATE TABLE if not exists ROOM(
    ROOM_ID INT AUTO_INCREMENT PRIMARY KEY,
    RIGHT_ROOM_ID INT,
    LEFT_ROOM_ID INT,
    TOP_ROOM_ID INT,
    BOTTOM_ROOM_ID INT,
    IS_RIGHT_PARTITION_SET BIT,
    IS_LEFT_PARTITION_SET BIT,
    IS_TOP_PARTITION_SET BIT,
    IS_BOTTOM_PARTITION_SET BIT,
    ITEM_ID INT,
    ROOM_IMAGE_ID INT,
    FOREIGN KEY (ITEM_ID) REFERENCES ITEM(ITEM_ID),
    FOREIGN KEY (ROOM_IMAGE_ID) REFERENCES ROOM_IMAGE(ROOM_IMAGE_ID)
);

ALTER TABLE ROOM
ADD MAP_IMAGE_ID INT;

ALTER TABLE ROOM
ADD Explosion_Value INT;

CREATE TABLE if not exists USER(
    USER_ID INT AUTO_INCREMENT PRIMARY KEY,
    ROOM_ID int,
    LIFE int,
    DIRECTION int
);

CREATE TABLE if not exists USER_ITEM(
    USER_ID INT,
    ITEM_ID INT,
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (ITEM_ID) REFERENCES ITEM(ITEM_ID)
);
