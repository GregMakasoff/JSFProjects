package com.corejsf;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;

@Named("problemReport")
@ConversationScoped
public class ProblemReport implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private Conversation conversation;
	@Inject
	private Problem problem;

	private String header;
	private int number;
	private Date dateOfIssue;
	private String project;
	private String originator;
	private String title;
	private String modulesAffected;
	private String description;
	private String recommendSolution;

	private enum LevelsPriority {
		LOW, MEDIUM, HIGH, CRITICAL
	}
	private enum LevelsSeverity {
		LOW, MEDIUM, HIGH
	}

	private String priority;
	private String severity;
	private Date dateAssigned;
	private Date dateFixed;
	private boolean scoped = true;
	private LevelsPriority levelP = LevelsPriority.LOW;
	private LevelsSeverity levelS = LevelsSeverity.LOW;
	
	private static Map<String, LevelsPriority> selectedLevelPriority;
	public Map<String, LevelsPriority> getLevelsPriority() {
		return selectedLevelPriority;
	}
	static {
		selectedLevelPriority = new LinkedHashMap<String, LevelsPriority>();
		selectedLevelPriority.put("LOW", LevelsPriority.LOW); // label, value
		selectedLevelPriority.put("MEDIUM", LevelsPriority.MEDIUM);
		selectedLevelPriority.put("HIGH", LevelsPriority.HIGH);
		selectedLevelPriority.put("CRITICAL", LevelsPriority.CRITICAL);
	};

	public Map<String, LevelsPriority> getSelectedLevelPriority() {
		return selectedLevelPriority;
	}
	public void setSelectedLevelPriority(Map<String, LevelsPriority> selectedLevel) {
		ProblemReport.selectedLevelPriority = selectedLevel;
	}
	
	public Map<String, LevelsSeverity> getLevelsSeverity() {
		return selectedLevelSeverity;
	}

	private static Map<String, LevelsSeverity> selectedLevelSeverity;
	static {
		selectedLevelSeverity = new LinkedHashMap<String, LevelsSeverity>();
		selectedLevelSeverity.put("LOW", LevelsSeverity.LOW); // label, value
		selectedLevelSeverity.put("MEDIUM", LevelsSeverity.MEDIUM);
		selectedLevelSeverity.put("HIGH", LevelsSeverity.HIGH);
	};

	public Map<String, LevelsSeverity> getSelectedLevelSeverity() {
		return selectedLevelSeverity;
	}

	public void setSelectedLevelSeverity(Map<String, LevelsSeverity> selectedLevel) {
		ProblemReport.selectedLevelSeverity = selectedLevel;
	}

	/**
	 * @return the problem
	 */
	public Problem getProblem() {
		return problem;
	}

	/**
	 * @param problem the problem to set
	 */
	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the dateOfIssue
	 */
	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	/**
	 * @param dateOfIssue the dateOfIssue to set
	 */
	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the originator
	 */
	public String getOriginator() {
		return originator;
	}

	/**
	 * @param originator the originator to set
	 */
	public void setOriginator(String originator) {
		this.originator = originator;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the modulesAffected
	 */
	public String getModulesAffected() {
		return modulesAffected;
	}

	/**
	 * @param modulesAffected the modulesAffected to set
	 */
	public void setModulesAffected(String modulesAffected) {
		this.modulesAffected = modulesAffected;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the recommendSolution
	 */
	public String getRecommendSolution() {
		return recommendSolution;
	}

	/**
	 * @param recommendSolution the recommendSolution to set
	 */
	public void setRecommendSolution(String recommendSolution) {
		this.recommendSolution = recommendSolution;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the severity
	 */
	public String getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	/**
	 * @return the dateAssigned
	 */
	public Date getDateAssigned() {
		return dateAssigned;
	}

	/**
	 * @param dateAssigned the dateAssigned to set
	 */
	public void setDateAssigned(Date dateAssigned) {
		this.dateAssigned = dateAssigned;
	}

	/**
	 * @return the dateFixed
	 */
	public Date getDateFixed() {
		return dateFixed;
	}

	/**
	 * @param dateFixed the dateFixed to set
	 */
	public void setDateFixed(Date dateFixed) {
		this.dateFixed = dateFixed;
	}

	/**
	 * Start the conversation
	 */
	public void start() {
		conversation.begin();
	}
	/**
	 * End the conversation
	 */
	public void end() {
		conversation.end();
	}
	
	public String originalPage() {
		header = "Software Problem Report";
		return "enterProblem";
	}
	
	/**
	 * Starts conversation and navigates to display page
	 */
	public String display() {
		if (!scoped)
			header = "Engineer Change Request";
		else
			header = "Software Problem Report";
		return "ProblemReport";
	}

	/**
	 * Ends conversation and sets all values to null
	 */
	public String restart() {
		end();

		return "enterProblem";
	}

	@PostConstruct
	public void construct() {
		start();
		header = "Software Problem Report";
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the scoped
	 */
	public boolean getScoped() {
		return scoped;
	}

	/**
	 * @param scoped set the scope
	 */
	public void setScoped(boolean scoped) {
		this.scoped = scoped;
	}
	/**
	 * @return the levelP
	 */
	public LevelsPriority getLevelP() {
		return levelP;
	}
	/**
	 * @param levelP the levelP to set
	 */
	public void setLevelP(LevelsPriority levelP) {
		this.levelP = levelP;
	}
	/**
	 * @return the levelS
	 */
	public LevelsSeverity getLevelS() {
		return levelS;
	}
	/**
	 * @param levelS the levelS to set
	 */
	public void setLevelS(LevelsSeverity levelS) {
		this.levelS = levelS;
	}

}
