FROM java:10
VOLUME /tmp
ADD target/*.jar /spring-boot-admin.jar
RUN bash -c 'touch /spring-boot-admin.jar'
EXPOSE 8080
ENTRYPOINT ["java","-jar","/spring-boot-admin.jar"]