
依赖中间件说明
nacos:
    启动命令：sh /Users/key/Java/nacos/bin/startup.sh -m standalone
    数据库：apolloconfigdb、apolloportaldb
    访问地址：http://127.0.0.1:8848/nacos
    命名空间：77105e26-79c5-4497-974c-fdecba4b49d4

kafka
    启动命令：
         cd /Users/key/Java/kafka/kafka_2.13-3.2.0/bin
         ./zookeeper-server-start.sh -daemon ../config/zookeeper.properties
         ./kafka-server-start.sh ../config/server.properties

zipkin:
    启动命令：java -DKAFKA_BOOTSTRAP_SERVERS=127.0.0.1:9092 -jar /Users/key/Java/zipkin/zipkin-server-2.23.16-exec.jar --STORAGE_TYPE=mysql --MYSQL_HOST=127.0.0.1 --MYSQL_TCP_PORT=3306 --MYSQL_USER=root --MYSQL_PASS=123456 --MYSQ
L_DB=zipkin
    访问地址： http://127.0.0.1:9411


skywalking
    启动命令:sh /Users/key/Java/apache-skywalking/bin/startup.sh