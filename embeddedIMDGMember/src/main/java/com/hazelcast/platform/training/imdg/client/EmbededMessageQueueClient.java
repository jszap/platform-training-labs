package com.hazelcast.platform.training.imdg.client;

import com.hazelcast.collection.IQueue;
import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * IMDG Exercise 3 - Demonstrate distribute messaging queue using embedded deployment topology
 *
 */
public class EmbededMessageQueueClient {
	
	HazelcastInstance instance;
	
	public EmbededMessageQueueClient(){
		instance =  Hazelcast.newHazelcastInstance(new ClasspathXmlConfig("hazelcast_PRIMARY.xml"));
		Thread messageProducerThread = new Thread(new Runnable(){
			public void run(){
				produceMessages();
			}
		
		}); 
		
		Thread messageConsumerThread = new Thread(new Runnable(){
			public void run(){
				consumeMessages();
			}
		
		}); 
		
        //start producing and consuming messages
		messageProducerThread.start();
		messageConsumerThread.start();
	}
	
	private void produceMessages() {
		try {
			
			IQueue<String> queue=instance.getQueue("TrainingQueue");
			int i=0;
			 while(true){
		        queue.offer("Adding Message "+ Double.toString(Math.random()));
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}				

	private void consumeMessages(){
		IQueue<String> queue=instance.getQueue("TrainingQueue");
		while (true){
			try {
				String value = queue.take();
				System.out.println(" Message Consumed :"+value);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
		
	public static void main(String[] args) {
		EmbededMessageQueueClient messageQueueClient = new EmbededMessageQueueClient();
	}
 }
