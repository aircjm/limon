drop table if exists anki_card;
create table anki_card
(
  id                    bigint(20)   not null auto_increment comment 'id',
  card_id               varchar(255) not null default '' comment 'trello 卡片id',
  card_title            varchar(2000) not null default '' comment '标题',
  card_desc             longtext     null comment '内容',
  desc_html             longtext     null comment '内容的html',
  trello_update_time    timestamp(3) comment 'trello 卡片更新时间 毫秒级',
  list_id               varchar(255) not null default '' comment 'trello 集合id',
  board_id              varchar(255) not null default '' comment 'trello 面板id',
  type                  int                   default 0 comment '类型',
  status                smallint     null comment '状态',
  anki_note_id          varchar(255) null comment 'anki生成note的id',
  anki_note_update_time timestamp null comment 'anki卡片 更新时间',
  create_time           timestamp    NOT NULL DEFAULT current_timestamp comment '创建时间',
  update_time           TIMESTAMP    NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp comment '更新时间',
  creator               int          null comment '创建人',
  modifier              int          null comment '修改人',
  deleted               int                   default 0 null comment '逻辑删除标识(0.未删除,1.已删除)',
  primary key (id)
) engine = innodb
  auto_increment = 1 comment = '卡片表';

drop table if exists trello_card;
create table trello_card
(
  id                 int auto_increment comment '主键'
    primary key,
  card_id            varchar(200) default ''                null comment 'card id',
  card_title         varchar(5000) default ''                null comment 'card title',
  card_desc          text                                   null comment 'card desc',
  card               text                                   null comment 'card的json内容',
  date_last_activity timestamp    default CURRENT_TIMESTAMP not null comment 'card最后更新时间',
  board_id           varchar(200) default ''                null comment 'board id',
  create_time           timestamp    NOT NULL DEFAULT current_timestamp comment '创建时间',
  update_time           TIMESTAMP    NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp comment '更新时间',
  creator               int          null comment '创建人',
  modifier              int          null comment '修改人',
  deleted               int                   default 0 null comment '逻辑删除标识(0.未删除,1.已删除)',
  constraint trello_card_id_index
    unique (card_id)
)
  comment 'trello的card表';


drop table if exists lm_task;
create table lm_task
(
    id          bigint(20)   not null auto_increment comment 'id',
    name       varchar(500) not null default '' comment '标题',
    desc_type        tinyint(1)   NOT NULL DEFAULT 0 comment '类型 0-markdown 1-html 2-prosemirror',
    task_desc   text          comment '内容',
    task_html   text          comment '内容的html',
    status      tinyint(1)   NOT NULL DEFAULT 0 comment '状态',
    due_time    DATETIME comment '截至时间',
    done_time   DATETIME comment '创建时间',
    start_time  DATETIME comment '持续时间-开始时间',
    end_time    DATETIME comment '持续时间-结束时间',
    note_id     bigint(20)   NOT NULL DEFAULT 0 comment '笔记id',
    position    float                 DEFAULT 0.00 comment '位置',
    create_time DATETIME     NOT NULL DEFAULT current_timestamp comment '创建时间',
    update_time DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp comment '更新时间',
    creator     int          null comment '创建人',
    modifier    int          null comment '修改人',
    deleted     int                   default 0 null comment '逻辑删除标识(0.未删除,1.已删除)',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '任务表';



drop table if exists lm_tag;
create table lm_tag
(
    id          bigint(20)   not null auto_increment comment 'id',
    name        varchar(500) not null default '' comment '标签名称',
    color        varchar(50)   NOT NULL DEFAULT 0 comment '颜色',
    status      tinyint(1)   NOT NULL DEFAULT 0 comment '状态',
    create_time DATETIME     NOT NULL DEFAULT current_timestamp comment '创建时间',
    update_time DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp comment '更新时间',
    creator     int          null comment '创建人',
    modifier    int          null comment '修改人',
    deleted     int                   default 0 null comment '逻辑删除标识(0.未删除,1.已删除)',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = '标签表';


drop table if exists lm_task_attachment;
create table lm_task_attachment
(
    id          bigint(20)   not null auto_increment comment 'id',
    file_name       varchar(200) not null default '' comment '文件名称',
    task_id     bigint(20)   NOT NULL DEFAULT 0 comment '任务id',
    type        tinyint(1)   NOT NULL DEFAULT 0 comment '类型 0markdown 1html',
    status      tinyint(1)   NOT NULL DEFAULT 0 comment '状态',
    file_url        varchar(1000) not null default '' comment '文件url',
    create_time DATETIME     NOT NULL DEFAULT current_timestamp comment '创建时间',
    update_time DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp comment '更新时间',
    creator     int          null comment '创建人',
    modifier    int          null comment '修改人',
    deleted     int                   default 0 null comment '逻辑删除标识(0.未删除,1.已删除)',
    primary key (id)
) engine = innodb
  auto_increment = 1 comment = 'task附件表';



