FROM tomcat:9.0-jre10
COPY target/restfulmavenapp.war /usr/local/tomcat/webapps/restfulmavenapp.war
EXPOSE 8080