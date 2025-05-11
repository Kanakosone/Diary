FROM amazoncorretto:17 AS build
COPY ./ /home/app
RUN cd /home/app && ./gradlew build

FROM amazoncorretto:17-alpine
COPY --from=build /home/app/build/libs/Diary1-0.0.1-SNAPSHOT.jar /usr/local/lib/diary.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dfile.encoding=UTF-8","/usr/local/lib/diary.jar"]





## Javaの公式軽量版イメージを使う
#FROM openjdk:17-jdk-slim
#
#COPY ./ /home/app
#RUN cd /home/app && ./gradlew build
#
#WORKDIR /app
#
## Gradleでビルドを実行
#RUN gradle clean build
#
## ビルドされたjarファイルをアプリにコピー（名前は後で確認）
#COPY build/libs/Diary1-0.0.1-SNAPSHOT.jar app.jar
#
## ポート開放
#EXPOSE 8080
#
## JARファイルを実行
#ENTRYPOINT ["java", "-jar", "/app.jar"]
