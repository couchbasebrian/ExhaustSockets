package com.couchbase.support;

import java.util.ArrayList;
import java.util.Set;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

// Brian Williams
// Created September 24, 2015
// Revised October 20, 2015
// Originally Developed using couchbase-client 1.4.10 via Maven Central
// This version developed with Couchbase Java Client 2.1.4

// This creates about 1000 connections on the Couchbase server UI

public class ExhaustSockets2 {

	public static void printMemoryInfo() {

		Runtime r = Runtime.getRuntime();
		
		long totalMemory = r.totalMemory();
		long freeMemory  = r.freeMemory();
	    long maxMemory   = r.maxMemory();
				
	    long usedMemory = totalMemory - freeMemory;
	    
	    long totalFree  = maxMemory - usedMemory;
	    
	    System.out.println("Used: " + usedMemory + " Free: " + freeMemory + " Total: " + totalMemory + " Max: " + maxMemory + " TotalFree: " + totalFree);
	    
	}
	
	  private static void countThreads() {
		  Set<Thread> threadSet = Thread.getAllStackTraces().keySet();		  
		  System.out.println("There are " + threadSet.size() + " threads");
		  //for (Thread t : threadSet ) {
			//  System.out.println(t.getName());
		  //}
	  }

	public static void main(String[] args) {

		System.out.println("Welcome to ExhaustSockets2");

		boolean keepGoing = true;
		
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();

		int iteration = 0;
		int successCount = 0;
		int failCount = 0;

		CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder().kvEndpoints(1000).build();
		
		Cluster cluster = null;

		while ( keepGoing) {

				System.out.println("Iteration " + iteration++);
				
				printMemoryInfo();
				countThreads();
				
			    try {
				// Your IP address goes here
			    	cluster = CouchbaseCluster.create(env, "10.10.20.10");
			    
				// Your bucket name goes here	
			    	Bucket bucket = cluster.openBucket("BUCKETNAME");
			    	
			    	clusters.add(cluster);
			    	
			    	successCount++;
			      
			    } catch (Exception e) {
			    	System.out.println("Error connecting to Couchbase: " + e.getMessage());
			    	failCount++;
			    	keepGoing = false;
			    }
		
			    System.out.println("  Success: " + successCount + " Failed: " + failCount);
			    
			    try {
			    	Thread.sleep(100);
			    }
			    catch (InterruptedException e) {
			    	System.out.println("My sleep was interrupted");
			    }
		}
			
		// Sleep for 10 seconds
	    try {
	    	Thread.sleep(10000);
	    }
	    catch (InterruptedException e) {
	    	System.out.println("My sleep was interrupted");
	    }
			
		System.out.println("Leaving ExhaustSockets2.  Goodbye.");

	}

}


// eof
