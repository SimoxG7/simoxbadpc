SELECT id, CASE 
  WHEN p_id IS NULL THEN 'Root'
  WHEN p_id IN (SELECT id FROM Tree) AND id IN (SELECT p_id FROM Tree) then 'Inner'
  ELSE 'Leaf'
  END AS type 
FROM Tree;


-- Root 
SELECT id, 'Root' as type 
FROM Tree
WHERE p_id IS NULL
UNION
-- Inner
SELECT DISTINCT T1.id, 'Inner' AS type
FROM Tree AS T1 
JOIN Tree AS T2 ON T1.id = T2.p_id AND T1.p_id IS NOT NULL
-- Leaf
UNION 
SELECT id, 'Leaf' AS type 
FROM Tree 
WHERE p_id IS NOT NULL AND id NOT IN 
(SELECT id
FROM Tree
WHERE id IN (SELECT p_id FROM Tree));


