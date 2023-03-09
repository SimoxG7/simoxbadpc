SELECT C.employee_id 
FROM (
  SELECT *
  FROM Employees AS E
  UNION
  SELECT *
  FROM Salaries AS S
) AS C
GROUP BY employee_id
HAVING COUNT(employee_id) = 1
ORDER BY employee_id ASC;