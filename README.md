# YHackdayのバックエンド

## 構築

コンテナを用いたアプリケーションのビルドは以下のコマンドを用いる。

```sh:
docker-compose build
docker-compose up -d db 
docker-compose up -d backend
```


## Api

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