package testvo;

public class StudentScorevo {
	private String cid;
	private String sid;
	private String absence;
	private String usual_performance;
	private String examination_performance;
	private String score;
	private String sname;
	private String cname;
	private float performance;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAbsence() {
		return absence;
	}
	public void setAbsence(String absence) {
		this.absence = absence;
	}
	
	public String getExamination_performance() {
		return examination_performance;
	}
	public void setExamination_performance(String examination_performance) {
		this.examination_performance = examination_performance;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
	
	public String getUsual_performance() {
		return usual_performance;
	}
	public void setUsual_performance(String usual_performance) {
		this.usual_performance = usual_performance;
	}
	public float getPerformance() {
		return performance;
	}
	public void setPerformance(float performance) {
		this.performance = performance;
	}
	public float getAbsenceScore() {
		return absenceScore;
	}
	public void setAbsenceScore(float absenceScore) {
		this.absenceScore = absenceScore;
	}
	public float getHomework() {
		return homework;
	}
	public void setHomework(float homework) {
		this.homework = homework;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	private float absenceScore;
	private float homework;
	private String proportion;
	public StudentScorevo() {
		// TODO Auto-generated constructor stub
	}

}
