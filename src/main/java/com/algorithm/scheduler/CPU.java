package com.algorithm.scheduler;

public class CPU {
	
	public static void run(long bt) {
		try {
			Thread.sleep(bt*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
