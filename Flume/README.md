# 0. Flume
플럼은 로그 데이터를 효율적이고 안정적으로 수집할 수 있는 프레임워크다.

하둡 에코시스템에서 버퍼 스토리지 역할로 사용한다. 스트리밍 데이터를 카프카로 받고 HDFS에 바로 저장하면 블록이 수도없이 생긴다. 따라서 일정 크기 이상까지 데이터를 모은 뒤 HDFS에 저장해야한다. 해당 버퍼 역할을 위해 사용할 수 있는 것이 플럼이다.

# 1. 아키텍처

> [이미지 출처](https://flume.apache.org/FlumeUserGuide.html)

![UserGuide_image00](https://user-images.githubusercontent.com/54028026/130549810-1ab56aba-977d-406e-bd5b-5905e15d7151.png)

플럼은 source, channel, sink 세 가지로 구성되어있다.

- source는 플럼이 데이터를 받아오는 곳이다.
- channel은 플럼 자체로 메모리 기반과 디스크 기반 중에서 선택할 수 있다.
- sink는 최종적으로 데이터를 전송하는 곳이다. 

플럼은 위 세가지 사항을 명시한 conf 파일을 작성하여 실행한다.


# 2. conf 파일 작성 요령

### 0) 역할과 변수명 선언하기
```bash
agent1.sources = kafka-source
agent1.channels = memory-channel
agent1.sinks = hdfs-sink
```
agent.conf 파일에서 각각의 역할이 사용할 변수명을 선언해준다.

---

### 1) source

```bash
# sources
agent1.sources.kafka-source.type = org.apache.flume.source.kafka.KafkaSource
agent1.sources.kafka-source.kafka.bootstrap.servers = broker:9092 
agent1.sources.kafka-source.channels = memory-channel
agent1.sources.kafka-source.kafka.topics = Test_Topic
```

agent1은 conf 파일에서 사용자가 임의로 선언하는 agent의 이름이다. agent1.sources.kafka-source의 의미는 사용자가 정의한 kafka-source라는 source를 객체로 사용하겠다는 의미다. 그러므로 agent1.sources.kafka-source라는 패턴으로 flume source에 대한 옵션을 설정할 수 있다.

- agent1.sources.kafka-source.type

    source의 타입을 지정한다. 위에서는 아파치 카프카.

- agent1.sources.kafka-source.kafka.bootstrap.servers

    source인 카프카의 브로커 서버를 알려준다.

- agent1.sources.kafka-source.channels

    소스로부터 받아온 데이터를 저장할 채널이 어디인지 알려준다.

- agent1.sources.kafka-source.kafka.topics

    카프카로부터 어떤 토픽의 데이터를 받아올 건지 알려준다.

---

## 2) channel

플럼이 이벤트 데이터를 저장하는 공간

```bash
# channels
agent1.channels.memory-channel.type = memory
agent1.channels.memory-channel.capacity = 10000
agent1.channels.memory-channel.transactionCapacity = 1000
```

- agent1.channels.memory-channel.type

    agent1의 채널인 memory-channel의 타입이 memory임을 선언했다.

- agent1.channels.memory-channel.capacity

     최대 큐 사이즈로 채널에 저장될 수 있는 최대 이벤트 수

- agent1.channels.memory-channel.transactionCapacity

    채널이 한 번의 트랜잭션에서 source 또는 sink에서 가져올 수 있는 최대 이벤트 수

---

### 3) sink

플럼이 최종적으로 데이터를 저장하는 곳

```bash
# sinks
agent1.sinks.hdfs-sink.type = hdfs
agent1.sinks.hdfs-sink.hdfs.path = hdfs://hadoop-datanode:9000/home/%{topic}
agent1.sinks.hdfs-sink.channel = memory-channel
agent1.sinks.hdfs-sink.hdfs.filePrefix = %Y-%m-%d
agent1.sinks.hdfs-sink.hdfs.fileSuffix = .json
agent1.sinks.hdfs-sink.hdfs.fileType = DataStream
agent1.sinks.hdfs-sink.hdfs.rollInterval = 60
agent1.sinks.hdfs-sink.hdfs.rollSize = 134217728
agent1.sinks.hdfs-sink.hdfs.rollCount = 0
```

sink의 이름은 앞서 정의한 hdfs-sink이므로 agent1.sinks.hdfs-sink를 이용하여 sink에 대한 설정을 진행할 수 있다.

- agent1.sinks.hdfs-sink.type

    sink의 타입은 hdfs로 정의한다.

- agent1.sinks.hdfs-sink.hdfs.path

    hdfs 주소를 알려준다.

- agent1.sinks.hdfs-sink.channel

    sink에 저장할 데이터를 가져오는 channel이 memory-channel임을 알려준다.

- agent1.sinks.hdfs-sink.hdfs.filePrefix

    파일 명을 정의한다. 위에서는 $Y-%m-%d로 날짜가 파일명이 되도록 설정했다.

- agent1.sinks.hdfs-sink.hdfs.fileSuffix

   파일의 확장자를 정의한다.

- agent1.sinks.hdfs-sink.hdfs.fileType

    저장할 데이터의 파일 형식을 설정할 수 있다 위에서 지정한 DataStream은 출력할 파일을 압축하지 않으므로 별도의 codeC를 설정할 필요가 없다. 

    CompressedStream은 별도의 codeC 옵션을 지정해야 한다.

    

- agent1.sinks.hdfs-sink.hdfs.rollInterval

    데이터가 들어오지 않을 경우 최대 기다리는 시간이다. ***데이터가 들어오지 않은 상태라면 시간을 초과하더라도 플럼이 빈 파일을 만들지 않는다.**

- agent1.sinks.hdfs-sink.hdfs.rollSize

   파일의 최대 사이즈를 결정한다.  134217728 == 128MB 

- agent1.sinks.hdfs-sink.hdfs.rollCount

    파일에 최대 라인 수를 지정한다. 0으로 설정하면 무제한이다.

---

# 3. sample-agent.conf

```bash
# sources
agent1.sources.kafka-source.type = org.apache.flume.source.kafka.KafkaSource
agent1.sources.kafka-source.kafka.bootstrap.servers = broker:9092
agent1.sources.kafka-source.channels = memory-channel
agent1.sources.kafka-source.kafka.topics = Test_Topic

# channels
agent1.channels.memory-channel.type = memory
agent1.channels.memory-channel.capacity = 10000
agent1.channels.memory-channel.transactionCapacity = 1000

# sinks
agent1.sinks.hdfs-sink.type = hdfs
agent1.sinks.hdfs-sink.hdfs.path = hdfs://hadoop-datanode:9000/home/%{topic}/
agent1.sinks.hdfs-sink.channel = memory-channel
agent1.sinks.hdfs-sink.hdfs.filePrefix = %Y-%m-%d
agent1.sinks.hdfs-sink.hdfs.fileSuffix = .json
agent1.sinks.hdfs-sink.hdfs.fileType = DataStream
agent1.sinks.hdfs-sink.hdfs.rollInterval = 60
agent1.sinks.hdfs-sink.hdfs.rollSize = 134217728
agent1.sinks.hdfs-sink.hdfs.rollCount = 0

# alias
agent1.sources = kafka-source
agent1.channels = memory-channel
agent1.sinks = hdfs-sink
```

---

# 4. 단일 에이전트로 다중 채널 구성하기

카프카는 토픽을 기준으로 데이터를 구분할 수 있다. 위 파일에서는 카프카의 Test_Topic에 해당하는 데이터만 채널에 저장하여 싱크로 전송한다. 
처음 agent.conf 파일에서 각각의 역할과 변수명을 저장했다. 이를 응용하면 싱글 에이전트에서 다중 채널을 구성할 수 있다.

- 단일 소스, 채널, 싱크

```bash
agent1.sources = kafka-source
agent1.channels = memory-channel
agent1.sinks = hdfs-sink
```

- 다중 소스, 채널, 싱크

```bash
agent1.sources = kafka-source-Topic1 kafka-source-Topic2
agent1.channels = memory-channel1 memory-channel2
agent1.sinks = hdfs-sink1 hdfs-sink2
```

---
  
