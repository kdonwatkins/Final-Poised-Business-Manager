import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;			
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;		
import java.util.ArrayList;					// Import all necessary classes


///////////////////////////////////////////////////////////////////////// Main Method /////////////////////////////////////////////////////////////////////////////////////////

public class PoisedManager{

	public static void main(String[] args) {
		
		System.out.println("Hi and welcome to Poise! Let's get you started! You may enter ");
		System.out.println("\nPlease type only a letter. Your options are: "
					+ "\n"
					+ "\n\t-Create a new project and add to text file (A)"				
					+ "\n\t-Create new contractor profile (B) "
					+ "\n\t-Create Project List from text file (C)"	
					+ "\n\t-Edit and finalize project (D)"						// These are the options for the user to choose from
					+ "\n\t-View Unfinished Projects (E)"
					+ "\n\t-View Overdue Projects (F)"
					+ "\n\t-Exit program (G): ");
			
			Scanner input = new Scanner(System.in);
			String userChoice = input.nextLine();
	
			if ((userChoice.toUpperCase()).equals("A")) {				
			
				try {
					addProjectToFile();												// Create Project and add to file
				} catch(Exception e) {
					System.out.println("\nSomething went wrong. Are you sure you entered everything correctly?");
					e.printStackTrace();
				}
			} 
		
			else if ((userChoice.toUpperCase()).equals("B")) {		
				try {
					addPerson();													// Create a Contractor 
				} catch (Exception e) {
					System.out.println("\nSomething went wrong. Are you sure you entered everything correctly?");
					e.printStackTrace();
				}
			} 
		
			else if ((userChoice.toUpperCase()).equals("C")) {
				try {
					readAndCreateProjectList();										// Read the projects from the text file and create Project objects
				} catch (Exception e) {
					System.out.println("\nSomething went wrong. Are you sure you entered everything correctly?");
					e.printStackTrace();
				}
			}
		
			else if ((userChoice.toUpperCase()).equals("D")) {
				try {	
					editAndFinalizeProject();											// Deletes the old project details from text file
					addProjectToFile();												// Re-add the project to the text file with updated details
				} catch (Exception e) {
					System.out.println("\nSomething went wrong. Are you sure you entered everything correctly?");
					e.printStackTrace();
				}
			
			}
		
			else if ((userChoice.toUpperCase()).equals("E")) {
				try {
					viewUnfinishedProjects();											// View all incomplete projects
				} catch (Exception e) {
					System.out.println("\nSomething went wrong. Are you sure you entered everything correctly?");
					e.printStackTrace();
				}
			}
		
			else if ((userChoice.toUpperCase()).equals("F")) {
				try {
					viewOverdueProjects();												// View all overdue projects
				} catch (Exception e) {
					System.out.println("\nSomething went wrong. Are you sure you entered everything correctly?");
					e.printStackTrace();
				}
			}
		
			else if ((userChoice.toUpperCase()).equals("G")) {
				System.exit(0);														// Exit program
			}
	
			System.out.println("\nThank you and goodbye!");
			input.close();
	
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
///////////	Methods:
	
//  1 - Create and add a project to file
//  2 - Create a contractor profile
//  3 - Read and create a project list
//  4 - Edit and finalize a project
// 	5 - View unfinished projects
//  6 - View overdue projects
	
/////////// Certain methods will write any projects and/or changes to the text file after any changes have been made (when necessary)

//////// METHOD 1:
	
	public static void addProjectToFile() {
		
		Scanner input = new Scanner(System.in);								// Capture project details from user
		
		System.out.print("\nGreat let's add an updated project! Enter the project number: ");	
			int typedNumber = input.nextInt();
			input.nextLine();
			
		System.out.print("Enter the project name: ");				
			String typedName = input.nextLine();
			
		System.out.print("Enter building type: ");
			String typedBuilding = input.nextLine();
			
		System.out.print("Enter project physical address: ");
			String typedAddress = input.nextLine();
			
		System.out.print("Enter ERF number: ");
			int typedErf = input.nextInt();
			
		System.out.print("Enter project total fee (number only): ");
			double typedTotal = input.nextDouble();
			
		System.out.print("Enter amount already paid (number only): ");
			double typedPaid = input.nextDouble();
			
		System.out.print("Enter project deadline [DDMMYYYY]: ");
			int typedDeadline = input.nextInt();
			input.nextLine();
			
			boolean defaultIsProjectCompleted = false;
			boolean defaultIsPastDueDate = false;
			int defaultDateCompleted = 0;													// After capturing details, a Project object is created
		
		Project poisedProject = new Project(typedNumber, typedName, typedBuilding, typedAddress, typedErf, 				
				typedTotal, typedPaid, typedDeadline, defaultIsProjectCompleted, defaultIsPastDueDate, defaultDateCompleted);  
		
		System.out.println("\nYour new project has been created! Here it is:");
		System.out.println(poisedProject.toString());
		
		System.out.print("\nWould you like to edit the project deadline before writing to file? [Y/N]: "); 		// User can edit deadline
		String dateChoice = input.nextLine();
		
		if ((dateChoice.toUpperCase()).equals("Y")) {
			
			System.out.print("\nPlease enter the new project deadline [DDMMYYYY]: ");
				int newDueDate = input.nextInt();
				input.nextLine();
				
			poisedProject.setProjectDeadline(newDueDate);
		}
		
		System.out.print("\nWould you like to edit the amount already paid before writing to file? [Y/N]: "); 	
		String paidChoice = input.nextLine();									// User can edit amount already paid
		
		if ((paidChoice.toUpperCase()).equals("Y")) {
			
			System.out.print("\nPlease enter the new fee already paid: ");
				double newAlreadyPaid = input.nextDouble();
				input.nextLine();
				
			poisedProject.setAmountAlreadyPaid(newAlreadyPaid);
			
			}
		
		System.out.println("\nIs the project completed? [Y/N]: ");					// User can mark project as completed
		String typedComplete = input.nextLine();
		
		if ((typedComplete.toUpperCase()).equals("Y")) {
				poisedProject.setIsProjectFinalised(true);
			}
		
		System.out.println("\nIs the project past it's due date? [Y/N]: ");			// User can mark project as overdue
		String typedPastDate = input.nextLine();
		
		if ((typedPastDate.toUpperCase()).equals("Y")) {
			poisedProject.setIsPastDueDate(true);
			}
		
		System.out.println("\nIf the project is complete, please enter the completion date [DDMMYYYY]. If still incomplete, please enter '-1': ");	// User can edit completion date
		int typedCompleteDate = input.nextInt();
		input.nextLine();
			
		poisedProject.setDateCompleted(typedCompleteDate);	
					
		BufferedWriter myOutput;
				try {	
					myOutput = new BufferedWriter(new FileWriter("/Users/admin/Dropbox/Keegan Watkins-53469/"		// New project written to text file is relative format
								+ "Introduction to Software Engineering/Task 24/textForCapstone.txt", true));
						
					myOutput.write(poisedProject.toStringForTextFile());
					System.out.println(poisedProject.toString());
					myOutput.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}	
				input.close();		
	}
		
	
//////// METHOD 2:
	
	public static void addPerson() {	
	
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nGreat let's add a contractor!");					// Capture contractor's details
		System.out.println("\nPlease enter a contractor name: ");
			String typedName = input.nextLine();
			
		System.out.print("Enter contractor job title: ");
			String typedJob = input.nextLine();
			
		System.out.print("Enter contractor phone number: ");
			int typedPhone = input.nextInt();
			input.nextLine();
			
		System.out.print("Enter contractor email address: ");
			String typedEmail = input.nextLine();
			
		System.out.print("Enter contractor physical address: ");
			String typedAddress = input.nextLine();
		
		Person newPerson = new Person(typedName, typedJob, typedPhone, typedEmail, typedAddress); 		// Create contractor object here
		
		System.out.println("\nYour new contractor has been created! Here he/she is:");
		System.out.println(newPerson.toString());
		
		System.out.print("\nWould you like to update your contractor's details? [Y/N]: ");
			String userChoice = input.nextLine();
		
		if ((userChoice.toUpperCase()).equals("Y")) { 					// User can edit contractors details if required
			
			System.out.print("\nPlease enter the new phone number: ");
				int newPhone = input.nextInt();
				input.nextLine();
				
			System.out.print("Please enter the new email address: ");
				String newEmail = input.nextLine();
				
			System.out.print("Please enter the new physical addess: ");
				String newAddress = input.nextLine();
			
			newPerson.setPhoneNumber(newPhone);
			newPerson.setEmailAddress(newEmail);
			newPerson.setPhysicalAddress(newAddress);
			
			System.out.println("\nAll done! Here's the updated contractor:");
			System.out.println(newPerson.toString());
			
		} else {
			System.out.println("\nYou have chosen to not edit the contracter that you just created.");
		}
		input.close();
	} 
	
	
///////// METHOD 3:
	
	public static void readAndCreateProjectList() {		
		
		ArrayList<Project> projectsFromFile = new ArrayList<Project>(); 
		
		try {	
		File projectFile = new File("/Users/admin/Dropbox/Keegan Watkins-53469/"		// Get file to read
				+ "Introduction to Software Engineering/Task 24/textForCapstone.txt");
		
		Scanner readProject = new Scanner(projectFile);							// Read file!
		
		while (readProject.hasNextLine()) {  
			
			String[] currentLine = readProject.nextLine().split(", ");
			
			Project projectFromFile = new Project(Integer.parseInt(currentLine[0]), currentLine[1], currentLine[2], currentLine[3], Integer.parseInt(currentLine[4]), 
					Double.parseDouble(currentLine[5]), Double.parseDouble(currentLine[6]), Integer.parseInt(currentLine[7]), Boolean.parseBoolean(currentLine[8]), 
					Boolean.parseBoolean(currentLine[9]), Integer.parseInt(currentLine[10]));
			
			projectsFromFile.add(projectFromFile); 		// Project objects were created from text file items (Strings parsed into relevant data types) and project list shown to user
			
			} 
		
		System.out.println("\nHere are the projects currently within the text file:\n");
		System.out.println(projectsFromFile.toString());
		readProject.close();
		
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
//////// METHOD 4:
		
		public static void editAndFinalizeProject()  {
			
		Scanner userInput = new Scanner(System.in);			// This Scanner was not closed because of an issue when returning to Main method
																							// The link below describes the issue (I think):
																							// https://stackoverflow.com/questions/13042008/java-util-nosuchelementexception-scanner-reading-user-input
																													
		System.out.println("Please enter the number of the project you would like to change: ");				
		String userTaskChoice = userInput.nextLine();
		
		System.out.println("\nYour chosen project will now be deleted from the text file. Please re-add your project with your new preferred details below: ");
		
			try {		
			BufferedReader readProjects = new BufferedReader(new FileReader("/Users/admin/Dropbox/Keegan Watkins-53469/"		
					+ "Introduction to Software Engineering/Task 24/textForCapstone.txt"));		
			
			String forNewFile = "";
			String readLines = "'";
			
			try {
			while ((readLines = readProjects.readLine()) != null) {  					// Identify the lines which do not share the same project number
				if (!(readLines.contains(userTaskChoice))) {								// Relevant project is removed from text file by identifying all other projects
						 forNewFile += readLines + "\n";
				}
			}
			readProjects.close();
			
			FileWriter outputProjects = new  FileWriter("/Users/admin/Dropbox/Keegan Watkins-53469/"
					+ "Introduction to Software Engineering/Task 24/textForCapstone.txt");
			
			outputProjects.write(forNewFile);					// All the other projects from the text file are re-written back to the text file

			outputProjects.close();
			
			}  catch(FileNotFoundException e) {
				e.printStackTrace();
			}	
			
			}  catch(IOException e ) {
				e.printStackTrace();
			}	
		}

		
//////// METHOD 5:
		
	public static void viewUnfinishedProjects() {
		
		ArrayList<Project> projectsFromFile = new ArrayList<Project>(); 

		System.out.println("\nBelow you will find all unfinished projects (if any): ");
		
		try {
		File projectFile = new File("/Users/admin/Dropbox/Keegan Watkins-53469/"		
				+ "Introduction to Software Engineering/Task 24/textForCapstone.txt");
		
		Scanner readUnfinishedProject = new Scanner(projectFile);				
		
		while (readUnfinishedProject.hasNextLine()) {  
			
			String[] currentLine = readUnfinishedProject.nextLine().split(", ");
			
			if (currentLine[8].equals("false")) {
				Project projectFromFile = new Project(Integer.parseInt(currentLine[0]), currentLine[1], currentLine[2], currentLine[3], Integer.parseInt(currentLine[4]), 
						Double.parseDouble(currentLine[5]), Double.parseDouble(currentLine[6]), Integer.parseInt(currentLine[7]), Boolean.parseBoolean(currentLine[8]), 
						Boolean.parseBoolean(currentLine[9]), Integer.parseInt(currentLine[10]));
				
				projectsFromFile.add(projectFromFile); 					// Unfinished projects from text file are turned into Project objects here
			} 	
		} 
		System.out.println(projectsFromFile);
		readUnfinishedProject.close();
		
		} catch(FileNotFoundException e) {
				e.printStackTrace();
			}	
}
	
	
//////// METHOD 6:
	
	public static void viewOverdueProjects() {
		
		ArrayList<Project> projectsFromFile = new ArrayList<Project>(); 
		
		System.out.println("\nBelow you will see all projects past their due date (if any):.");
		
		try {
		File projectFile = new File("/Users/admin/Dropbox/Keegan Watkins-53469/"		
				+ "Introduction to Software Engineering/Task 24/textForCapstone.txt");
		
		Scanner readOverdueProjects = new Scanner(projectFile);					
		
		while (readOverdueProjects.hasNextLine()) {  
			
			String[] currentLine = readOverdueProjects.nextLine().split(", ");
			
			if (currentLine[9].equals("true")) {
				Project projectFromFile = new Project(Integer.parseInt(currentLine[0]), currentLine[1], currentLine[2], currentLine[3], Integer.parseInt(currentLine[4]), 
						Double.parseDouble(currentLine[5]), Double.parseDouble(currentLine[6]), Integer.parseInt(currentLine[7]), Boolean.parseBoolean(currentLine[8]), 
						Boolean.parseBoolean(currentLine[9]), Integer.parseInt(currentLine[10]));
				
				projectsFromFile.add(projectFromFile); 						// Unfinished projects from text file are turned into Project objects here
			} 
		} 
		System.out.println("\n" + projectsFromFile.toString());
		readOverdueProjects.close();
		
		}catch(FileNotFoundException e) {
				e.printStackTrace();
			}	
	}
}
