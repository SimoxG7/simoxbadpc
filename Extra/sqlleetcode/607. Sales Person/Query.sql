SELECT name 
FROM SalesPerson 
WHERE name NOT IN (
SELECT P.name 
FROM SalesPerson AS P
JOIN Orders AS O ON P.sales_id = O.sales_id
JOIN Company AS C ON C.com_id = O.com_id 
WHERE C.name = 'RED');  



