CREATE TABLE expense_category
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(255) NOT NULL
);

CREATE TABLE expense_sub_category
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sub_category VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_expense_sub_category_on_category FOREIGN KEY (category_id)
        REFERENCES expense_category (id)
        ON DELETE CASCADE
);
