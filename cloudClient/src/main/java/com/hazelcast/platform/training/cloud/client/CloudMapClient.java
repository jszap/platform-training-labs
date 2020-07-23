package com.hazelcast.platform.training.cloud.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * Cloud IMDG Exercise 1 - Simply putting entries into hosted IMDG cloud service
 *
 */
public class CloudMapClient {
	HazelcastInstance instance;
	public CloudMapClient(){
		instance = HazelcastClient.newHazelcastClient(Utils.getCloudClientConfig());
		Thread producer = new Thread(new Runnable(){
			public void run(){
				processInput();
			}
		}); producer.start();
		
	}
	
	private void processInput(){
		try {
			
			IMap<String,String> map=instance.getMap("TrainingMap");
			int i=0;
			 while(true){
				System.out.println("Adding Entry "+ (++i));
		        //String OldValue = map.put(Integer.toString(i),Double.toString(Math.random()));
		        map.set(Integer.toString(i),Double.toString(Math.random()));
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CloudMapClient cloudMapClient = new CloudMapClient();
	}
 }
