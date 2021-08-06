# Spark 2.4 Standalone Mode Install Script

```bash
# os - ubuntu:20.04 LST
# spark version 2.4.8

# spark download
wget http://archive.apache.org/dist/spark/spark-2.4.8/spark-2.4.8-bin-hadoop2.7.tgz
tar xvzf ./spark-2.4.8-bin-hadoop2.7.tgz
rm spark-2.4.8-bin-hadoop2.7.tgz


# symbolic link
ln -s ./spark-2.4.8-bin-hadoop2.7 spark


# conf file
# vi ~/.bashrc
export SPARK_HOME=/home/hadoop/spark-2.4.8-bin-hadoop2.7
export PATH=$PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin


# 주의 spark2.4 버전은 파이썬 3.7까지만 지원
# conf/spark-env.sh
export SPARK_SSH_OPTS="-p 22" 
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
export HADOOP_HOME=$HADOOP_HOME
export HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop
export LD_LIBRARY_PATH=$HADOOP_HOME/lib/native
export SPARK_LOCAL_IP="master_ip"
export PYSPARK_PYTHON=/usr/bin/python3.7
export PYSPARK_DRIVER_PYTHON=/usr/bin/python3.7
export SPARK_MASTER_HOST="master_ip"


# conf/spark-defaults.conf
spark.master                     spark://localhost:7077
```
