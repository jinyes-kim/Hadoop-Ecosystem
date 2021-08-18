from kafka import KafkaProducer

# Kafka Broker IP
broker = ['localhost:9092']

# Topic name
topicName = 'jinyes'

# Generate producer object
producer = KafkaProducer(bootstrap_servers=broker)


# Send test message
for i in range(1000):
    msg = "Hello Kafka {}".format(i)
    producer.send(topicName, msg.encode('utf-8'))
