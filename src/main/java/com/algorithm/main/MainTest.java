package com.algorithm.main;

import java.io.FileNotFoundException;
import java.util.concurrent.Semaphore;

import com.algorithm.process.CreateAndTrackProcesses;
import com.algorithm.scheduler.FCFSDispatcher;
import com.algorithm.scheduler.ReadyQueue;
import com.algorithm.scheduler.WaitingQueue;

public class MainTest {
	
	public static long START= System.currentTimeMillis();
	public static int NUMBER_OF_PROCESSES=0;
	private static Semaphore semaphore=new Semaphore(0);
	private static CreateAndTrackProcesses tracker;

	// This function is used by Test Cases 
	// To run from main just edit line 37 to your File path
	public static float Main(String[] args) {
		try {
			main(args);
		} catch (FileNotFoundException e) {
			System.err.println("FATAL error");
			e.printStackTrace();
		}
		return tracker.getAvgWT();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ReadyQueue readyQueue=new ReadyQueue();
		WaitingQueue waitingQueue=new WaitingQueue(readyQueue);
		FCFSDispatcher dispatcher=new FCFSDispatcher(readyQueue, waitingQueue,semaphore);
		
		tracker=new CreateAndTrackProcesses();
		tracker.CreateProcesses(args[0]);
		tracker.AddToReady(new NewState(readyQueue));
		
		Thread cpuThread=new Thread(dispatcher);
		cpuThread.start();
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		tracker.generateReport();
	}
	
	

}
