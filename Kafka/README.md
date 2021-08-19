
# 0. Kafka
##### 카프카는 이벤트 기반 분산 스트리밍 플랫폼이다. 비슷한 서비스로 GCP의 Pub/Sub, AWS의 Kinessis 등이 있다.

# 1. Unified
> [이미지 출처](https://engineering.linkedin.com/distributed-systems/log-what-every-software-engineer-should-know-about-real-time-datas-unifying
)
#### 카프카를 사용하면 파편화된 데이터 파이프라인을 중앙집중화된 심플한 아키텍처로 개선할 수 있다.
## (1). 복잡한 데이터 파이프라인 예시
![datapipeline_complex](https://user-images.githubusercontent.com/54028026/129995521-97881455-1d9a-4e80-93d1-68a3415282a7.png)
#### 위 구조는 각각의 프로세스가 파편화되어있는 상태로 데이터 처리 프로세스가 추가되는 경우 점점 더 구조가 복잡해질 수 밖에 없다.

## (2). 중앙집중화된 데이터 파이프라인 예시
![datapipeline_simple](https://user-images.githubusercontent.com/54028026/129995526-cecac563-4d9f-4ebc-92df-35ec94787536.png)
####  카프카를 사용하는 아키텍처는 카프카 브로커가 모든 프로세스의 매개자 역할을 하는데, 이 과정에서 아키텍처가 매우 단순해진다. 
#### RabiitMQ, Apahce Pulsa 같은 메시징 큐는 데이터를 읽으면 해당 데이터를 사용한 것으로 간주하고 삭제한다. 반면 카프카는 데이터를 요청해서 사용하더라도 보존 정책에 의해 일정 시간 동안 보관한다. 즉 중앙집중화된 아키텍처이고, 데이터의 재요청이 가능하다. 따라서 수집한 데이터는 일단 카프카 클러스터에 전송한다. 그리고 데이터가 필요하면 카프카 클러스터에 요청만 하면된다. 이러한 카프카의 중앙집중화된 아키텍처는 기존의 1:1 매칭의 개발 스타일에서 발생하던 커플링 이슈를 해결하였다.

# 2. Producer & Consumer
#### 카프카는 발행과 구독이라는 두 가지 행위가 존재한다. Producer는 데이터를 브로커에 전송한다. 그리고 Consumer는 카프카 브로커에 데이터를 요청하여 전달 받는다. 이때 원하는 데이터를 구분하기 위해 Topic이라는 개념이 존재한다. 
#### 유튜브 채널을 구독한다고 생각해보자. 크리에이터(Producer)는 유튜브(Broker)에 존재한 자신의 채널(Topic)컨텐츠를 업로드(Produce)한다. 그리고 구독자(Consumer)는 자신이 원하는 채널(Topic)을 구독한다(Consume). 이러한 과정에서 프로듀서(유튜버)는 컨슈머(구독자)에게 컨텐츠(Data)를 전달하는 문제를 고려할 필요가 없다. 유튜브(브로커)에 업로드만 하면 채널의 컨텐츠를 소비하고자 하는 구독자가 플랫폼(카프카 브로커)에 요청하여 사용한다. 
![jinyes-kafka-producer consumer](https://user-images.githubusercontent.com/54028026/129997367-2c2a5f9e-f308-4ece-b359-1b9023dc925b.png)



# 3. Partition
> [이미지 출처](https://kafka.apache.org/081/documentation.html)

![log_anatomy](https://user-images.githubusercontent.com/54028026/129998890-d8579ddd-6fe7-4b95-a505-2b6f510486ea.png)
#### 카프카 브로커는 프로듀서로부터 전달 받은 데이터를 큐에 저장한다. 이를 파티션이라고 부른다. 다중 큐를 지원하여 파티션의 개수를 늘릴 수 있다. 이것이 카프카의 병렬 처리 메커니즘이다. (단 파티션의 개수가 2개 이상일 때 카프카는 offset의 순서를 보장하지 않는다.)
#### 카프카에는 __commit_offset__이라는 토픽이 존재하는데, 해당 토픽은 데이터를 요청한 컨슈머와 그룹별로 특정 토픽의 몇 번째 offset까지 데이터를 읽었는지 저장한다. 이를 통해 특정 토픽을 여러 컨슈머와 그룹에서 요청하더라도 중복없이 데이터를 전달한다.  
---
Reference
>[아파치 카프카 애플리케이션 프로그래밍 with 자바](http://www.yes24.com/Product/Goods/99122569?OzSrank=1)
