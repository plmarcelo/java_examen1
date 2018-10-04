FROM gradle:4.9-jdk8

RUN git clone https://github.com/plmarcelo/java_examen1.git project

WORKDIR project

RUN gradle -stacktrace build

#CMD ["gradle", "run"]