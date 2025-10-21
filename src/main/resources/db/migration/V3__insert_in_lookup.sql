-- Insert main categories
INSERT INTO expense_category (category)
VALUES
    ('Fun'),
    ('Food'),
    ('Transportation'),
    ('Bills'),
    ('Health');

-- Insert subcategories
INSERT INTO expense_sub_category (sub_category, category_id)
SELECT s.sub_category, c.id
FROM expense_category c
         JOIN (
    VALUES
        ('Fun', 'Cafe'),
        ('Fun', 'Movies'),
        ('Fun', 'Concerts'),
        ('Food', 'Groceries'),
        ('Food', 'Restaurants'),
        ('Food', 'Snacks'),
        ('Transportation', 'Fuel'),
        ('Transportation', 'Taxi'),
        ('Transportation', 'Public Transit'),
        ('Bills', 'Electricity'),
        ('Bills', 'Internet'),
        ('Bills', 'Water'),
        ('Health', 'Gym'),
        ('Health', 'Pharmacy'),
        ('Health', 'Doctor')
) AS s(category_name, sub_category)
              ON c.category = s.category_name;
