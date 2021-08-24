> Spark: The Definitive Guide
# 0.Spark
스파크는 대규모 데이터 처리를 위한 인메모리 기반 분석 엔진이다. 스파크의 철학은 '빅데이터를 위한 통합 컴퓨팅 엔진과 라이브러리 집합'이다.
최근 반복적인 연산을 필요로하는 기계학습과 데이터 분석등이 대두되며 


## (1) 통합 컴퓨팅 엔진
빅데이터 애플리케이션 개발에 필요한 통합 플랫폼을 제공하고자 하는 핵심 목표를 가지고 있다. 이때 간단한 데이터 읽기에서, SQL 처리, 머신러닝 그리고 스트림 처리까지 다양한 데이터 분석 작업을 같은 연산 엔진과 일관성 있는 API로 수행할 수 있도록 함을 의미한다.
스파크는 통합이라는 관점을 중시하기 때문에 기능의 범위를 컴퓨팅 엔진으로 제한해왔다. 그 결과 스파크는 저장소(HDFS, S3, Kafka.. 등)의 데이터를 연산하는 역할만 수행한다.
## (2) 라이브러리
스파크는 엔진에서 제공하는 표준 라이브러리와 오픈소스 커뮤니티에서 서드파티 패키지로 제공하는 여러 오픈소스 프로젝트의 집합체다. 스파크 코어 엔진은 최초 공개 후 큰 변화가 없지만, 라이브러리는 지속적으로 변화해왔다.

##### [외부 라이브러리 목록](http://spark-packages.org)
--- 

# 1.Spark vs MapReduce
머신러닝 알고리즘이 데이터를 10회 처리한다고 가정하면, 맵리듀스의 경우 매번 데이터를 처음부터 읽어야한다. 반면에 스파크는 데이터를 메모리에 올려서 연산하는 인메모리 방식이기 때문에 매번 데이터를 처음부터 읽어야할 필요가 없어서 반복적인 데이터 처리 작업에서 맵리듀스보다 속도가 빠르다. 또한 Scala, Java, Python, SQL과 같은 다양한 언어를 지원한다. 

---
# 2.RDD(Resilient Distributed Dataset)
RDD는 Spark의 기본 데이터 구조로 내결함성 및 불변성을 지닌 분산 컬렉션이다. RDD의 각 데이터셋은 클러스터의 다른 노드에서 계산할 수 있는 논리적 파티션으로 나뉘어 있다.
크게 두 가지 작업을 수행할 수 있다. 
1. **Tansformation**: RDD에 Map과 같은 변환 작업을 실시한다. 단 RDD는 변경할 수 없으므로 새로운 RDD를 반환한다.
2. **Action**: RDD에 Reduce와 같은 집계 작업을 수행한다.

# 3.RDD vs DataFrame vs DataSet
> https://www.analyticsvidhya.com/blog/2020/11/what-is-the-difference-between-rdds-dataframes-and-datasets/
## (1) RDD
직역하면 탄력적인 분산 데이터 셋이다. 스파크의 기본 데이터 구조이며 클러스터의 여러 노드에 데이터를 분할 저장 및 병렬로 처리할 수 있다. 주로 데이터 셋에서 로우 레벨의 변환을 수행하려는 경우 사용한다. 특히 RDD는 생성 시 데이터셋의 스키마를 자동으로 유추하지 않기 때문에 직접 지정해주어야 한다. (전처리 부분에서의 제어가 더 필요한 경우 RDD를 사용한다.) 

## (2) Dataframe
RDD의 한계를 극복하기 위해서 스파크 1.3버전에 처음 도입되었다. 행과 열로 구성되어 있으며 RDD와 다르게 자동으로 스키마를 자동으로 생성하며 RDD에서는 불가능했던 런타임 중의 디버깅이 가능하다. 더 나아가 csv, json, avro, hdfs 등 다양한 데이터 포맷을 읽고 쓸 수 있다. 최적화를 위해 catalyst optimizer를 사용한다.

## (3) Dataset
스파크 데이터셋은 데이터프레임 API의 확장으로 RDD와 데이터프레임의 이점을 모두 제공한다. SQL 엔진을 사용하여 데이터셋의 스키마를 자동으로 찾으며 객체지향과 type-safe 인터페이스를 제공한다. 해당 인터페이스는 컴파일링 중에 데이터 유형을 검사하고 유효하지 않다면 에러를 발생시킨다. 정형 데이터와 비정형 데이터를 모두 효율적으로 처리할 수 있다. 단 Python은 아직까지 스파크 데이터 셋을 생성할 수 없다. Dataset은 RDD보다는 빠르지만 Dataframe보다는 약간 느리다.

---

# 4.Spark Basic Command
## (1) 데이터 로드
```scala
//Data Frame
val textFile = spark.read.textFile("REAMD.md")
val csvFile = spark.read.option("header", true).csv("data.csv")


//RDD
val csvRDD = sc.textFile("data.csv")


//Convert to DataFrame
val df = csvRDD.toDF()

//Convert to DataSet
val ds = spark.createDataset(csvRDD)
```

## (2) 데이터 쓰기
```scala
csvFile.write.option("header", true).csv("/home/user/test_write.csv")
```


## (3) 데이터 출력

```scala
// 스키마 출력
textFile.printSchema()

//DataFrame
csvFile.show()

//RDD
csvRDD.collect().foreach(println)

```

## (4) 필터링
```scala
// gender 컬럼 값이 1이고, age 컬럼이 30 초과인 행만 출력
df.filter(df("gener") === 1 && df("Age") > 30).show()

// gener 컬럼 값이 1인 행을 new_df에 할당
val new_df = df.filter(df("columns") === 1)
```

## (5) 컬럼 이름 변경
```
// Age 컬럼 이름을 new_Age로 변경한 데이터 프레임을 할당
val new_df = df.withColumnRenamed("Age", "new_Age")
```
