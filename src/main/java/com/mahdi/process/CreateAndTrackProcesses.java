package com.mahdi.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.mahdi.main.MainTest;
import com.mahdi.main.NewState;

public class CreateAndTrackProcesses {
	private List<Process> p=new ArrayList<>();

	
	public List<Process> CreateProcesses(String fileName) throws FileNotFoundException {
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
		MainTest.NUMBER_OF_PROCESSES=count;
		in.close();
		return p;
		
	}
	
	
	public void generateReport() {
		System.out.println("terminated with");
		System.out.println("Pid AT BT   CT     TT     WT");
		for(Process process:p) {
			System.out.println(process.getInfo());
		}
	}
	
	
	public float getAvgWaitingTime() {
		float avg=0;
		for (Process process : p) {
			avg+=process.getWT();
		}
		MainTest.avgWT=avg/p.size();
		System.out.println("AVG Waiting Time "+ MainTest.avgWT);
		return MainTest.avgWT;				
	}
	
	
	
	public void AddToReady(NewState newState) {
		for(Process process:p) {
			newState.addToReady(process);
		}
	}
}
