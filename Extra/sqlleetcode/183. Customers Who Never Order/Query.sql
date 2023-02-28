SELECT Customers.name as Customers
FROM Customers 
WHERE Customers.id NOT IN (
  SELECT customerId 
  FROM Orders 
  JOIN Customers 
  ON Customers.id = customerId
);