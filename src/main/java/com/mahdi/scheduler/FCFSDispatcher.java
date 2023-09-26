package com.mahdi.scheduler;


import com.mahdi.main.MainTest;
import com.mahdi.process.Process;

public class FCFSDispatcher implements Runnable {
	private Process runningProcess;
	private ReadyQueue readyQueue;
	private WaitingQueue blocking;

	public FCFSDispatcher(ReadyQueue queue, WaitingQueue waitingQueue) {
		readyQueue = queue;
		blocking = waitingQueue;
	}

	@Override
	public void run() {
		Integer bT;
		while (!Thread.currentThread().isInterrupted()) {
				try {
					runningProcess=readyQueue.getProcess();
				} catch (InterruptedException e) {
					System.out.println("enable to get a process");
				}
			
				bT=runningProcess.getBurstTime();
				// This is Burst Time
				
				if(bT>=0) {
					CPU.run(bT);
					//Finished BT
					
					if(runningProcess.IOQEmpty()) {
						runningProcess.generateProcessReport();
						MainTest.NUMBER_OF_PROCESSES--;
						
						if(MainTest.NUMBER_OF_PROCESSES==0) {
							MainTest.terminate();
							Thread.currentThread().interrupt();
						}
						
						
					}else {
						blocking.addToWaiting(runningProcess);
					}
				}else {
					runningProcess.generateProcessReport();
					MainTest.NUMBER_OF_PROCESSES--;
					if(MainTest.NUMBER_OF_PROCESSES==0) {
						MainTest.terminate();
						Thread.currentThread().interrupt();
					}
				}
		}

	}

}
