package matchpackage.access;

import java.util.ArrayList;

public class Provider extends Customer {

	private String website;
	private String logo;
	private double compensation;
	private String icon;
	private ArrayList<String> keywords;
	private String resume;
	private String plan;
	private ArrayList<String> approvals;
	private ArrayList<String> projects;

	Provider(String name, String password, String website, String logo, double compensation, String icon,
			ArrayList<String> keywords, String resume, String plan) {
		super(name, password);
		setWebsite(website);
		setLogo(logo);
		setCompensation(compensation);
		setIcon(icon);
		setKeywords(keywords);
		setResume(resume);
		setPlan(plan);

	}

	public String getStringProvider() {
		String attributeList = this.getName() + " " + this.website + " " + this.logo + " " + this.compensation + " "
				+ this.icon + " " + this.resume + " " + this.plan;
		return attributeList;
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
