# Running a wat in tomcat
FROM tomcat:9.0-jre10
MAINTAINER Mansur Khan <pmansuralikhan@gmail.com>
RUN apt-get update
CMD ["ls", "-lart", "/usr/local/tomcat"]
COPY target/restfulmavenapp.war /usr/local/tomcat/webapps/restfulmavenapp.war
CMD ["ls", "-lart", "/usr/local/tomcat/webapps"]
EXPOSE 8080
