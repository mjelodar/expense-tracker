CREATE TABLE expense
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime NULL,
    category_id      BIGINT NULL,
    category_name    VARCHAR(255) NULL,
    subcategory_id   BIGINT NULL,
    subcategory_name VARCHAR(255) NULL,
    cost DOUBLE NULL,
    user_id          BIGINT NULL,
    CONSTRAINT pk_expense PRIMARY KEY (id)
);