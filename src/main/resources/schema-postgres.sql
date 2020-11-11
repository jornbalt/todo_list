DROP TABLE IF EXISTS tasks_categories;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS category;
CREATE TABLE task(id serial PRIMARY KEY, name VARCHAR(255) UNIQUE NOT NULL);
CREATE TABLE category(id serial PRIMARY KEY, name VARCHAR(50) UNIQUE NOT NULL);
CREATE TABLE tasks_categories(task_id INT, category_id INT, FOREIGN KEY (task_id) REFERENCES task (id), FOREIGN KEY (category_id) REFERENCES category (id));