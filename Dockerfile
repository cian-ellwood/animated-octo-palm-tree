FROM amazonlinux:2
LABEL maintainer="Cian Ellwood"
WORKDIR /usr/local/apiLogger
RUN yum update -y && \
    yum install java -y && \
    yum clean all;

ARG VERSION
# Set the environment variable for the jar version so we can use it in the start.sh script
ENV JAR_VERSION=$VERSION

# Copy jar into current workdir
COPY /build/libs/apilogger-${VERSION}.jar .
COPY /build/resources/main/application.properties .
COPY /build/resources/main/start.sh .
RUN chmod +x start.sh
ENTRYPOINT ["/usr/local/apiLogger/start.sh"]
EXPOSE 3030