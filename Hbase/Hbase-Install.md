# Hbase Standalone mode install 
#### Hadoop 2.9.2 사용하므로 Hbase는 1.6 버전을 사용한다.
#### 버전 호환 리스트는 [참조](https://hbase.apache.org/book.html#quickstart)
```
# Download
wget https://archive.apache.org/dist/hbase/hbase-0.98.8/hbase-0.98.8-hadoop2-bin.tar.gz
tar zxvf hbase-0.98.8-hadoop2-bin.tar.gz
ln -s hbase-0.98.8-hadoop2-bin hbase
```

# hbase-site.xml
```
<configuration>
   //Here you have to set the path where you want HBase to store its files.
   <property>
      <name>hbase.rootdir</name>
      <value>file:/home/hadoop/hBase/HFiles</value>
   </property>
	
   //Here you have to set the path where you want HBase to store its built in zookeeper  files.
   <property>
      <name>hbase.zookeeper.property.dataDir</name>
      <value>/home/hadoop/hbase/zookeeper</value>
   </property>
</configuration>
```
