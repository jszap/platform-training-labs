package com.hazelcast.platform.training.cloud.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * Cloud IMDG Exercise 1 - Simply putting entries into hosted IMDG cloud service
 *
 */
public class CloudMapClient {
	private static HazelcastInstance instance;
	
	/**
	 * Cloud IMDG Exercise 1 - Simply putting entries into hosted IMDG cloud service
	 *
	 */

	public CloudMapClient(){
		CloudMapClient.init();
	}
	
	private static void init() {
		if(instance == null) {
			instance = HazelcastClient.newHazelcastClient(Utils.getCloudClientConfig());
		}
	}
	
	public void putEntriesInToMap(){
		try {
			
			IMap<String,String> map=instance.getMap("TrainingMap");
			int i=0;
			 while(true){
				System.out.println("Adding Entry "+ (++i));
		        map.set(Integer.toString(i),Double.toString(Math.random()));
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CloudMapClient cloudMapClient = new CloudMapClient();
		cloudMapClient.putEntriesInToMap();
	}
 }
