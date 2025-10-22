CREATE TABLE notification
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime NULL,
    user_id          BIGINT NULL,
    rule_id          BIGINT NULL,
    message          VARCHAR(255) NULL,
    category_name    VARCHAR(255) NULL,
    subcategory_name VARCHAR(255) NULL,
    type             VARCHAR(255) NULL,
    seen             BIT(1) NULL,
    CONSTRAINT pk_notification PRIMARY KEY (id)
);
