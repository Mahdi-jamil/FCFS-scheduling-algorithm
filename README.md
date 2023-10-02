# FCFS Scheduling Algorithm Simulator

Simulate the First-Come-First-Serve (FCFS) scheduling algorithm for process management in Java.


## Table of Contents

- [Overview](#overview)
- [Usage](#usage)
- [Example Output](#example-output)



# Overview
This Java program simulates the First-Come-First-Serve (FCFS) scheduling algorithm for process management. FCFS is a non-preemptive scheduling algorithm where processes are executed in the order they arrive in the queue. This program is designed to simulate the real-life behavior of FCFS, meaning it will not provide immediate output but will wait for all processes to finish execution.


## Usage

1. Clone this repository to your local machine.
2. Navigate to the project directory.
3. Change Path in line 36 in MainTest to any file.txt and just Run Main ( Short Method or Do 4,5,6 )
4. Modify the choosen test.txt file to your own processes ( default : Test1.txt )
5. Modify pom.xml to your choosen Test.java file (  default : Test1.java )
6. Run the program using Maven.(mvn clean test or run pom.xml as clean test)
7. Review the program's output.


###  Example Output


```markdown
process 1 arrives
process 3 arrives
process 4 arrives
process 2 arrives
process 5 arrives
terminated with
Pid AT BT   CT     TT     WT
P1  0 4 4675 4675.0 675.0
P2  3 1 10702 7702.0 6702.0
P3  1 2 6676 5676.0 3676.0
P4  2 3 9688 7688.0 4688.0
P5  5 4 14713 9713.0 5713.0
AVG Waiting Time 4290.8
```





