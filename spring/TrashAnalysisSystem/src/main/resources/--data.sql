INSERT INTO users (email, password) VALUES
    ('kkamimura@example.com', '0000'),
    ('tyukinaga@example.com', '0000');

INSERT INTO trash_categories (name) VALUES
    ('燃えるごみ'),
    ('燃えないごみ'),
    ('紙・布類'),
    ('びん・かん'),
    ('ペットボトル'),
    ('その他');

INSERT INTO trashes (user_id, trash_category_id, date, capacity, weight) VALUE
('1', '1', '2017-06-09', '20', '4.0');
