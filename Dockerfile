FROM gradle:jdk8

RUN git clone https://github.com/plmarcelo/java_examen1.git project

WORKDIR project

RUN gradle build

CMD ["gradle", "run"]