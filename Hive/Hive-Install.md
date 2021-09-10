# Hive Install Script
*하둡이 설치되어있고 실행중이어야 합니다.*

```
OS: Ubuntu 20.04 LTS
Hive: 2.3.9


# Download Hive 

cd /usr/local/
wget https://archive.apache.org/dist/hive/hive-2.3.9/apache-hive-2.3.9-bin.tar.gz
mv apache-hive-2.3.9 hive


# sudo vi ~/.bashrc
export HIVE_HOME=/usr/local/hive

# make hive-site.sh
cd /usr/local/hive/conf
cp hive-env.sh.template hive-env.sh


# add Hadoop env path & Hive conf path
# vi hive-env.sh
export HADOOP_HOME=$HADOOP_HOME
export HIVE_CONF_DIR=/usr/local/hive/conf


# HDFS SetUp
hadoop fs -mkdir /tmp
hadoop fs -mkdir -p /user/hive/warehouse
hadoop fs -chmod g+w /tmp
hadoop fs -chmod g+w /user/hive/warehouse

# Initialize Schema
/usr/local/hive/bin/schematool -dbType derby -initSchema

# Start Hive
hive
```
