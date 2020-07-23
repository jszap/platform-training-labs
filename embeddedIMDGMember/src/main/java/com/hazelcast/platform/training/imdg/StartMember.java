package com.hazelcast.platform.training.imdg;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.core.Hazelcast;

/**
 * IMDG Lab 1 - Demonstrate how to configure & create a local cluster of IMDG members 
 *
 */
public class StartMember 
{
    public static void main( String[] args )
    {
        System.out.println( "Starting Hazelcast IMDG member" );
        Hazelcast.newHazelcastInstance(new ClasspathXmlConfig("hazelcast_PRIMARY.xml"));
    }
}
