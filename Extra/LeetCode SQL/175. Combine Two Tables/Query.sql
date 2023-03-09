-- quelli che sono in entrambe union quelli che sono solo nella prima con null null

SELECT firstName, lastName, city, state 
FROM Person AS P
LEFT OUTER JOIN Address AS A ON P.personId = A.personId;

-- UNION 
-- SELECT firstName, lastName, NULL AS city, NULL AS state 
-- FROM Person AS P 
-- WHERE P.personId NOT IN (SELECT personId FROM Address);








