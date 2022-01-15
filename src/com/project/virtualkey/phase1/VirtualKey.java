package com.project.virtualkey.phase1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class VirtualKey {

	static String dir;
	File folderName;

	// non-parameterized constructor
	public VirtualKey() {
		dir = System.getProperty("user.dir");
		folderName = new File(dir + "/files");

		if(!folderName.exists()) {
			// making the directory
			folderName.mkdirs();
		}
		// Getting the absolute path of the folder name
		System.out.println("\nDirectory: " + folderName.getAbsolutePath());
	}

	private static final String WelcomeScreen = "\n***** Virtual key for your repositories *****\n";
	private static final String MainMenu = "\nSelect an options:\n"
			+ "1. List files\n"
			+ "2. Add, Delete and Search\n"
			+ "3. Exit\n";
	private static final String SecondaryMenu = "\nSelect an option:\n"
			+ "a. Add\n" 
			+ "b. Delete\n"
			+ "c. Search\n"
			+ "d. Return";

	// Displaying the main menu
	void showMainMenu() {
		System.out.println(MainMenu);
		try(Scanner sc = new Scanner(System.in)) {
			int option = sc.nextInt();

			switch(option) {
			case 1: {
				showFiles();
				showMainMenu();
			}
			case 2: {
				showSecondaryMenu();
			}
			case 3: {
				System.out.println("Thank you...");
				System.exit(0);
			}
			default: showMainMenu();
			}

		} catch(Exception ex) {
			System.out.println("Choose 1, 2 or 3");
			showMainMenu();
		}
	}

	// Displaying the secondary menu
	void showSecondaryMenu() {
		System.out.println(SecondaryMenu);
		try(Scanner sc = new Scanner(System.in)) {
			char[] inputChar = sc.nextLine().toLowerCase().trim().toCharArray();
			char option = inputChar[0];

			switch(option) {
			case 'a': {
				System.out.println("Adding file: Enter the file name: ");
				String fileName = sc.next().trim().toLowerCase();
				addFile(fileName);
				break;
			}

			case 'b': {
				System.out.println("Deleting file: Enter the file name: ");
				String fileName = sc.next().trim().toLowerCase();
				deleteFile(fileName);
				break;
			}

			case 'c': {
				System.out.println("Searching file: Enter the file name: ");
				String fileName = sc.next().trim().toLowerCase();
				searchFile(fileName);
				break;
			}

			case 'd': {
				System.out.println("Returning to main menu: ");
				showMainMenu();
				break;
			}

			default: System.out.println("Enter a, b, c or d");

			}
			showSecondaryMenu();
		} catch(Exception ex) {
			System.out.println("Enter a, b, c or d");
			showSecondaryMenu();
		}
	}

	// Showing the files
	void showFiles() {
		if(folderName.list().length == 0) 
			System.out.println("Folder is empty");
		else {
			String[] list = folderName.list();
			System.out.println("The files in " + folderName + " are: ");
			Arrays.sort(list);
			for(String str : list) {
				System.out.println(str);
			}
		}
	}

	// Adding the files
	void addFile(String fileName) throws IOException {

		File path = new File(folderName + "/" +fileName);
		String[] list = folderName.list();
		for(String file : list) {
			if(fileName.equalsIgnoreCase(file)) {
				System.out.println("File " + fileName + " already exists at " + folderName);
				return;
			}
		}

		path.createNewFile();
		System.out.println("File " + fileName + " added to " + folderName);
		System.out.println("The file added successfully");
	}

	// Deleting the files
	void deleteFile(String fileName) {

		File path = new File(folderName + "/" + fileName);
		String[] list = folderName.list();
		for(String file : list) {
			if(fileName.equals(file) && path.delete()) {
				System.out.println("File " + fileName + " deleted from " + folderName);
				System.out.println("The file deleted successfully");
				return;
			}
		}

		System.out.println("Delete operation failed. FILE NOT FOUND");

	}
	
	// Searching the files
	void searchFile(String fileName) {

		String[] list = folderName.list();
		for(String file : list) {
			if(fileName.equals(file)) {
				System.out.println("Found: File " + fileName + " exists at " + folderName);
				return;
			}
		}
		
		System.out.println("File not found");

	}
	
	// Main method
	public static void main(String[] args) {
		System.out.println("Welcome screen\n");
		String appName = "Virtual Key for Your Repositories";
		String devDetails = "Amitkumar Rajanal";
		System.out.println("The application name is: " + appName);
		System.out.println("The developer name is: " + devDetails);
		VirtualKey virtualKey = new VirtualKey();
		virtualKey.showMainMenu();
	}

}
