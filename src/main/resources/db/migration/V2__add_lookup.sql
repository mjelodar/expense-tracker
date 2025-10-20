CREATE TABLE expense_category
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    category VARCHAR(255) NULL,
    CONSTRAINT pk_expensecategory PRIMARY KEY (id)
);

CREATE TABLE expense_sub_category
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    sub_category VARCHAR(255) NULL,
    category_id  BIGINT NULL,
    CONSTRAINT pk_expensesubcategory PRIMARY KEY (id)
);

ALTER TABLE expense_sub_category
    ADD CONSTRAINT FK_EXPENSESUBCATEGORY_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES expense_sub_category (id);
