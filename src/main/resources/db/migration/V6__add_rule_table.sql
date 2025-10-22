CREATE TABLE rule
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime NULL,
    updated_at       datetime NULL,
    version          BIGINT NULL,
    `description`    VARCHAR(255) NULL,
    category_id      BIGINT NULL,
    category_name    VARCHAR(255) NULL,
    subcategory_id   BIGINT NULL,
    subcategory_name VARCHAR(255) NULL,
    cost DOUBLE NULL,
    user_id          BIGINT NULL,
    operator         VARCHAR(255) NULL,
    time_unit        VARCHAR(255) NULL,
    time_period      INT NULL,
    expiration_at    datetime NULL,
    threshold_cost DOUBLE NULL,
    no_of_repeats    BIGINT NULL,
    is_notified      BIT(1) NULL,
    CONSTRAINT pk_rule PRIMARY KEY (id)
);
