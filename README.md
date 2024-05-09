# animated-octo-palm-tree
![ProjectOverview](readmeResources/PersonalProject.jpg)

This project is intended to be a simple service that performs the following

- Receives API Requests from an application 
- Ensures the payloads conform to a specified layout 
- Outputs these to a log that will be scraped by fluentbit and loaded into an external DB
- Fluentbit will then upload these to an external DB that can be queried by an observability platform such as Prometheus

Technologies that will be used for this application
- Spring Framework
- Java 17
- Kotlin for the build.gradle file (just to get some familiarity with it)
- Docker (to containerise this application)
- K8s (to ensure scalability if to be used at a wider scale)

The following technologies will be used for the complete end to end flow, these may change as I work through this
- Fluentbit (to handle the scraping of the log directory)
- Gatling (to test populating payloads at scale as well as overall application performance)
- Prometheus (to handle metrics)
- Grafana Mimir (as our DB)

##TODO
- [x] Create a Basic Restful API Service to begin containerization
- [x] Containerize Application using Docker
  - [x] Update Gradle to Add Build Scripts for Docker Build/Save/Push
  - [x] Create a versioning script to follow YYYY.WW.<INC>
- [ ] Deploy image using Kubernetes and Helm to make this scalable
- [ ] Add 2 additional endpoints that support GET requests, use dummy data for now
- [ ] Add 2 additional endpoints that support POST requests to forward to DB
- [ ] For the GET Endpoints, expand the functionality to report service info and health. 
  - https://www.baeldung.com/spring-boot-info-actuator-custom <- we will utilise this for the info as it's free 
- [ ] For the POST Endpoints, expand the functionality to create the records dynamically using a trusted source
  - https://www.baeldung.com/java-json-deserialize-record-gson <- this could be one of the approaches i follow, this may be updated down the line
  - This may involve creating a separate repo for me to pull from, could use a python script to pull these into a local dir
- [ ] Pass Java VM args into Dockerfile 
- [ ] Create start script for java application
- [ ] Configure fluentbit to scrape data from the services endpoint, this will probably live on a local server
- [ ] Configure Prometheus on local machine to report data accurately