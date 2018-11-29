FROM tomcat:9.0-jre10
RUN echo "Welcome Mansur!"
RUN ls -lart /usr/local/tomcat
RUN ls -lart /usr/local/tomcat/webapps

COPY target/restfulmavenapp.war /usr/local/tomcat/webapps/restfulmavenapp.war
