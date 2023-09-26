package com.mahdi.scheduler;


public class CPU {
	
	public static void run(long at) {
		try {
			Thread.sleep(at*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
