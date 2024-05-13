# Helm Install Guide
1. Install the Chartmuseum Chart with the following command
   1. `helm install stable/chartmuseum --set env.open.DISABLE_API=false`
2. Once installed, proxy the repo to your localhost
    1. `export POD_NAME=$(kubectl get pods --namespace default -l "app=chartmuseum" -l "release=chartmuseum-1715593943" -o jsonpath="{.items[0].metadata.name}")
       echo http://127.0.0.1:8080/
       kubectl port-forward $POD_NAME 8080:8080 --namespace default`
3. Package your chart with 
   1. `helm package k8s/apiLogger`
4. Install chart to the chart museum repo
   1. `curl -L --data-binary "@apilogger-<version>.tgz" http://localhost:8080/api/charts`
5. Update the repo with the following command
   1. `helm repo update`
6. Install the chart with
   1. `helm install chartmuseum/apilogger --generate-name`