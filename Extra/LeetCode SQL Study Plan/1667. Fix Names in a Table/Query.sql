-- UPDATE Users 
-- SET name = CONCAT(UPPER(SUBSTRING(name, 1, 1)), LOWER(SUBSTRING(name, 2, LENGTH(name)-1)));

SELECT user_id, CONCAT(UPPER(SUBSTRING(name, 1, 1)), LOWER(SUBSTRING(name, 2, LENGTH(name)-1))) as name 
FROM Users
ORDER BY user_id;