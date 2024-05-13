FROM amazonlinux:2
MAINTAINER Cian Ellwood
WORKDIR /usr/local/apiLogger
RUN yum update -y && \
    yum install java -y && \
    yum clean all;

ARG VERSION
# Copy jar into current workdir
COPY /build/libs/apilogger-${VERSION}.jar .
COPY /build/resources/main/application.properties .
ENTRYPOINT ["java", "-jar", "/usr/local/apiLogger/animated-octo-palm-tree-${VERSION}.jar"]
EXPOSE 3030