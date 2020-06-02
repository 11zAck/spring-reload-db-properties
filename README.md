# Reloading properties in Spring 2.1.14
1. Download project.
2. Download maven libs.
3. Exec: ``mvn compile``
4. Run with: ```java -jar target/imendez-0.0.1-SNAPSHOT.jar```

## Get Fun!
The project has a h2 database, login in with:
[http://localhost:8082/database](http://localhost:8082/database)

**URL:** ```jdbc:h2:mem:storedb```

**User:** ```seller```

**Pass:** ```secret1```

There is a table called: ``DVT__TP_PARAMS_WEB`` 

Run script: 
```sql
SELECT * FROM DVT__TP_PARAMS_WEB 
```
You'll see two properties. Go to [http://localhost:8082/api/v1/properties/](http://localhost:8082/api/v1/properties/), there are showing two values corresponding the two properties before.


In database, change the value of one property and update the environment properties in [http://localhost:8082/api/v1/properties/reload/](http://localhost:8082/api/v1/properties/reload/) GET HTTP endpoint.

Go to previus URL [http://localhost:8082/api/v1/properties/](http://localhost:8082/api/v1/properties/) and you see the new value of your property.
