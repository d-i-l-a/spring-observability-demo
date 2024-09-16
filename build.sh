mvn clean package -DskipTests

docker build -t spring-application-image .

docker compose up -d spring-boot-application --remove-orphans