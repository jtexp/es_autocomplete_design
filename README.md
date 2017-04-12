# es_autocomplete_design

1. Create an aws account
2. Setup elasticsearch on Free tier, setup access to be your IP or whatever you feel is convenient for now.
3. Familiarize yourself with [Docs](https://www.elastic.co/guide/en/elasticsearch/reference/5.1/docs.html) and [Tutorial](https://www.elastic.co/guide/en/elasticsearch/guide/master/getting-started.html)
4. Create elasticsearch-cred.json as shown in [elastic.md](src/main/java/nutriscope/ndb/elastic/elastic.md)
5. Run Setup.main in order to download the data files we will use.
6. Run Upload.main in order to upload the bulk SimpleFood to ES
