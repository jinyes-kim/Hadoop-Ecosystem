# Hbase Pseudo-Distributed for Local Testing mode
#### Hadoop 2.9.2 버전을 사용하므로 Hbase는 1.6 버전을 사용한다.
#### 버전 호환 리스트 [참조](https://hbase.apache.org/book.html#quickstart)

# Download
```
wget https://archive.apache.org/dist/hbase/1.6.0/hbase-1.6.0-bin.tar.gz
tar zxvf hbase-1.6.0
ln -s hbase-1.6.0 hbase
```

# hbase-env.sh
```
vi hbase/conf/hbase-env.sh

export JAVA_HOME='Your JAVA HOME path"
```

# hbase-site.xml
```
vi hbase/conf/hbase-site.xml

# 아래 설정 추가

<configuration>
  <property>
    <name>hbase.rootdir</name>
    <value>hdfs://'Your Nmaenode':9000/hbase</value>
  </property>
  <property>
    <name>hbase.cluster.distributed</name>
    <value>true</value>
  </property>
</configuration>
```
