# Running a wat in tomcat
FROM tomcat:9.0-jre10
MAINTAINER Mansur Khan <pmansuralikhan@gmail.com>
COPY target/restfulmavenapp.war /usr/local/tomcat/webapps/restfulmavenapp.war
EXPOSE 8080
