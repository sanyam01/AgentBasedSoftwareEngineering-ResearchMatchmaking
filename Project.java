package matchpackage.database;

public class Project {

	String projectName;
	Provider  provider;
	Customer customer;
	private String description;
	private String deadline;
	private int progress;
	private int estimatedTime;
	

	public Project(String project_Name,  String description, String deadline, int progress, int estimatedtime){
		this.projectName=project_Name;		
		this.description=description;
		this.deadline=deadline;
		this.progress=progress;
		this.estimatedTime=estimatedtime;
	}
	
	public void setName(String project_Name) {
		this.projectName=project_Name;
	}
	
	public String getName() {
		return projectName;
	}
	
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setProgress(int progress) {
		this.progress=progress;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public void setDeadline(String deadline) {
		this.deadline=deadline;
	}
	public String getDeadline() {
		return deadline;
	}
	
	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime=estimatedTime;
	}
	
	public int getEstimatedTime() {
		return estimatedTime;
	}
}
