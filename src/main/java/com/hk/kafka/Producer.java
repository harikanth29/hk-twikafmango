package com.hk.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.google.common.io.Resources;
import com.hk.twitter.stream.TwitterStream;
import com.hk.twitter.stream.TwitterStream;

public class Producer {

	public static void main(String[] args) throws IOException {
		// set up the producer
		KafkaProducer<String, String> producer;
		try (InputStream props = Resources.getResource("producer.props")
				.openStream()) {
			Properties properties = new Properties();
			properties.load(props);
			producer = new KafkaProducer<>(properties);
		}
		try {
			while (true) {

				String msg = TwitterStream.run("vmeA5182eAVJpmhYZCIpS5SNy",
						"PcJluFLzt9Sj3q5xM3XGVJutSNzM6CqOR2lhGD8tnspfwVMzTB",
						"4771549746-m1p2baqYLlmO3ixEWoEc2Erj4JPqaGzWVWmg4Ap",
						"zcp6dYqqmqs5h2LpCT0jtjmHpyxQ4UlzBnyz8iYdMhbG0");
				// send lots of messages
				System.out.println("Producing the msg......");
				producer.send(new ProducerRecord<String, String>(
						"twitter-stream1", msg));
				System.out.println("Producer successful.." + msg);
			}
		} catch (Throwable throwable) {
			System.out.printf("%s", throwable.getStackTrace());
		} finally {
			producer.close();
		}
	}

}
