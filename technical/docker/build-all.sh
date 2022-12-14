#!/usr/bin/env bash

function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}
set -e

#cd ../../business/credit;         note "Building credit-service...";                    ./gradlew clean build; cd -
cd ../../business/rate;           note "Building rate-service...";                      ./gradlew clean build; cd -
#cd ../../business/overdue;        note "Building overdue-service...";                   ./gradlew clean build; cd -

cd ../discovery;                  note "Building discovery-server...";                  ./gradlew clean build; cd -
#cd ../gateway;                    note "Building gateway-server...";                    ./gradlew clean build; cd -
#cd ../config;                     note "Building config-server...";                     ./gradlew clean build; cd -


docker-compose build