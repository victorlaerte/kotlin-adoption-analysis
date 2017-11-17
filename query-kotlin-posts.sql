SELECT Title,Body
FROM posts
WHERE Tags LIKE '%kotlin%'
	AND ParentId IS NULL
	AND NOT (
		Tags LIKE '%javascript%'
		OR
		Tags LIKE '%js%')
INTO OUTFILE '/Users/victoroliveira/Documents/workspace/kotlin-stackoverflow-data-analysis/kotlin-posts.csv'
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n';