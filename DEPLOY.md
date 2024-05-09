# Deployment Instructions
1. First increment the service version using
   1. If deploying to Production use `./gradlew updateVersion -PconfidenceLevel="PROD"`
   2. If deploying to Development use `./gradlew updateVersion -PconfidenceLevel="DEV"`
2. Build the docker image using 
   1. If deploying to Production use `./gradlew buildDockerImage -PconfidenceLevel="PROD"`
   2. If deploying to Development use `./gradlew buildDockerImage -PconfidenceLevel="DEV"`