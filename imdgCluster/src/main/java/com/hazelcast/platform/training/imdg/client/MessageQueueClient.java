package com.hazelcast.platform.training.imdg.client;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;

/**
 * IMDG Cluster exercise 3 - Demonstrate distribute messaging queue using embedded deployment topology
 *
 */
public class MessageQueueClient {
	
	HazelcastInstance instance;
	
	public MessageQueueClient(){
		
		//TODO: Create a Hazelcast client instance to the the running 'PRIMARY' cluster
		
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
			
			//TODO: Get a reference handle to the distribute message Queue
			IQueue<String> trainingQueue = null;
			int i=0;
			 while(true){
				String message  =  "Adding Message "+ (++i);
				trainingQueue.offer(message);
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}				

	private void consumeMessages(){
		IQueue<String> trainingQueue = instance.getQueue("TrainingQueue");
		while (true){
			try {
				String incomingMessage = trainingQueue.take();
				System.out.println(" Message Consumed :"+incomingMessage);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
		
	public static void main(String[] args) {
		MessageQueueClient messageQueueClient = new MessageQueueClient();
	}
 }
