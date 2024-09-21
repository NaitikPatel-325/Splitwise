FROM ubuntu:latest
LABEL authors="np030"
RUN apt-get update && apt-get install -y \ procps
ENTRYPOINT ["top", "-b"]