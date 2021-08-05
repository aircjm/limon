# limon

limon 是一款极易搭建的个人笔记管理服务。


## 项目愿景

limon 项目旨在打造一个以最简便的方式搭建简单、稳定和可扩展的个人笔记管理服务。后续计划扩展成GTD和卡片笔记服务一体，形成个人时间管理，个人笔记管理一体的信息管理服务。

由于使用markdown 所以只能使用特定的模板 Markdown

## 主要特性

- 导入trello的笔记
- 笔记内容转换成markdown的anki的note方便复习回顾







## 注意事项
- 需要指定对应的DB数据库

### 初始化DB

docker run -it --name limon-mysql -p 3306:3306 -v /d/dockerDrive/mysql:/app -e MYSQL_DATABASE=limon -e MYSQL_USER=db_test -e MYSQL_PASSWORD=123456 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0


## 备份还原
```shell script
docker exec limon-mysql /usr/bin/mysqldump -u root --password=root limon > backup.sql
```



## 特别鸣谢

- 感谢 [在 Anki 中使用 Markdown](https://zhuanlan.zhihu.com/p/137570649) anki如何展示markdown。
- 感谢 [RuoYi](https://gitee.com/y_project/RuoYi-Vue) 设计的 脚手架。
- [![jetbrains.svg](jetbrains.svg)](https://www.jetbrains.com/?from=limon)





## 授权许可

本项目采用 MIT 开源授权许可证，完整的授权说明已放置在 [LICENSE](https://github.com/aircjm/limon/blob/master/LICENSE) 文件中。


## 参考
```shell script
docker exec mysql_container_name /usr/bin/mysqldump -u user_name --password=user_password db_name > backup.sql
```