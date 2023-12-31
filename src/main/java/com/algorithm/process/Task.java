package com.algorithm.process;

public interface Task {
	long calculateCT();
	float calculateTT();
	float calculateWT();
	void calculateCpuBurstTime();
	void generateProcessReport();
}
