# 0. Hive

하둡의 맵리듀스 작업을 실행하는 방법은 여러가지가 존재한다. 

1. 정형, 반정형, 비정형 데이터에 대해 자바 맵리듀스 프로그램을 사용하는 기존 방법
2. Pig를 사용해서 구조화된 데이터와 반구조화된 데이터를 스크립팅(절차적 언어)로 처리하는 방법
3. Hive를 사용해서 구조화된 데이터를 맵리듀스용 쿼리 언어인 HiveQL 사용해서 처리하는 방법

Hive는 하둡에서 구조화된 데이터를 처리하기 위한 데이터 웨어하우스 인프라 도구다. 빅데이터를 요약하고 쿼리 및 분석을 쉽게 만든다. 

## 하이브의 특징
- 스키마는 데이터베이스(하이브 메타스토어)에 저장하고 처리한 데이터는 HDFS에 저장한다.
- [OLAP](https://en.wikipedia.org/wiki/Online_analytical_processing) 용으로 설계되었다.
- HiveQL 이라는 SQL 스타일의 언어를 제공



# 1. Cheat Sheet

샘플 데이터
```bash
# sample.txt

1201,Gopa,45000,Technical manager
1202,Manisha,45000,Proof reader
1203,Masthanvali,40000,Technical writer
1204,Kiran,40000,Hr Admin
1205,Kranthi,3000,Op Admin
```
---

## (1). 데이터베이스 관련
```bash
# 데이터베이스 생성
CREATE DATABASE db_name;

# 데이터베이스 조회
SHOW DATABASES;

# 데이터베이스 지정
USE db_name;

# 데이터베이스 삭제
DROP DATABASE db_name;
```

## (2). 테이블 관련
```bash
# 테이블 생성
CREATE TABLE sample (number INT, name STRING, salary INT, job STRING) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';

# 테이블 리스트 조회
SHOW TABLES;

# 테이블 삭제
DROP TABLE sample;

# 테이블 정보 조회
DESC FORMATTED sample;

# 테이블 데이터 조회
SELECT * FROM sample;

# 테이블 정보 변경
ALTER TABLE name RENAME TO new_name
ALTER TABLE name ADD COLUMNS (col_spec[, col_spec ...])
ALTER TABLE name DROP [COLUMN] column_name
ALTER TABLE name CHANGE column_name new_name new_type
ALTER TABLE name REPLACE COLUMNS (col_spec[, col_spec ...])
```

## (3). HDFS에서 데이터 로드
```bash
# sample.txt 데이터를 HDFS에 저장
hdfs dfs -put sample.txt /

# HDFS에 저장한 데이터를 Hive 테이블로 로드
LOAD DATA INPATH '/sample.txt/' OVERWRITE INTO TABLE sample;
```
로드한 데이터는 HDFS의 /user/hive/warehouse/ 경로에 저장된다.


```bash

```

```bash

```
