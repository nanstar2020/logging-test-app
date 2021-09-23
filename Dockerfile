FROM amazon/aws-cli
  
RUN yum install -y rsync 

ENTRYPOINT ["/bin/sh", "-c", "while true; do sleep 10; done"]
