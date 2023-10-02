package com.algorithm.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.algorithm.main.MainTest;
import com.algorithm.main.NewState;

public class CreateAndTrackProcesses {
	private List<Process> processList=new ArrayList<>();
	private float avgWT;
	
	public List<Process> CreateProcesses(String fileName) throws FileNotFoundException {
		File file=new File(fileName);
		Scanner in=new Scanner(file);
		
		Process createdProcess;
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
		    MainTest.NUMBER_OF_PROCESSES++;
		    createdProcess = new Process(id, at, btTest, IOTest);
		    processList.add(createdProcess);
		}
		 
		in.close();
		return processList;
		
	}
	
	public void AddToReady(NewState newState) {
		for(Process process : processList) {
			newState.addToReady(process);
		}
	}
	
	public void generateReport() {
		System.out.println("Terminated with :\n");
		System.out.println("Pid AT BT   CT     TT     WT");
		for (Process process : processList) {
			System.out.println(process.getInfo());
		}
		System.out.println("AVG Waiting Time "+ getAvgWaitingTime());
	}

	private float getAvgWaitingTime() {
		avgWT = 0;
		for (Process process : processList) {
			avgWT+=process.getWT();
		}
		avgWT /= processList.size();
		return avgWT;				
	}
	
	public float getAvgWT() {
		return avgWT;
	}
	

}
