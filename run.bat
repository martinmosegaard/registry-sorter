@echo off
:: This script uses the Gradle wrapper to run a simple demo.
:: It can also be run standalone like this:
:: gradlew.bat installDist
:: build\install\registrysorter\bin\registrysorter.bat doc\input-example.txt

gradlew.bat run -Pinput=%1
