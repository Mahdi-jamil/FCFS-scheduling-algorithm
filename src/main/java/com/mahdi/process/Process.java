package com.mahdi.process;

import java.util.Queue;

import com.mahdi.main.MainTest;

public class Process implements Task{
	private final int pid;
	private final int ArrivalTime;
	
	Queue<Integer> BurstTime;
	Queue<Integer> IORequest;
	
	private String finalInfo;
	volatile int TotalBurstTime;
	private float WT;
	
	public float getWT() {
		return WT;
	}
	
	
	public Process(int pid, int arrivalTime, Queue<Integer> burstTime, Queue<Integer> iORequest) {
		this.pid = pid;
		ArrivalTime = arrivalTime;
		BurstTime = burstTime;
		IORequest = iORequest;
		calculateCpuBurstTime();
	}
	
	
	
	public Integer getBurstTime() {
		if(BurstTime.isEmpty())return -1;
		return BurstTime.poll();
	}
	public Integer getIOTime() {
		if(IORequest.isEmpty())return null;
		return IORequest.poll();
	}
	
	public Boolean BurstQEmpty() {
		if(BurstTime.isEmpty())return true;
		return false;
	}
	public Boolean IOQEmpty() {
		if(IORequest.isEmpty())return true;
		return false;
	}
	
	

	@Override
	public long calculateCT() {
		return System.currentTimeMillis()-MainTest.START;
	}

	@Override
	public float calculateTT() {
		return calculateCT()-ArrivalTime*1000;
	}

	@Override
	public float calculateWT() {
		WT=calculateTT()-(TotalBurstTime*1000);
		
		return WT;
	}

	@Override
	public void calculateCpuBurstTime() {
		TotalBurstTime=BurstTime.stream()
						.mapToInt(v->v.intValue())
						.sum();
		
	}


	@Override
	public void generateProcessReport() {
		
		finalInfo="P"+pid+"  "+ArrivalTime+" "+TotalBurstTime+" "+calculateCT() +
				" "+calculateTT()+" "+calculateWT();
		
	}
	
	public String getInfo() {
		return finalInfo;
	}

	public int getArrivalTime() {
		return ArrivalTime;
	}
	
	
	
	public String toString() {
		return pid+"";
	}
	
	
	
	
	
	
	
	
	
	
	
}
