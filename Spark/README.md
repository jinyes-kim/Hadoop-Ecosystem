# Spark
#### 스파크는 대규모 데이터 처리를 위한 인메모리 기반 분석 엔진이다. 스파크의 철학은 '빅데이터를 위한 통합 컴퓨팅 엔진과 라이브러리 집합'이다.
## 통합 컴퓨팅 엔진
#### 빅데이터 애플리케이션 개발에 필요한 통합 플랫폼을 제공하고자 하는 핵심 목표를 가지고 있다. 이때 간단한 데이터 읽기에서, SQL 처리, 머신러닝 그리고 스트림 처리까지 다양한 데이터 분석 작업을 같은 연산 엔진과 일관성 있는 API로 수행할 수 있도록 함을 의미한다.
#### 스파크는 통합이라는 관점을 중시하기 때문에 기능의 범위를 컴퓨팅 엔진으로 제한해왔다. 그 결과 스파크는 저장소(HDFS, S3, Kafka.. 등)의 데이터를 연산하는 역할만 수행한다.
## 라이브러리
#### 스파크는 엔진에서 제공하는 표준 라이브러리와 오픈소스 커뮤니티에서 서드파티 패키지로 제공하는 여러 오픈소스 프로젝트의 집합체다. 스파크 코어 엔진은 최초 공개 후 큰 변화가 없지만, 라이브러리는 지속적으로 변화해왔다.

[외부 라이브러리 목록](http://spark-packages.org)

## 스파크 vs 맵리듀스
#### 머신러닝 알고리즘이 데이터를 10회 처리한다고 가정하면, 맵리듀스의 경우 매번 데이터를 처음부터 읽어야한다. 반면에 스파크는 데이터를 메모리에 올려서 연산하는 인메모리 방식이기 때문에 매번 데이터를 처음부터 읽어야할 필요가 없어서 반복적인 데이터 처리 작업에서 맵리듀스보다 속도가 빠르다. 또한 Scala, Java, Python, SQL  다양한 언어를 지원한다.
---
# Spark Tutorial
> -

## 데이터 로드
```scala
//Data Frame
val textFile = spark.read.textFile("REAMD.md")
val csvFile = spark.read.option("header", true).csv("data.csv")

// 스키마 출력
textFile.printSchema()


//RDD
val csvRDD = sc.textFile("data.csv")
```

## 데이터 저장
```scala
csvFile.write.option("header", true).csv("/home/user/test_write.csv")
```


## 데이터 출력

```scala
//DataFrame
csvFile.show()

//RDD
csvRDD.collect().foreach(println)
```

## 필터링
```scala
// gender 컬럼 값이 1이고, age 컬럼이 30 초과인 행만 출력
df.filter(df("gener") === 1 && df("Age") > 30).show()

// gener 컬럼 값이 1인 행을 new_df에 할당
val new_df = df.filter(df("columns") === 1)
```

## 컬럼 이름 변경
```
// Age 컬럼 이름을 new_Age로 변경한 데이터 프레임을 할당
val new_df = df.withColumnRenamed("Age", "new_Age")
```
