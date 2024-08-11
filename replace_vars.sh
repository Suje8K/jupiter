#!/bin/bash

sed -i "s/#{mongodb-username}/$MONGODB_USERNAME/g" src/main/resources/application.yml
sed -i "s/#{mongodb-password}/$MONGODB_PASSWORD/g" src/main/resources/application.yml
sed -i "s/#{mongodb-dbname}/$MONGODB_DBNAE/g" src/main/resources/application.yml
sed -i "s/#{default-username}/$SPRING_USERNAME/g" src/main/resources/application.yml
sed -i "s/#{default-password}/$SPRING_PASSWORD/g" src/main/resources/application.yml
