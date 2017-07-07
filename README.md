export M2=/Users/dimeh/Documents/Tools/apache-maven-3.3.9/bin
export PATH=$PATH:$M2
export DOTOKEN=98d323b628a53ea06cfc1c8989b23169e9b3c72dea3c1f1044297f8783fa643a

> docker build -t ehayanis/demo-webapp .
=======
**Download and Start Mongo DB Server**
> docker run -P -d --name mongodb mongo

**Use of mongodb**
> docker exec -it mongodb /bin/bash <br>
> mongo admin -u admin -p OapsdL7wpSD0 --host localhost --port 28017

**Create and list collections**
> use microserviceblog
> db.createCollection('testCollection')
> db.getCollectionNames() 

> show dbs
To display the DB you are using;
> db 
The operation should return test which is the default database. To switch DB 
> use <database>


**MongoDB Overview**
> Database, Collection (Table), Document (Row)

**Insert into collection**
> db.mycol.insert({
     _id: ObjectId(7df78ad8902c),<br>
     title: 'MongoDB Overview', <br>
     description: 'MongoDB is no sql database',<br>
     by: 'tutorials point',<br>
     url: 'http://www.tutorialspoint.com',<br>
     tags: ['mongodb', 'database', 'NoSQL'],<br>
     likes: 100<br>
  })
  
**Find Document in Collection**
> db.testCollection.find({"by":"tutorials point"}).pretty() <br>
> db.testCollection.find({$and:[{"by":"tutorials point"},{"likes": "$gt:50"}]}).pretty()

**Update and sate methods**
> The update method updates the value in the existing document, while save replaces the existing document 

**Remove Documents**
> To remove all elements <br> 
db.testCollection.remove() <br>
> To remove specific elements <br>
db.testCollection.remove({'title':'MongoDB Overview'})


**DOCKER and VMs provisionning DO**

> docker-machine create --driver digitalocean -digitalocean-image centos-7-0-x64 --digitalocean-access-token $DOTOKEN centos-docker
> docker-machine create --driver digitalocean --digitalocean-access-token $DOTOKEN default-docker

> docker-machine env demo-machine 

> eval $(docker-machine env demo-machine)

> docker info 

> docker-compose up -d


**Using POSTMAN** 
POST method, body raw type and content-type json 
value: {"firstName":"Luis","lastName":"OSS"}

**Error: Can't connect to docker daemon**
Provision Centos VM, remvoe docker-ce then install docker 
-- add root to docker group 
-- usermod -aG docker $(whoami)

**DOCKER and VMs provisionning AWS**
> Create an HOME/.aws/credentials file and copy ID and secret key 
> Run "docker-machine create --drvier amazonec2 aws-node"
