# es_autocomplete_design

1. Create an aws account
2. Setup elasticsearch on Free tier, setup access to be your IP or whatever you feel is convenient for now.
3. Familiarize yourself with [Docs](https://www.elastic.co/guide/en/elasticsearch/reference/5.1/docs.html) and [Tutorial](https://www.elastic.co/guide/en/elasticsearch/guide/master/getting-started.html)
4. Create elasticsearch-cred.json as shown in [elastic.md](src/main/java/nutriscope/ndb/elastic/elastic.md)
5. Run Setup.main in order to download the data files we will use.
6. Create the mappings suggest index [0]
7. Run Upload.main in order to upload the bulk SimpleFood to ES
8. Search [1] and get results [2]


[0] Autosuggest Mapping
```$json
PUT /ndb2
{
	"mappings": {
		"food": {
			"properties": {
				"suggest": {
					"type": "completion"
				},
				"name": {"type": "keyword"},
				"ndbid": {"type": "text"}
			}
		}
	}
}

```

[1] Autosuggest Search
```$json
POST /ndb2/_search?pretty
{
    "suggest": {
      "food-suggest": {
        "prefix": "Che",
        "completion": {
          "field": "suggest"
      }
    }
  }
}

```
[2] Results
```$json
{
    "took": 45,
    "timed_out": false,
    "_shards": {
        "total": 5,
        "successful": 5,
        "failed": 0
    },
    "hits": {
        "total": 0,
        "max_score": 0,
        "hits": []
    },
    "suggest": {
        "food-suggest": [
            {
                "text": "Che",
                "offset": 0,
                "length": 3,
                "options": [
                    {
                        "text": "Cheese food, cold pack, American",
                        "_index": "ndb2",
                        "_type": "food",
                        "_id": "01045",
                        "_score": 1,
                        "_source": {
                            "ndbId": "01045",
                            "name": "Cheese food, cold pack, American",
                            "suggest": {
                                "input": "Cheese food, cold pack, American"
                            }
                        }
                    },
                    {
                        "text": "Cheese food, pasteurized process, American, vitami",
                        "_index": "ndb2",
                        "_type": "food",
                        "_id": "01046",
                        "_score": 1,
                        "_source": {
                            "ndbId": "01046",
                            "name": "Cheese food, pasteurized process, American, vitamin D fortified",
                            "suggest": {
                                "input": "Cheese food, pasteurized process, American, vitamin D fortified"
                            }
                        }
                    },
                    {
                        "text": "Cheese food, pasteurized process, swiss",
                        "_index": "ndb2",
                        "_type": "food",
                        "_id": "01047",
                        "_score": 1,
                        "_source": {
                            "ndbId": "01047",
                            "name": "Cheese food, pasteurized process, swiss",
                            "suggest": {
                                "input": "Cheese food, pasteurized process, swiss"
                            }
                        }
                    },
                    {
                        "text": "Cheese, American, nonfat or fat free",
                        "_index": "ndb2",
                        "_type": "food",
                        "_id": "01061",
                        "_score": 1,
                        "_source": {
                            "ndbId": "01061",
                            "name": "Cheese, American, nonfat or fat free",
                            "suggest": {
                                "input": "Cheese, American, nonfat or fat free"
                            }
                        }
                    },
                    {
                        "text": "Cheese, blue",
                        "_index": "ndb2",
                        "_type": "food",
                        "_id": "01004",
                        "_score": 1,
                        "_source": {
                            "ndbId": "01004",
                            "name": "Cheese, blue",
                            "suggest": {
                                "input": "Cheese, blue"
                            }
                        }
                    }
                ]
            }
        ]
    }
}
```
