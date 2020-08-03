package com.hazelcast.platform.training.cloud.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

/**
 * Cloud IMDG Exercise 2 - Messaging putting message into IMDG cloud service queue
 *
 */

public class CloudMessageQueueClient {
	
	HazelcastInstance instance;
	
	public CloudMessageQueueClient(){
		instance = HazelcastClient.newHazelcastClient(Utils.getCloudClientConfig());
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
			//TODO: using h
			IQueue<String> queue=instance.getQueue("TrainingQueue");
			int i=0;
			 while(true){
		        queue.offer("Adding Message "+ (++i));
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
		CloudMessageQueueClient cloudMessageQueueClient = new CloudMessageQueueClient();
	}
 }
