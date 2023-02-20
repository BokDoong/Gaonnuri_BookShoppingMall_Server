FROM library/ubuntu:1.0
LABEL maintainer="ctce7226@gmail.com"

VOLUME /tmp

# 우리가 정의한 start.sh를 컨테이너 내부의 /bin/bash로 옮겨줍니다.
COPY docker-resources/start.sh /bin/bash
ARG JAR_FILE=./build/libs/ShoppingMall-0.0.1-SNAPSHOT.jar

# 빌드 결과물을 컨테이너 내부로 옮겨줍니다.
COPY ${JAR_FILE} ShoppingMall-0.0.1-SNAPSHOT.jar

# docker network 80포트 매핑
EXPOSE 80
# bash 실행
CMD ["/bin/bash"]
