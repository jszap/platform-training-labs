package com.hazelcast.platform.training.imdg.client;

import java.io.FileNotFoundException;
import java.util.Random;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

/**
 * IMDG Cluster exercise 2 - Demonstrate distribute map using C/S deployment topology
 *
 */

public class MapClient {
	
	HazelcastInstance instance;
	
	public MapClient() throws FileNotFoundException{

		//TODO: Create a Hazelcast client instance to the the running 'PRIMARY' cluster

		Thread mapWriterThread = new Thread(new Runnable(){
			public void run(){
				processInput();
			}
		}); 
		
		mapWriterThread.start();
		
	}
	
	private void processInput(){
		try {
			
			IMap<String,String> map=instance.getMap("TrainnigMap");
			Random randInt = new Random();
			Integer.toString(randInt.nextInt(10000));
			
			 while(true){
				String key = "key"+Integer.toString(randInt.nextInt(10000));
				System.out.println("Adding Entry key: "+ key);
		        //String OldValue = map.put(key,Double.toString(Math.random()));
		        map.set(key,Double.toString(Math.random()));
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		try {
			MapClient mapClient = new MapClient();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
 }
