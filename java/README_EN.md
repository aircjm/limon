# Limon - Personal Note Management Service

Limon is an easy-to-setup personal note management service designed for simplicity, stability, and scalability.

## Project Vision

The Limon project aims to create a simple, stable, and scalable personal note management service with the easiest setup possible. Future plans include expanding into a comprehensive GTD (Getting Things Done) and Zettelkasten note-taking service, integrating personal time management and note management into a unified information management service.

Since Limon uses Markdown, it requires specific templates for Markdown formatting.

## Key Features

- **Import Trello Notes**: Easily import notes from Trello.
- **Convert to Anki Notes**: Automatically convert note content into Anki-compatible Markdown notes for review and retention.

## Important Notes

- **Database Configuration**: Ensure you specify the correct database for your setup.

### Initializing the Database

```shell
docker run -it --name limon-mysql -p 3306:3306 -v /d/dockerDrive/mysql:/app -e MYSQL_DATABASE=limon -e MYSQL_USER=db_test -e MYSQL_PASSWORD=123456 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0
```

## Backup and Restore

### Backup

```shell
docker exec limon-mysql /usr/bin/mysqldump -u root --password=root limon > backup.sql
```

### Restore

```shell
docker exec -i limon-mysql mysql -u root --password=root limon < backup.sql
```

## Special Thanks

- **Anki Markdown Integration**: Thanks to [Using Markdown in Anki](https://zhuanlan.zhihu.com/p/137570649) for guidance on displaying Markdown in Anki.
- **RuoYi Scaffold**: Thanks to [RuoYi](https://gitee.com/y_project/RuoYi-Vue) for the project scaffold design.
- **JetBrains Support**: Special thanks to JetBrains for their open-source license support.

  [![jetbrains.svg](jetbrains.svg)](https://www.jetbrains.com/?from=limon)

## License

This project is licensed under the MIT License. Full license details can be found in the [LICENSE](https://github.com/aircjm/limon/blob/master/LICENSE) file.

## References

For additional backup and restore commands, refer to:

```shell
docker exec mysql_container_name /usr/bin/mysqldump -u user_name --password=user_password db_name > backup.sql
```