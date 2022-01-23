create table users(
    id bigint auto_increment primary key,
    name     varchar(255) not null,
    age      int          null,
    email    varchar(255) not null,
    password varchar(255) not null,
    enabled  bit (1)   null,
    constraint users_email_uindex
        unique (email)
);

create table roles(
    id int not null primary key,
    role varchar(50) not null
);

create table users_roles(
    user_id bigint not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint users_roles_ibfk_1
        foreign key (user_id) references users (id),
    constraint users_roles_ibfk_2
        foreign key (role_id) references roles (id)
);


