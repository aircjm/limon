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

