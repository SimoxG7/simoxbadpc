WITH cte AS (
  SELECT customer_number, COUNT(order_number) AS CNT 
  FROM Orders
  GROUP BY customer_number
)
SELECT customer_number 
FROM cte 
WHERE CNT = (
  SELECT MAX(CNT)
  FROM cte
);


-- bad alt?
SELECT customer_number
FROM Orders
GROUP BY customer_number
ORDER BY COUNT(order_number) DESC
LIMIT 1;


