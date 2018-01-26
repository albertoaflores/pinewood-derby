#!/bin/bash

for jsonFile in $(ls car-*); do
	echo "Creating Racer with info from $jsonFile"
	./create-racer.sh $jsonFile	
done