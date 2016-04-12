#!/bin/bash

## This script uses the Gradle wrapper to run a simple demo.
## It can also be run standalone like this:
## $ ./gradlew installDist
## $ build/install/registrysorter/bin/registrysorter doc/input-example.txt

./gradlew run -Pinput=doc/input-example.txt
