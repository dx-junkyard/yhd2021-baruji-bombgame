# YHackdayのバックエンド

## 概要

本アプリケーションは、2人でボンバーマンのようなゲームをするアプリです。

## QuickStart

コンテナを用いたアプリケーションのビルドは以下のコマンドを用いる。

```sh:
docker-compose build
docker-compose up -d db 
docker-compose up -d backend
```

ワンライナーで実行する場合。

```sh:
mvn clean package; docker-compose build; docker-compose up
```

コンテナの起動後、以下のURLにアクセ薄することでゲームを開始できる。

ユーザ1でログイン
- http://localhost:8080/v1/action/1

ユーザ2でログイン
- http://localhost:8080/v1/action/2


## APIの詳細

### 初期化処理

開始ボタンを押すとゲームが開始できるようにする。  
ゲームに参加するプレイヤーを2人作成する場合は、以下のコマンドを用いる。

`v1/timekeeper/init/{timekeeperId: 固定で1}/player/{参加するユーザ数: 固定で2}`

```sh:
curl -X POST http://127.0.0.1:8080/v1/timekeeper/init/1/player/2
```

### ターンを進めるコマンド

以下のコマンドを実行することで、実行時にプレイヤーの行動選択が完了していた場合、プレイヤーの行動を適応し、ターンを一つ進める。

```sh:
curl -X POST http://127.0.0.1:8080/v1/timekeeper/1
```

`timekeeper.sh`を実行することで、5秒おきに上記のリクエストを送信することができる。

### 行動

プレイヤーの行動に関するAPIを以下に示す。

#### 前進

```sh:
curl -X POST http://127.0.0.1:8080/v1/action/1 -d '{"actionId": 1}' -H "Content-Type: application/json"
```

#### 右向き

```sh:
curl -X POST http://127.0.0.1:8080/v1/action/1 -d '{"actionId": 2}' -H "Content-Type: application/json"
```

#### 左向き

```sh:
curl -X POST http://127.0.0.1:8080/v1/action/1 -d '{"actionId": 3}' -H "Content-Type: application/json"
```

#### 反転向き

```sh:
curl -X POST http://127.0.0.1:8080/v1/action/1 -d '{"actionId": 4}' -H "Content-Type: application/json"
```

#### 爆弾設置

```sh:
curl -X POST http://127.0.0.1:8080/v1/action/1 -d '{"actionId": 5}' -H "Content-Type: application/json"
```
