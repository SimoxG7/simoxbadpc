SELECT Users.name, SUM(Transactions.amount) AS 'balance'
FROM Users 
JOIN Transactions 
ON Transactions.account = Users.account
GROUP BY Users.name
HAVING balance > 10000;




