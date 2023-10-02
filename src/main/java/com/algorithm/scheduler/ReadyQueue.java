package com.algorithm.scheduler;

import java.util.concurrent.*;

import com.algorithm.process.Process;

public class ReadyQueue {
	private BlockingQueue<Process> queue;

	public ReadyQueue() {
		queue = new LinkedBlockingQueue<>();
	}

	public void addProcess(Process process) {
		try {
			queue.put(process);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public Process getProcess() throws InterruptedException {
		return queue.take();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	
}
