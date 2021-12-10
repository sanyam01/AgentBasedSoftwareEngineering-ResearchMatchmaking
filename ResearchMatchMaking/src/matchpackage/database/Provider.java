package matchpackage.database;

import java.util.ArrayList;

import jade.core.AID;

public class Provider extends Customer {

	private String website;
	private String logo;
	private double compensation;
	private String icon = "";
	private ArrayList<String> keywords;
	private String resume;
	private String plan = "";
	private ArrayList<String> approvals;
	private ArrayList<String> projects;

	public Provider(String name, String password, String website, String logo, double compensation,
			ArrayList<String> keywords, String resume) {
		super(name, password);
		setWebsite(website);
		setLogo(logo);
		setCompensation(compensation);
		setKeywords(keywords);
		setResume(resume);

		approvals = new ArrayList<String>();
		projects = new ArrayList<String>();

	}



	public String getStringProvider() {
		String attributeList = this.getName() + "*" + this.website + "*" + this.logo + "*" + this.getKeywords() + "*"
				+ this.resume + "*" + this.compensation + "*" + plan;
		return attributeList;
	}

	public String getStringProviderGuest() {

		String attributeList = this.getName() + "*" + this.website + "*" + this.logo + "*" + this.getKeywords() + "*"
				+ this.resume + "*" + plan;

		return attributeList;

	}

	public void addApproval(String approval) {
		approvals.add(approval);

	}

	public void addProject(String project) {
		approvals.add(project);

	}

	public ArrayList<String> getApprovals() {
		return approvals;
	}

	public ArrayList<String> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<String> projects) {
		this.projects = projects;
	}

	public void setApprovals(ArrayList<String> approvals) {
		this.approvals = approvals;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public double getCompensation() {
		return compensation;
	}

	public void setCompensation(double compensation) {
		this.compensation = compensation;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
