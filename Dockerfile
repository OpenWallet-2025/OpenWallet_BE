#############################################
# 1️⃣ Build Stage  (JDK 17)
#############################################
FROM eclipse-temurin:17-jdk AS builder

# Work directory
WORKDIR /app

# Gradle wrapper 및 build config만 먼저 복사하여 캐시 최적화
COPY gradlew gradlew.bat ./
COPY gradle gradle
COPY build.gradle settings.gradle ./

# 의존성 캐시용 — src 빼고 먼저 수행
RUN ./gradlew dependencies --no-daemon || true

# 실제 소스 복사
COPY src src

# Boot JAR 빌드
RUN ./gradlew bootJar --no-daemon


#############################################
# 2️⃣ Runtime Stage  (JRE 17)
#############################################
FROM eclipse-temurin:17-jre

WORKDIR /app

# builder에서 만들어진 bootJar만 가져오기
COPY --from=builder /app/build/libs/*.jar app.jar

# 컨테이너 포트
EXPOSE 8080

# 실행 명령
ENTRYPOINT ["java","-jar","/app/app.jar"]
