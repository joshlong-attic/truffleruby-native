#!/usr/bin/env bash
rm -rf target
mvn -e -X -DskipTests -Pnative  native:compile  && target/ruby