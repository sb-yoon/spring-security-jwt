/* 회원 */
CREATE TABLE tbl_account
(
    `id`                            BIGINT           NOT NULL    AUTO_INCREMENT         COMMENT '회원 식별자',
    `email`                         VARCHAR(50)      NOT NULL                           COMMENT '이메일',
    `pwd`                           VARCHAR(255)     NOT NULL                           COMMENT '비밀번호(암호화)',
    `nickname`                      VARCHAR(50)      NOT NULL                           COMMENT '닉네임',
    `name`                          VARCHAR(20)      NULL                               COMMENT '이름',
    `gender`                        VARCHAR(1)       NULL                               COMMENT '성별(M:남성,F:여성)',
    `role`                          VARCHAR(10)      NOT NULL    DEFAULT 'USER'         COMMENT '회원구분(USER:일반, CORP:기업)',
    `reg_date`                      DATETIME         NULL                               COMMENT '등록일시',
    `mod_date`                      DATETIME         NULL                               COMMENT '수정일시',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=UTF8mb4 COMMENT '회원';

/* 게시글 */
CREATE TABLE tbl_board
(
    `id`                            BIGINT           NOT NULL    AUTO_INCREMENT         COMMENT '게시글 식별자',
    `category`                      VARCHAR(20)      NOT NULL                           COMMENT '카테고리',
    `title`                         VARCHAR(100)     NOT NULL                           COMMENT '제목',
    `content`                       TEXT             NOT NULL                           COMMENT '내용',
    `account_id`                    BIGINT           NOT NULL                           COMMENT '회원 식별자',
    `reg_date`                      DATETIME         NULL                               COMMENT '등록일시',
    `mod_date`                      DATETIME         NULL                               COMMENT '수정일시',
    PRIMARY KEY (id),
    CONSTRAINT FK_board_user_id_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8mb4 COMMENT '게시글';