-- SELECT stock_name, SUM(price) AS 'spent' 
-- FROM Stocks
-- WHERE operation = 'Buy'
-- GROUP BY stock_name;
-- SELECT stock_name, SUM(price) AS 'earned' 
-- FROM Stocks
-- WHERE operation = 'Sell'
-- GROUP BY stock_name;

WITH cteEarned AS (
  SELECT stock_name, SUM(price) AS 'earned' 
  FROM Stocks
  WHERE operation = 'Sell'
  GROUP BY stock_name
),
cteSpent AS (
  SELECT stock_name, SUM(price) AS 'spent' 
  FROM Stocks
  WHERE operation = 'Buy'
  GROUP BY stock_name
)
SELECT cteEarned.stock_name, earned - spent AS 'capital_gain_loss' 
FROM cteEarned 
JOIN cteSpent ON cteEarned.stock_name = cteSpent.stock_name;

SELECT stock_name, SUM(
  case 
    when operation = 'Buy' then -1 * price  
    else price
  end
) AS 'capital_gain_loss'
FROM Stocks
GROUP BY stock_name;