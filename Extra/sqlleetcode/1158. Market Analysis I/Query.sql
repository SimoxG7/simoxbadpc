SELECT buyer_id, join_date, COUNT(buyer_id) AS 'orders_in_2019'
FROM Users
JOIN Orders ON buyer_id = user_id
WHERE YEAR(order_date) = 2019
GROUP BY buyer_id, join_date
UNION
SELECT user_id, join_date, 0 AS 'orders_in_2019'
FROM Users 
WHERE user_id NOT IN (
  SELECT buyer_id
  FROM Orders
  WHERE YEAR(order_date) = 2019
)
ORDER BY buyer_id;



SELECT u.user_id AS buyer_id, join_date, IFNULL(COUNT(order_date), 0) AS orders_in_2019 
FROM Users as u
LEFT JOIN Orders as o
ON u.user_id = o.buyer_id
AND YEAR(order_date) = '2019'
GROUP BY u.user_id;

