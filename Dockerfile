# Javaの公式軽量版イメージを使う
FROM openjdk:17-jdk-slim

WORKDIR /app

# Gradleでビルドを実行
RUN gradle clean build

# ビルドされたjarファイルをアプリにコピー（名前は後で確認）
COPY build/libs/Diary1-0.0.1-SNAPSHOT.jar app.jar

# ポート開放
EXPOSE 8080

# JARファイルを実行
ENTRYPOINT ["java", "-jar", "/app.jar"]
