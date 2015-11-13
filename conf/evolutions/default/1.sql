# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table genre (
  id                        bigserial not null,
  name                      varchar(255),
  constraint pk_genre primary key (id))
;

create table movie (
  id                        bigserial not null,
  title                     varchar(255),
  summary                   varchar(255),
  url                       varchar(255),
  genre_id                  bigint,
  constraint pk_movie primary key (id))
;

create table users (
  id                        bigserial not null,
  username                  varchar(255),
  password_hash             varchar(255),
  constraint uq_users_username unique (username),
  constraint pk_users primary key (id))
;

alter table movie add constraint fk_movie_genre_1 foreign key (genre_id) references genre (id);
create index ix_movie_genre_1 on movie (genre_id);



# --- !Downs

drop table if exists genre cascade;

drop table if exists movie cascade;

drop table if exists users cascade;

