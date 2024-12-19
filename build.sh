#!/bin/bash

./mvnw clean package -DskipTests

if [ $? -eq 0 ]; then
  JAR_FILE=$(find target -type f -name "*.jar" | head -n 1)
  if [ -n "$JAR_FILE" ]; then
    echo "Build successful. JAR file created at $JAR_FILE"
  else
    echo "Build successful, but no JAR file found in target directory."
    exit 1
  fi
else
  echo "Build failed."
  exit 1
fi
