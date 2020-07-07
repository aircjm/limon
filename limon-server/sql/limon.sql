
drop table if exists cell_card;
create table cell_card
(
  id                    bigint(20)   not null auto_increment comment 'id',
  card_id               varchar(255) not null default '' comment 'trello 卡片id',
  card_title            varchar(255) not null default '' comment '标题',
  card_desc             longtext     null comment '内容',
  desc_html             longtext     null comment '内容的html',
  trello_update_time    timestamp comment 'trello 卡片更新时间',
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