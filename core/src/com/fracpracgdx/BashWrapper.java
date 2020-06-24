package com.fracpracgdx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

// BashWrapper passes commands to a given bash script and returns the results
//   as a string

// the bash script that BashWrapper runs must take a single argument - a path
//   to a text file containing input
public class BashWrapper {
	String library, script;
	
	// Requires:
	//   library must be of the form "class" where "libclass.so" is installed 
	//     in /usr/include/
	//   script must be a valid executable bash script
	public BashWrapper(String library, String script) {
		this.library = library;
		this.script = script;
		System.loadLibrary(this.library);
		
		// make sure the bash script is runnable
		Process proc = null;
		try {
			proc = Runtime.getRuntime().exec("chmod +x " + this.script);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// runCommand passes input to the script by writing it to a file, passing
	//   the file to the script, and deleting the file upon termination
	// Effects: creates and deletes a file
	// Efficiency: O(n)
	public String runCommand(String command) {
		CommandFile cmdfile = new CommandFile();
		cmdfile.addCommand(command);
		Process proc = null;
		try {
			proc = Runtime.getRuntime().exec(this.script + " " + cmdfile.getFilename()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		String result = new String();
		while (true) {
			String s = new String();
			try {
				s = reader.readLine();
				if (s == null) break;
			} catch (IOException e) {
				System.out.println("BashWrapper.runCommand - IOException");
				e.printStackTrace();
			}
			result += s;
		}
		cmdfile.destroy();
		System.out.println("runCommand result: " + result);
		return result;
	}
}
