SELECT email AS 'Email'
FROM Person 
GROUP BY email
HAVING COUNT(email) > 1;


SELECT DISTINCT(p1.email) 
FROM Person AS p1, Person AS p2
WHERE p1.id <> p2.id AND p1.email = p2.email;

