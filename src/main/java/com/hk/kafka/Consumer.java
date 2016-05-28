package com.hk.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.metrics.stats.Histogram;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.hk.db.TweetDB;
import com.hk.db.impl.TweetDbImpl;
import com.hk.pojo.TweetPojo;

public class Consumer {

	public static void main(String[] args) throws IOException {
		// set up house-keeping
		ObjectMapper mapper = new ObjectMapper();
		// Histogram stats = new Histogram(1, 10000000, 2);
		// Histogram global = new Histogram(1, 10000000, 2);

		// and the consumer
		KafkaConsumer<String, String> consumer;
		try (InputStream props = Resources.getResource("consumer.props")
				.openStream()) {
			Properties properties = new Properties();
			properties.load(props);
			if (properties.getProperty("group.id") == null) {
				properties.setProperty("group.id",
						"group-" + new Random().nextInt(100000));
			}
			consumer = new KafkaConsumer<>(properties);
		}
		consumer.subscribe(Arrays.asList("twitter-stream1", "fast-messages",
				"summary-markers"));
		// consumer.subscribe(Arrays.asList(a));
		int timeouts = 0;
		// noinspection InfiniteLoopStatement
		try {
			while (true) {
				// read records with a short timeout. If we time out, we don't
				// really care.
				ConsumerRecords<String, String> records = consumer.poll(200);
				if (records.count() == 0) {
					timeouts++;
				} else {
					System.out.printf("Got %d records after %d timeouts\n",
							records.count(), timeouts);
					timeouts = 0;
				}
				TweetDB dbimpl = new TweetDbImpl();
				TweetPojo pojo = new TweetPojo();
				for (ConsumerRecord<String, String> record : records) {
					switch (record.topic()) {
					case "twitter-stream1":
						JsonNode tweet = mapper.readTree(record.value());
//						pojo = mapper.readValue(tweet.toString(), TweetPojo.class);
						
//						Iterator<String> lst =tweet.fieldNames();
//						while (lst.hasNext()){
//							System.out.println(lst.next());
//						}
					
//						for (JsonNode jsonNode : tweet) {
//							tweet.
//							System.out.println(jsonNode.asText(null));
//						}
							
						// switch (tweet.get("type").asText()) {
						// case "twitter":
						System.out.println("Inserting the tweet..");
//						dbimpl.putData(tweet.asText());
						dbimpl.putData(tweet.toString());
						// }

					case "summary-markers":
						break;
					case "twitter-stream":
						break;
					default:
						throw new IllegalStateException(
								"Shouldn't be possible to get message on topic "
										+ record.topic());
					}
				}
			}

		} catch (Exception e) {
			// case t: Throwable => t.printStackTrace();
			e.printStackTrace();
		}
	}
}