# demo-app

* downoad client generator
wget https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/4.3.1/openapi-generator-cli-4.3.1.jar    -O openapi-generator-cli.jar


* generate client
java -jar openapi-generator-cli.jar generate -i /home/aleksandr/myproj/cdp-api-gateway-poc/api-specification/consumers-management-v1.yaml -g java -o ./java

* build client 
mvn clean install -DskipTests 


* add client jar
idea:
File > Project structure > Libraries> + > estee/demo/src/main/resources/jars
