# limon-server
limon系统的服务器配置

## 部署
### 如何docker部署
```shell
docker run -d \
--name limon-server \
-p 8085:8085 \
-e SPRING_PROFILES_ACTIVE="prod" \
-v /data/java/limon/config/:/data/java/limon/config/ \
-v /var/logs/java/limon:/var/log/java/limon \
aircjm/limon-server:1.0.0
```


### 单独部署mysql
```shell
docker run -it \
--name limon \
-p 3306:3306 \
-v ~/docker/limon-mysql:/app \
-e MYSQL_DATABASE=limon \
-e MYSQL_USER=db_test \
-e MYSQL_PASSWORD=12345678 \
-e MYSQL_ROOT_PASSWORD=123456 \
wangxian/alpine-mysql
```


# release log
git tag v**
git push origin v**

