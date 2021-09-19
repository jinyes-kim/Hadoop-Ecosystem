# Hbase Standalone mode install 

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
