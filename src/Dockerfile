# Javaの公式軽量版イメージを使う
FROM openjdk:17-jdk-slim

# ビルドされたjarファイルをアプリにコピー（名前は後で確認）
COPY build/libs/Diary-0.0.1-SNAPSHOT.jar app.jar

# JARファイルを実行
ENTRYPOINT ["java", "-jar", "/app.jar"]
