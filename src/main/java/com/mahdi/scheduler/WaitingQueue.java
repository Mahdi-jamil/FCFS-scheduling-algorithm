package com.mahdi.scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.mahdi.process.Process;

public class WaitingQueue {
	private BlockingQueue<Process> queue;
	private ReadyQueue readyQueue;

	private Thread ioManagerThread; // Reference to the I/O manager thread.

	public WaitingQueue(ReadyQueue q) {
		queue = new LinkedBlockingQueue<>();
		readyQueue = q;
	}

	public void addToWaiting(Process process) {
		try {
			queue.put(process);
			startIOManagerThread();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void startIOManagerThread() {

		ioManagerThread = new Thread(() -> {
				try {

					Process process = queue.take();
					int remainingIOTime = process.getIOTime();
					while (remainingIOTime > 0) {
						Thread.sleep(1000);
						remainingIOTime--;
					}

					// I/O time is up, move the process to the ready queue.
					readyQueue.addProcess(process); // Add to the ready queue.

				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
		});
		ioManagerThread.start();
	}

	
}
