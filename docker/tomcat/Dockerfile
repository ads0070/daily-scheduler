FROM tomcat:10-jdk11-openjdk

COPY ./conf/tomcat-users.xml $CATALINA_HOME/conf/tomcat-users.xml
COPY ./conf/context.xml /tmp/context.xml

RUN cp -a ./webapps.dist/* ./webapps
RUN cp /tmp/context.xml $CATALINA_HOME/webapps/manager/META-INF/context.xml

EXPOSE 8080
CMD ["catalina.sh", "run"]
