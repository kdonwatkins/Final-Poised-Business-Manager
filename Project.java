
public class Project {
	
	int number;
	String name;
	String buildingType;
	String physicalAddress;
	int erfNumber;
	double totalFeeCharged;
	double amountAlreadyPaid;
	int projectDeadline;
	boolean isProjectFinalised; 
	boolean isPastDueDate; 
	int dateCompleted;
	
	// Constructor for Project
	
	public Project(int number, String name, String buildingType, String physicalAddress, int erfNumber, double totalFeeCharged, 
			double amountAlreadyPaid, int projectDeadline,  boolean isProjectFinalised, boolean isPastDueDate, int dateCompleted) {
		
		this.number = number;
		this.name = name;
		this.buildingType = buildingType;
		this.physicalAddress = physicalAddress;
		this.erfNumber = erfNumber;
		this.totalFeeCharged = totalFeeCharged;
		this.amountAlreadyPaid = amountAlreadyPaid;
		this.projectDeadline = projectDeadline; 
		this.isProjectFinalised = isProjectFinalised;
		this.isPastDueDate = isPastDueDate;
		this.dateCompleted = dateCompleted;
	}
	
	// Normal toString method (for viewing in program):
	
	public String toString() {
		String proper = "";
		proper += "\nProject Number: " + this.number;
		proper += "\nProject Name: " + this.name;
		proper += "\nBuilding Type: " + this.buildingType;
		proper += "\nPhysical Address: " + this.physicalAddress;
		proper += "\nERF Number: " + this.erfNumber;
		proper += "\nTotal Fee Charged: R" + this.totalFeeCharged;
		proper += "\nAmount Already Paid: R" + this.amountAlreadyPaid;
		proper += "\nProject Deadline: " + this.projectDeadline;
		proper += "\nProject is finalised: " + this.isProjectFinalised;
		proper += "\nProject past due date: " + this.isPastDueDate;
		proper += "\nDate Completed: " + this.dateCompleted;
		proper += "\n";
		return proper;
	}
	
	// Other toString method (for the format of the text file)
	
	public String toStringForTextFile() {
		String textForFile = this.number + ", " + this.name + ", " + this.buildingType + ", " + this.physicalAddress + ", " + this.erfNumber + ", " + this.totalFeeCharged + 
				", " + this.amountAlreadyPaid + ", " + this.projectDeadline + ", " + this.isProjectFinalised + ", " + this.isPastDueDate + ", " + this.dateCompleted;
		return textForFile;
	}
	
	// Five setters here for editing the project fields
	
	public void setAmountAlreadyPaid(double newAlreadyPaid) {
		this.amountAlreadyPaid = newAlreadyPaid;
	}

	public void setProjectDeadline(int newProjectDeadline) {			
		this.projectDeadline = newProjectDeadline;
	}
	
	public void setIsProjectFinalised(boolean isProjectFinalised) {
		this.isProjectFinalised = isProjectFinalised;
	}
	
	public void setIsPastDueDate(boolean isPastDueDate) {
		this.isPastDueDate = isPastDueDate;
	}
	
	public void setDateCompleted(int newDateCompleted) {
		this.dateCompleted = newDateCompleted;
	}
	
}
