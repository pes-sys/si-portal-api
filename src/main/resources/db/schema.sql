DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS member_profile;
DROP TABLE IF EXISTS member_login_history;
DROP TABLE IF EXISTS oauth_accounts;
DROP TABLE IF EXISTS member_credentials;
DROP TABLE IF EXISTS group_memberships;
DROP TABLE IF EXISTS members;

-- ========================================
-- 1) members 테이블: 회원 기본 정보 + 포인트 + 소프트삭제
-- ========================================
CREATE TABLE members (
                         member_id         INT UNSIGNED   NOT NULL AUTO_INCREMENT COMMENT '사용자 고유번호 (PK)',
                         name              VARCHAR(50)    NOT NULL COMMENT '계정 이름',
                         password_hash     VARCHAR(255)   NOT NULL COMMENT '암호화된 비밀번호',
                         email             VARCHAR(100)   NOT NULL COMMENT '이메일 주소',
                         is_email_verified TINYINT        NOT NULL DEFAULT 0 COMMENT '이메일 인증 여부',
                         phone_number      VARCHAR(20)    NULL COMMENT '휴대폰 번호',
                         role              ENUM('MEMBER','ADMIN') NOT NULL DEFAULT 'MEMBER' COMMENT '사용자 권한',
                         points            INT UNSIGNED   NOT NULL DEFAULT 0 COMMENT '회원 포인트',
                         created_at        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
                         updated_at        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일시',
                         is_deleted        TINYINT        NOT NULL DEFAULT 0 COMMENT '삭제 플래그(0=활성,1=삭제)',
                         deleted_at        DATETIME       NULL COMMENT '삭제 일시',

                         PRIMARY KEY (member_id),
                         UNIQUE KEY uk_members_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
    COMMENT='회원 정보 저장 테이블';


-- ========================================
-- 2) group_memberships: 그룹 정의 + 멤버 매핑
-- ========================================
CREATE TABLE group_memberships (
                                   group_id         INT UNSIGNED   NOT NULL AUTO_INCREMENT COMMENT '그룹 고유번호',
                                   group_name       VARCHAR(100)   NOT NULL COMMENT '그룹명(매장/부서)',
                                   member_id        INT UNSIGNED   NOT NULL COMMENT 'FK → members.member_id',
                                   role_in_group    ENUM('OWNER','MANAGER','STAFF') NOT NULL DEFAULT 'STAFF' COMMENT '그룹 내 직책',
                                   joined_at        DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '합류 시점',
                                   left_at          DATETIME       NULL COMMENT '탈퇴 시점',
                                   status           ENUM('ACTIVE','PENDING','SUSPENDED') NOT NULL DEFAULT 'ACTIVE' COMMENT '소속 상태',
                                   permissions      JSON           NULL COMMENT '권한 설정',
                                   is_primary       TINYINT        NOT NULL DEFAULT 0 COMMENT '주 소속 여부',
                                   notes            VARCHAR(255)   NULL COMMENT '관리자 메모',
                                   created_at       DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성',
                                   updated_at       DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '레코드 수정',
                                   is_deleted       TINYINT        NOT NULL DEFAULT 0 COMMENT '삭제 플래그',
                                   deleted_at       DATETIME       NULL COMMENT '삭제 일시',

                                   PRIMARY KEY (group_id, member_id),
                                   KEY idx_gm_member (member_id),

    -- 회원 삭제 시 매핑도 함께 삭제
                                   CONSTRAINT fk_gm_member FOREIGN KEY(member_id)
                                       REFERENCES members(member_id)
                                       ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
    COMMENT='그룹 멤버십 매핑 테이블';


-- ========================================
-- 3) member_credentials: 인증·보안 정보
-- ========================================
CREATE TABLE member_credentials (
                                    member_id         INT UNSIGNED NOT NULL COMMENT 'FK → members.member_id',
                                    reset_token       VARCHAR(100) NULL COMMENT '재설정 토큰',
                                    reset_expires_at  DATETIME     NULL COMMENT '토큰 만료',
                                    two_factor_enabled TINYINT     NOT NULL DEFAULT 0 COMMENT '2차 인증 사용',
                                    two_factor_secret VARCHAR(100) NULL COMMENT '2차 인증 시크릿',
                                    created_at        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    updated_at        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    is_deleted        TINYINT      NOT NULL DEFAULT 0 COMMENT '삭제 플래그',
                                    deleted_at        DATETIME     NULL COMMENT '삭제 일시',

                                    PRIMARY KEY (member_id),

    -- 회원 삭제 시 인증 정보도 함께 삭제
                                    CONSTRAINT fk_cred_member FOREIGN KEY(member_id)
                                        REFERENCES members(member_id)
                                        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
    COMMENT='인증·보안 정보';


-- ========================================
-- 4) oauth_accounts: 소셜 로그인 연동
-- ========================================
CREATE TABLE oauth_accounts (
                                oauth_id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
                                member_id         INT UNSIGNED    NOT NULL COMMENT 'FK → members.member_id',
                                provider          VARCHAR(30)     NOT NULL COMMENT '공급자명',
                                provider_user_id  VARCHAR(100)    NOT NULL COMMENT '공급자 유저ID',
                                access_token      TEXT            NULL,
                                refresh_token     TEXT            NULL,
                                token_expires_at  DATETIME        NULL,
                                created_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                is_deleted        TINYINT         NOT NULL DEFAULT 0 COMMENT '삭제 플래그',
                                deleted_at        DATETIME        NULL COMMENT '삭제 일시',

                                PRIMARY KEY (oauth_id),
                                KEY idx_oauth_member (member_id),

    -- 회원 삭제 시 소셜 연동 정보도 함께 삭제
                                CONSTRAINT fk_oauth_member FOREIGN KEY(member_id)
                                    REFERENCES members(member_id)
                                    ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
    COMMENT='소셜 로그인 계정';


-- ========================================
-- 5) member_login_history: 로그인 이력
-- ========================================
CREATE TABLE member_login_history (
                                      history_id      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
                                      member_id       INT UNSIGNED    NULL COMMENT 'FK → members.member_id',
                                      login_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      ip_address      VARCHAR(45)     NULL,
                                      success_flag    TINYINT         NOT NULL COMMENT '성공(1)/실패(0)',
                                      is_deleted      TINYINT         NOT NULL DEFAULT 0 COMMENT '삭제 플래그',
                                      deleted_at      DATETIME        NULL COMMENT '삭제 일시',

                                      PRIMARY KEY (history_id),
                                      KEY idx_login_member (member_id),

    -- 회원 삭제 시 이력은 남기되 member_id는 NULL 처리
                                      CONSTRAINT fk_login_member FOREIGN KEY(member_id)
                                          REFERENCES members(member_id)
                                          ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
    COMMENT='로그인 이력';


-- ========================================
-- 6) member_profile: 프로필 정보
-- ========================================
CREATE TABLE member_profile (
                                member_id       INT UNSIGNED NOT NULL COMMENT 'FK → members.member_id',
                                nickname        VARCHAR(50)  NULL COMMENT '닉네임',
                                avatar_url      VARCHAR(255) NULL COMMENT '프로필 이미지',
                                bio             TEXT         NULL COMMENT '자기소개',
                                preferences     JSON         NULL COMMENT '설정',
                                created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                is_deleted      TINYINT      NOT NULL DEFAULT 0 COMMENT '삭제 플래그',
                                deleted_at      DATETIME     NULL COMMENT '삭제 일시',

                                PRIMARY KEY (member_id),

    -- 회원 삭제 시 프로필도 함께 삭제
                                CONSTRAINT fk_profile_member FOREIGN KEY(member_id)
                                    REFERENCES members(member_id)
                                    ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
    COMMENT='회원 프로필';


-- ========================================
-- 7) posts: 게시글
-- ========================================
CREATE TABLE posts (
                       post_id      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '글 PK',
                       member_id    INT UNSIGNED    NULL COMMENT '작성자 FK → members.member_id',
                       title        VARCHAR(200)    NOT NULL,
                       content      TEXT            NOT NULL,
                       status       ENUM('ACTIVE','INACTIVE') NOT NULL DEFAULT 'ACTIVE',
                       created_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       is_deleted   TINYINT         NOT NULL DEFAULT 0 COMMENT '삭제 플래그',
                       deleted_at   DATETIME        NULL COMMENT '삭제 일시',

                       PRIMARY KEY (post_id),
                       KEY idx_posts_member (member_id),

    -- 회원 삭제 시 글은 남기되 작성자는 NULL
                       CONSTRAINT fk_posts_member FOREIGN KEY(member_id)
                           REFERENCES members(member_id)
                           ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
    COMMENT='게시글';


-- ========================================
-- 8) comments: 댓글
-- ========================================
CREATE TABLE comments (
                          comment_id   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '댓글 PK',
                          post_id      BIGINT UNSIGNED NOT NULL COMMENT 'FK → posts.post_id',
                          member_id    INT UNSIGNED    NULL COMMENT '작성자 FK → members.member_id',
                          comment_text TEXT            NOT NULL,
                          created_at   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          is_deleted   TINYINT         NOT NULL DEFAULT 0 COMMENT '삭제 플래그',
                          deleted_at   DATETIME        NULL COMMENT '삭제 일시',

                          PRIMARY KEY (comment_id),
                          KEY idx_comments_post (post_id),
                          KEY idx_comments_member (member_id),

    -- 글 삭제 시 댓글도 함께 삭제
                          CONSTRAINT fk_comments_post   FOREIGN KEY(post_id)   REFERENCES posts(post_id)   ON DELETE CASCADE,
    -- 회원 삭제 시 댓글은 남기되 작성자는 NULL
                          CONSTRAINT fk_comments_member FOREIGN KEY(member_id) REFERENCES members(member_id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
    COMMENT='댓글';
