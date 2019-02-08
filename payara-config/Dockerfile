#FROM airhacks/payara-configured
FROM payara/server-full
MAINTAINER Mansur Khan <pmansuralikhan@gmail.com>

CMD ["echo ", "$DEPLOY_DIR"]
RUN ls
RUN pwd
#RUN ls -lart target/restfulmavenapp.war
RUN ls -lart /opt
RUN ls -lart /opt/payara5
RUN echo $DEPLOY_DIR
RUN java -version
RUN which java
RUN ls -lart /usr/lib/jvm/java-11-openjdk-amd64/
#COPY target/restfulmavenapp.war ${DEPLOY_DIR}
EXPOSE 4848
EXPOSE 8080
