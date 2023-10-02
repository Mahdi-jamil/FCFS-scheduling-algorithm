package com.algorithm.scheduler;


import java.util.concurrent.Semaphore;

import com.algorithm.main.MainTest;
import com.algorithm.process.Process;

public class FCFSDispatcher implements Runnable {
	private Process runningProcess;
	private ReadyQueue readyQueue;
	private WaitingQueue blocking;
	private Semaphore semaphore;

	public FCFSDispatcher(ReadyQueue queue, WaitingQueue waitingQueue,Semaphore semaphore) {
		readyQueue = queue;
		blocking = waitingQueue;
		this.semaphore=semaphore;
	}

	@Override
	public void run() {
		Integer bT;
		boolean done=false;
		
		while (!done) {
				try {
					runningProcess = readyQueue.getProcess();
				} catch (InterruptedException e) {
					System.out.println("enable to get a process");
				}
				bT = runningProcess.getBurstTime();
				if(bT>=0) {
					CPU.run(bT);
					
					if(runningProcess.IOQEmpty()) {
						runningProcess.generateProcessReport();
						MainTest.NUMBER_OF_PROCESSES--;
						
						if(MainTest.NUMBER_OF_PROCESSES==0) {
							semaphore.release();
							done = true;
						}
						
					}else {
						blocking.addToWaiting(runningProcess);
					}
					
				}else { // Last Task for the process in IO
					runningProcess.generateProcessReport();
					MainTest.NUMBER_OF_PROCESSES--;
					if(MainTest.NUMBER_OF_PROCESSES == 0) {
						semaphore.release();
						done = true;
					}
				}
		}

	}

}
