SELECT X.id
FROM Weather AS X
JOIN Weather AS Y ON X.recordDate = DATE_ADD(Y.recordDate, INTERVAL 1 DAY)
WHERE X.temperature > Y.temperature;