SELECT D1.date_id, D1.make_name, COUNT(DISTINCT D1.lead_id) AS 'unique_leads', COUNT(DISTINCT D1.partner_id) AS 'unique_partners'
FROM DailySales AS D1 
JOIN DailySales AS D2 ON D1.date_id = D2.date_id AND D1.make_name = D2.make_name
GROUP BY D1.date_id, D1.make_name;

SELECT date_id, make_name, COUNT(DISTINCT lead_id) AS 'unique_leads', COUNT(DISTINCT partner_id) AS 'unique_partners'
FROM DailySales 
GROUP BY date_id, make_name;