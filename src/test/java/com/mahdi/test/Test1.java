package com.mahdi.test;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.mahdi.main.MainTest;

public class Test1 {
	
	@Test
	public void test() {
	    String[] path = {"C:\\scheduling_project\\src\\test\\resources\\Test1.txt"};
	    
	    // Call the Main method and get the return value
	    float returnValue = MainTest.Main(path);
	    
	    // Define the lower and upper bounds of the expected range
	    float lowerBound = 3300.0f;  
	    float upperBound = 4300.0f;  
	    
	    // Check if the return value is within the specified range
	    boolean isInRange = (returnValue >= lowerBound) && (returnValue <= upperBound);
	    // Assert that the return value is within the expected range
	    assertTrue(isInRange);
	}


}
