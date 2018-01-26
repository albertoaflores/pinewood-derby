DEV_HOST="localhost:8080"
CF_HOST="http://dev-contact-service.cfapps.io/"
DEPLOYMENT_HOST=$DEV_HOST

curl -H "Content-Type: application/json" -X POST -d @$1 $DEPLOYMENT_HOST/api/racer
echo
