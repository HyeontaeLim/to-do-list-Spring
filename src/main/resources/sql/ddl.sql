
create table if not exists memo (
    memoId bigint NOT NULL AUTO_INCREMENT,
    memo text NOT NULL,
    created DATETIME NOT NULL,
    dTime DATETime NOT NULL,
    isCompleted bool NOT NULL,
    memberId bigint NOT NULL,
    PRIMARY KEY (memoId)
);

create table if not exists member (
    memberId bigint NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL ,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    email VARCHAR(255),
    PRIMARY KEY (memberId),
    UNIQUE (username)
);
