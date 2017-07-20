export M2=/Users/dimeh/Documents/Tools/apache-maven-3.3.9/bin
export PATH=$PATH:$M2

export DIGITALOCEAN_PRIVATE_NETWORKING=true
export DIGITALOCEAN_IMAGE=centos-7-x64

> docker build -t ehayanis/stack-tech .
=======
**Download and Start Mongo DB Server**
> docker run -P -d --name mongodb mongo

**to push an image:
Docker login 
docker push ehayanis/IMAGE_NAME

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
> docker-machine env demo-machine 

> eval $(docker-machine env demo-machine)
> Switch back to local: eval $(docker-machine env -u)
> docker info 

> docker-compose up -d


** Set Counsl and Docker Swarm ** 
Export KV IP: 
export KV_IP="10.132.20.128"

**Using POSTMAN** 
POST method, body raw type and content-type json 
value: {"firstName":"Luis","lastName":"OSS"}


**Error: Can't connect to docker daemon**
Provision Centos VM, remvoe docker-ce then install docker 
-- add root to docker group 
-- usermod -aG docker $(whoami)

**DOCKER and VMs provisionning AWS**
> Create an HOME/.aws/credentials file and copy ID and secret key 
> Run "docker-machine create --driver amazonec2 aws-node"

**Install and COnfigure Jenkins
export JENKINS_HOME=/Users/dimeh/Documents/workspace/pic/jenkins/home/
nohup java -jar jenkins.was &
nohup java -jar jenkins.war > output.log 2>&1 &