package com.algorithm.main;

import java.util.Timer;
import java.util.TimerTask;

import com.algorithm.process.Process;
import com.algorithm.scheduler.ReadyQueue;

public class NewState {
	
	private ReadyQueue readyQueue;
    
    public NewState(ReadyQueue readyQueue) {
    	this.readyQueue=readyQueue;
    }
    
    public void addToReady(Process process) {
    	Timer timer = new Timer();
          
          timer.schedule(new TimerTask() {
              @Override
              public void run() {
            	  System.out.println("process "+process.toString()+" arrives");
                  readyQueue.addProcess(process);
                  timer.cancel();
              }
          },process.getArrivalTime()*1000 );
      }
		
}
