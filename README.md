SCache is a simple distributed key value store which uses SQL as its storage.

Assumptions:

1 . Stores only string parameters. No additional data structures like list,array,set ,map are supported.

2 . With respect to CAP theorem. Consistency is compromised so the SCache will be 100% available with eventually consistent data.

3 . Nodes in the cluster are not dicovered by itself. SCache doest not have a service discovery like zookeeper. we need to manually form the clusters with ip and port either via text file or via http calls.

Possible Improvements:

1 . SCache uses Mysql has the underlying data store. As concurrency,faster retrieval and partitions are handled in MySql by default it was easy for development but we can define our own storage using files to make it faster.


2 . SCache uses HTTP protocol for communication between nodes in the cluster. HTTP protocol adds additional overhead during transmission. it can be improved by defining our own custom protocol or via socket.


3 . We can define any Service discovery servers like zookeeper for Node discovery.

Installation Guide:
Require : Tomcat7,Mysql,Maven,Java 1.5 above
Need to run two local tomcat servers with different port. Need to change the data base name in hibernate conf file in each generated war file. 

Steps
  
  
  1 .Clone the repository . git clone https://github.com/kannanshan/SqlCache.git
  
  
  2 . Change the database name to sql_cache_1 in the hibernate file. location : SqlCache/src/main/resources/config/hibernate.cfg.xml
  
  
  3 . Build the repo using maven. mvn clean install
  
  
  4 . Execute the sql-cache-schema.sql file to create the tables with the database name sqs_cache_1.
  
  
  5 . Start the tomat server with generated war  
  
  
  6 . Repeat the step from 2 with different Database name sql_cache_2

 Now we have two servers up and running. We need to form cluster by making an http call to both teh servers.
 Find the curl command below.
 
 curl -X POST http://localhost:8081/SqlCache/nodes -H 'content-type: application/json' -d '{"ip": "127.0.0.1","port":"8080"}'

curl -X POST http://localhost:8080/SqlCache/nodes -H 'content-type: application/json' -d '{"ip": "127.0.0.1","port":"8081"}'

Curl commands for set and get
curl -X POST http://127.0.0.1:8081/SqlCache/set/ -H 'content-type: application/json' -d '{"cricket": "sachin"}'

curl -X GET http://localhost:8080/SqlCache/get/cricket -H 'content-type: application/json'

Due to time constraint not able to complete the Integration test scripts and thought of creating a docker file which would have made the installation easier but not able to complete the above two because of the time constraints. 
 

