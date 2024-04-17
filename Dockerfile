FROM --platform=linux/arm64 amazonlinux:2
MAINTAINER Cian Ellwood
RUN yum update && \
    yum install java -y && \
    yum clean all;
COPY /build/libs/animated-octo-palm-tree-1.2.3.jar /opt/apiLogger/animated-octo-palm-tree-1.2.3.jar
CMD ["java", "-jar", "/opt/apiLogger/animated-octo-palm-tree-1.2.3.jar"]
EXPOSE 3000