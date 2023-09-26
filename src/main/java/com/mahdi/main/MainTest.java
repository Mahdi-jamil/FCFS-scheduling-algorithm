package com.mahdi.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.mahdi.process.Process;
import com.mahdi.scheduler.FCFSDispatcher;
import com.mahdi.scheduler.ReadyQueue;
import com.mahdi.scheduler.WaitingQueue;

public class MainTest {
	
	public static long START= System.currentTimeMillis();
	public static int NUMBER_OF_PROCESSES=0;

	private static volatile Boolean terminate=false;
	
	private static float avgWT;
	
	public static float Main(String[] args) {
		try {
			main(args);
		} catch (FileNotFoundException e) {
			System.err.println("FATAL error");
			e.printStackTrace();
		}
		
		return avgWT;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ReadyQueue readyQueue=new ReadyQueue();
		NewState newState=new NewState(readyQueue);
		WaitingQueue waitingQueue=new WaitingQueue(readyQueue);
		FCFSDispatcher dispatcher=new FCFSDispatcher(readyQueue, waitingQueue);
		List<Process> p=new ArrayList<>();
		
		CreateProcesses(args[0],p);


		for(Process process:p) {
			newState.addToReady(process);
		}
		
		
		
		Thread cpuThread=new Thread(dispatcher);
		cpuThread.start();

		
		while (!terminate); 
		generateReport(p);
		System.out.println("AVG Waiting Time "+ getAvgWaitingTime(p));
		
	}
	
	public static void CreateProcesses(String fileName,List<Process> p) throws FileNotFoundException {
		File file=new File(fileName);
		Scanner in=new Scanner(file);
		
		int count=0;
		
		Process testProcess;
		int id;
		int at;
		LinkedList<Integer> btTest;
		LinkedList<Integer> IOTest;
		
		while (in.hasNextLine()) {
		    String line = in.nextLine();
		    String[] tokens = line.split(" ");

		    if (tokens.length < 2) {
		        continue;
		    }

		    id = Integer.parseInt(tokens[0]);
		    at = Integer.parseInt(tokens[1]);

		    btTest = new LinkedList<>();
		    IOTest = new LinkedList<>();

		    for (int i = 2; i < tokens.length; i++) {
		        // Parse each token as an integer and add it to the appropriate list
		        int value = Integer.parseInt(tokens[i]);
		        if (i % 2 == 0) {
		            btTest.add(value);
		        } else {
		            IOTest.add(value);
		        }
		    }
		    count++;
		    testProcess = new Process(id, at, btTest, IOTest);
		    p.add(testProcess);
		}
		NUMBER_OF_PROCESSES=count;
		in.close();
		
	}
	
	public static void generateReport(List<Process> p) {
		System.out.println("terminated with");
		System.out.println("Pid AT BT   CT     TT     WT");
		for(Process process:p) {
			System.out.println(process.getInfo());
		}
	}
	
	public static float getAvgWaitingTime(List<Process> p) {
		float avg=0;
		for (Process process : p) {
			avg+=process.getWT();
		}
		avgWT=avg/p.size();
		return avgWT;				
		
	}
	
	public static void terminate() {
		MainTest.terminate=Boolean.TRUE;
	}

}
