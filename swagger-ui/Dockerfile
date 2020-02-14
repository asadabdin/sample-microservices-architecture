FROM java:10
RUN mkdir /app
VOLUME /tmp
ADD target/*.jar /app/swagger-ui.jar
EXPOSE 8012
#RUN bash -c 'touch /app.jar'
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
CMD java $JAVA_OPTS -jar /app/swagger-ui.jar