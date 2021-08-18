from kafka import KafkaConsumer

# Kafka Broker IP
broker = ['localhost:9092']

# Topic name
topic = "jinyes"

# Generate consumer object
consumer = KafkaConsumer(topic,
                         group_id="group_1",
                         bootstrap_servers=broker,
                         auto_offset_reset="earliest")

# Read message from consumer object
try:
    for msg in consumer:
        print("Topic: {}\nPartition: {}\nOffset: {}\nKey: {}\nValue: {}\n".format(
            msg.topic, msg.partition, msg.offset, msg.key, msg.value.decode("utf-8")))

except KeyboardInterrupt:
    exit(0)
