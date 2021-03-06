# Hadoop Standalone Mode Install Script 

```bash
# OS: Ubuntu 20.04 LST
# Hadoop: Apache base 2.9.2


# install Java
sudo apt-get install -y openjdk-8-jdk


# vi ~/.bashrc
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
export PATH=$PATH:JAVA_HOME/bin
export CLASS_PATH="."


# vi /etc/hosts 
127.0.0.1 namenode


# add hadoop user 
adduser hadoop
su hadoop


# work dir
cd /home/hadoop


# generate keygen
ssh-keygen


# set ssh key 
ssh-copy-id -i /home/hadoop/.ssh/id_rsa.pub master -p 22


# apache hadoop version 2.9.2
wget https://archive.apache.org/dist/hadoop/common/hadoop-2.9.2/hadoop-2.9.2.tar.gz


# 압축 해제, 심볼릭 링크, 압축 파일 제거
tar xvfz hadoop-2.9.2.tar.gz
ln -s hadoop-2.9.2 hadoop
rm hadoop-2.9.2.tar.gz


#vi /home/hadoop/hadoop/etc/hadoop/hadoop-env.sh
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
export HADOOP_SSH_OPTS="-P 22"
export HADOOP_PID_DIR=/home/hadoop/hadoop-2.9.2/pids


# configure hadoop conf file
vi core-site.xml
vi hdfs-site.xml
vi mapre-site.xml
```
---


- core-site.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
  <property>
    <name>fs.defaultFS</name>
    <value>hdfs://namenode:9000/</value>
  </property>
  <property>
    <name>hadoop.tmp.dir</name>
    <value>/home/hadoop/hadoop/tmp/</value>
  </property>
</configuration>
```


- hdfs-site.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
  <property>
    <name>dfs.replication</name>
    <value>1</value>
  </property>
  <property>
    <name>dfs.http.address</name>
    <value>namenode:50070</value>
  </property>
  <property>
    <name>dfs.secondary.http.address</name>
    <value>namenode:50090</value>
  </property>
  <property>
    <name>dfs.permissions.enabled</name>
    <value>false</value>
  </property>
</configuration>
```


- mapred-site.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
  <property>
    <name>mapred.job.tracker</name>
    <value>namenode:9001</value>
  </property>
</configuration>
```

# Start Hadoop
```
hadoop namenode -format

/hadoop/sbin/start-all.sh
```
