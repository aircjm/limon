# Limon - Personal Note Management Service

Limon is an easy-to-setup personal note management service designed for simplicity, stability, and scalability.

## Project Vision

The Limon project aims to create a simple, stable, and scalable personal note management service with the easiest setup possible. Future plans include expanding into a comprehensive GTD (Getting Things Done) and Zettelkasten note-taking service, integrating personal time management and note management into a unified information management service.

Since Limon uses Markdown, it requires specific templates for Markdown formatting.

- **Intuitive Data Management**: Display data in a card format to allow users to manage and operate data intuitively.
- **Flexible Function Expansion**: Support merging cards, countdown functions, and other features to meet different scenarios.
- **Front-end and Back-end Separation**: Use Java for the back-end and Vue 3 for the front-end to achieve a separated architecture, improving development efficiency and maintainability.
- **Easy Deployment**: Use Docker for back-end deployment to simplify the deployment process and reduce operational costs.

## Key Features

- **Import Trello Notes**: Easily import notes from Trello.
- **Convert to Anki Notes**: Automatically convert note content into Anki-compatible Markdown notes for review and retention.
- **Card Display**: Display data in a card format with support for multiple card layouts.
- **Card Management**: Support creating, editing, deleting, and searching cards.
- **Card Merging**: Support merging multiple cards into one card for easy data integration.
- **Countdown Function**: Add countdown functions to cards, suitable for scenarios requiring timed reminders.
- **Responsive Design**: Support responsive layouts for different devices to ensure good display on various devices.

## Important Notes

- **Database Configuration**: Ensure you specify the correct database for your setup.

### Initializing the Database

```shell
docker run -it --name limon-mysql -p 3306:3306 -v /d/dockerDrive/mysql:/app -e MYSQL_DATABASE=limon -e MYSQL_USER=db_test -e MYSQL_PASSWORD=123456 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0
```


## Deployment Details

### Front-end Deployment

1. **Build for Production**:
   ```bash
   cd vue/vite-naiveui
   npm run build
   ```

2. **Deploy to Server**:
   Deploy the files in the `dist` directory to your web server (such as Nginx, Apache) or static file hosting service (such as GitHub Pages, Netlify).

### Back-end Deployment

1. **Deploy Using Docker**:
   ```bash
   docker run -d -p 8080:8080 --name limon-backend-container limon-backend
   ```

2. **Configure Database**:
   Ensure the database service is running and configure the database connection information correctly in the backend configuration file.

## Contributing Guidelines

We welcome contributions from the community! If you are interested in contributing to the Limon project, please follow these steps:

1. **Fork the Project**: Click the "Fork" button on the GitHub page to fork the project to your GitHub account.

2. **Create a Branch**: Create a new branch in your forked project for developing your feature or fixing a bug.

3. **Submit a Pull Request**: After completing the development, submit a Pull Request describing your changes and purpose.

4. **Code Review**: The project maintainers will review your Pull Request and provide feedback.

5. **Merge Code**: Once the Pull Request is accepted, your code will be merged into the main branch.


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