#FROM rockylinux/rockylinux:latest

#RUN yum -y update && \
#yum -y install httpd && \
#yum clean all

#COPY index.html /var/www/html/index.html

#EXPOSE 80
#ENTRYPOINT /usr/sbin/httpd -DFOREGROUND

FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]