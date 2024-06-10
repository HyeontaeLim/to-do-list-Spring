drop table if exists memo CASCADE;
create table memo (
    memoId bigint NOT NULL AUTO_INCREMENT,
    memo text NOT NULL,
    created DATETIME NOT NULL,
    dTime DATETime NOT NULL,

    PRIMARY KEY (memoId)
);

drop table if exists member CASCADE;
create table member (
    memberId bigint NOT NULL AUTO_INCREMENT,
    password text NOT NULL,
    name DATETIME NOT NULL,
    gender DATETime NOT NULL,
    email VARCHAR(255),
    PRIMARY KEY (memberId)
);