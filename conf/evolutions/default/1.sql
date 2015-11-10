# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table genre (
  id                        bigserial not null,
  name                      varchar(255),
  constraint pk_genre primary key (id))
;




# --- !Downs

drop table if exists genre cascade;

