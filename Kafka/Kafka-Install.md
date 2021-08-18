# Kafka Standalone Mode Install Script
```
# OS: Ubunt 20.04 LST
# Kafka: Apache base 2.5.0

# install Java
sudo apt-get install -y openjdk-8-jdk


# vi ~/.bashrc
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
export PATH=$PATH:JAVA_HOME/bin
export CLASS_PATH="."


# Dwonload Apache Kafka 2.5.0
wget http://archive.apache.org/dist/kafka/2.5.0/kafka_2.12-2.5.0.tgz


# Deompression
tar xvzf kafka


# vi config/server.properties




