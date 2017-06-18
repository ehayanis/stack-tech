**Download and Start Mongo DB Server**
> docker run -P -d --name mongodb mongo

**Use of mongodb**
> docker exec -it mongodb /bin/bash <br>
> mongo

**Create and list collections**
> use microserviceblog
> db.createCollection('testCollection')
> db.getCollectionNames()

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