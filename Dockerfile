# Javaの公式軽量版イメージを使う
FROM openjdk:17-jdk-slim

WORKDIR /app

# ビルドされたjarファイルをアプリにコピー（名前は後で確認）
COPY build/libs/*.jar app.jar

# ポート開放
EXPOSE 8080

# JARファイルを実行
ENTRYPOINT ["java", "-jar", "/app.jar"]
