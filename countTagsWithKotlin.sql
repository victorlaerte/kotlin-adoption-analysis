SELECT count(*) FROM posts
WHERE Tags LIKE '%kotlin%'
	AND ParentId IS NULL
	AND NOT (
		Tags LIKE '%javascript%'
		OR
		Tags LIKE '%js%')
		
/*4392*/