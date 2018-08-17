SELECT count(*) FROM posts
WHERE Tags LIKE '%kotlin%'
	AND ParentId IS NULL
	AND (
			Tags LIKE '%android%'
			OR
			Tags LIKE '%fragments%'
			OR
			Tags LIKE '%recyclerview%'
			OR
			Tags LIKE '%intent%'
			OR
			Tags LIKE '%retrofit%'									
			OR
			Tags LIKE '%room%'
			OR
			Tags LIKE '%sqlite%'
			OR
			Tags LIKE '%parcelable%'									
			OR
			Tags LIKE '%glide%'									
			OR
			Tags LIKE '%picasso%'									
			OR
			Tags LIKE '%realm%'									
			OR
			Tags LIKE '%gson%'									
			OR
			Tags LIKE '%dagger%'									
			OR
			Tags LIKE '%ButterKnife%'									
			OR
			Tags LIKE '%EventBus%'									
			OR
			Tags LIKE '%RxJava%'
			OR
			Tags LIKE '%Volley%'
			OR
			Tags LIKE '%OkHttp%'
			OR
			Tags LIKE '%Fresco%'												
		)		
/*5667*/