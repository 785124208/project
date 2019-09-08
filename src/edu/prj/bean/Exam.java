package edu.prj.bean;

import java.io.Serializable;
import java.sql.Date;

public class Exam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6768810443562708280L;
	/**
	 * 
	 */
	
	private Long examId;
	private Long studentId;
	private Long paperId;
	private Date StartOn;
	private Date EndOn;
	private Long isMark;
	private Float totalScore;
	
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getPaperId() {
		return paperId;
	}
	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}
	public Date getStartOn() {
		return StartOn;
	}
	public void setStartOn(Date startOn) {
		StartOn = startOn;
	}
	public Date getEndOn() {
		return EndOn;
	}
	public void setEndOn(Date endOn) {
		EndOn = endOn;
	}
	public Long getIsMark() {
		return isMark;
	}
	public void setIsMark(Long isMark) {
		this.isMark = isMark;
	}
	public Float getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}
	
}
