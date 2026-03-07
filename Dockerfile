FROM openjdk:22-jdk
LABEL authors="Ayomide Taiwo"
ADD target/Finance.jar finance.jar
ENTRYPOINT ["java","-jar", "/finance.jar"]
EXPOSE 8080
