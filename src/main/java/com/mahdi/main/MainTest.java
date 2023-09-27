package com.mahdi.main;

import java.io.FileNotFoundException;

import com.mahdi.process.CreateAndTrackProcesses;
import com.mahdi.scheduler.FCFSDispatcher;
import com.mahdi.scheduler.ReadyQueue;
import com.mahdi.scheduler.WaitingQueue;

public class MainTest {
	
	public static long START= System.currentTimeMillis();
	public static int NUMBER_OF_PROCESSES=0;

	private static volatile Boolean terminate=false;
	public static float avgWT;

	// this function is used by Test Cases 
	// To run from main just edit line 38 to your File path
	public static float Main(String[] args) {
		try {
			main(args);
		} catch (FileNotFoundException e) {
			System.err.println("FATAL error");
			e.printStackTrace();
		}
		
		return avgWT;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ReadyQueue readyQueue=new ReadyQueue();
		WaitingQueue waitingQueue=new WaitingQueue(readyQueue);
		FCFSDispatcher dispatcher=new FCFSDispatcher(readyQueue, waitingQueue);
		
		CreateAndTrackProcesses tracker=new CreateAndTrackProcesses();
		
		tracker.CreateProcesses(args[0]);
		
		tracker.AddToReady(new NewState(readyQueue));
		
		Thread cpuThread=new Thread(dispatcher);
		cpuThread.start();
		while (!terminate); 
		
		tracker.generateReport();
		tracker.getAvgWaitingTime();
		
	}
	
	
	
	public static void terminate() {
		MainTest.terminate=Boolean.TRUE;
	}

}
