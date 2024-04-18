FROM amazonlinux:2
MAINTAINER Cian Ellwood
WORKDIR /usr/local/apiLogger
RUN yum update && \
    yum install java -y && \
    yum clean all;

# Copy jar into current workdir
COPY /build/libs/animated-octo-palm-tree-1.2.3.jar .
COPY /build/resources/main/application.properties .
ENTRYPOINT ["java", "-jar", "/usr/local/apiLogger/animated-octo-palm-tree-1.2.3.jar"]
EXPOSE 3030