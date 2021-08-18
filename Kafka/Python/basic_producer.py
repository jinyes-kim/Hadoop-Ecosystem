from kafka import KafkaProducer

# Kafka Broker IP
broker = ['192.168.0.2:9092']

# Topic name
topic = 'jinyes'

# Generate producer object
producer = KafkaProducer(bootstrap_servers=broker)


# Send test message
for i in range(100):
    msg = "Hello Kafka {}".format(i)
    producer.send(topic, msg.encode('utf-8'))
    producer.flush()
