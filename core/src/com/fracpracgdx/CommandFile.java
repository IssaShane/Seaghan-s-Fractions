package com.fracpracgdx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CommandFile {
	private String filename;
	private File content;
	
	public CommandFile(String filename) {
		this.filename = filename;
		this.content = new File(filename);
		FileWriter writer;
		try {
			writer = new FileWriter(filename);
			writer.write("");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CommandFile() {
		this.filename = "AAAAAAAAA.command";
		this.content = new File(filename);
		FileWriter writer;
		try {
			writer = new FileWriter(filename);
			writer.write("");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addCommand(String command) {
		try {
			FileWriter writer = new FileWriter(this.filename, true);
			writer.write(command + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getFilename() { return filename; }
	
	public void destroy() {
		this.content.delete();
	}
}
