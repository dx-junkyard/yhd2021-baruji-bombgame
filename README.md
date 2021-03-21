# YHackdayのバックエンド

## 構築

コンテナを用いたアプリケーションのビルドは以下のコマンドを用いる。

```sh:
docker-compose build
docker-compose up -d db 
docker-compose up -d backend
```

一気痛感で行う場合

```sh:
mvn clean package; docker-compose build; docker-compose up backend
```


## Api

## 初期化処理

開始ボタンを作成し、そこを押すとゲームが開始できるようにする。
プレイヤーを2人作成する場合は以下のコマンドを用いる。

`v1/timekeeper/init/{timekeeperId: 固定で1}/player/{参加するユーザ数: 固定で2}`

```sh:
curl -X POST http://127.0.0.1:8080/v1/timekeeper/init/1/player/2
```

## ターンを進めるコマンド

```sh:
curl -X POST http://127.0.0.1:8080/v1/timekeeper/1
```

### 前進

```sh:
curl -X POST http://127.0.0.1:8080/v1/action/1 -d '{"actionId": 1}' -H "Content-Type: application/json"
```

### 右向き

```sh:
curl -X POST http://127.0.0.1:8080/v1/action/1 -d '{"actionId": 2}' -H "Content-Type: application/json"
```

### 左向き

```sh:
curl -X POST http://127.0.0.1:8080/v1/action/1 -d '{"actionId": 3}' -H "Content-Type: application/json"
```

### 反転向き

```sh:
curl -X POST http://127.0.0.1:8080/v1/action/1 -d '{"actionId": 4}' -H "Content-Type: application/json"
```

### 爆弾設置

```sh:
curl -X POST http://127.0.0.1:8080/v1/action/1 -d '{"actionId": 5}' -H "Content-Type: application/json"
```