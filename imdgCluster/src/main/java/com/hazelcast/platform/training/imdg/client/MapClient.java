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
				doMapPuts();
			}
		}); 
		
		mapWriterThread.start();
		
	}
	
	private void doMapPuts(){
		try {
			//TODO: Get a reference handle to the distribute map
			
			Random randInt = new Random();
			 while(true){
				String key = "key" + Integer.toString(randInt.nextInt(10000));
				String value = Double.toString(Math.random());
				System.out.println("Adding Entry key: "+ key);
				
				//TODO: Put entry into the map				
		        
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
