SELECT Users.name, SUM(Rides.distance) AS 'travelled_distance'
FROM Users 
JOIN Rides ON Rides.user_id = Users.id 
GROUP BY Users.name
UNION 
SELECT Users.name, 0 AS 'travelled_distance'
FROM Users
WHERE Users.id NOT IN (
  SELECT DISTINCT Rides.user_id 
  FROM Rides
)
ORDER BY travelled_distance DESC, name ASC; 


SELECT Users.name, IFNULL(SUM(Rides.distance), 0) AS 'travelled_distance'
FROM Users 
LEFT JOIN Rides
ON Users.id = Rides.user_id
GROUP BY user_id
ORDER BY travelled_distance DESC, name ASC;

