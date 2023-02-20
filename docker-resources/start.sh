#!/bin/bash

echo `sudo service mysql start`

java -jar ./ShoppingMall-0.0.1-SNAPSHOT.jar >> ./app.log 2>&1