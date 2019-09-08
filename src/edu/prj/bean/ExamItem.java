package edu.prj.bean;

import java.io.Serializable;

public class ExamItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4610568576782898971L;
	
	private Long itemId;
	private Long examId;
	private Long questionId;
	private String stuAnswer;
	private String stdAnswer;
	private Float stdScore;
	private Long markResult;
	private Float gainScore;
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getStuAnswer() {
		return stuAnswer;
	}
	public void setStuAnswer(String stuAnswer) {
		this.stuAnswer = stuAnswer;
	}
	public String getStdAnswer() {
		return stdAnswer;
	}
	public void setStdAnswer(String stdAnswer) {
		this.stdAnswer = stdAnswer;
	}
	public Float getStdScore() {
		return stdScore;
	}
	public void setStdScore(Float stdScore) {
		this.stdScore = stdScore;
	}
	public Long getMarkResult() {
		return markResult;
	}
	public void setMarkResult(Long markResult) {
		this.markResult = markResult;
	}
	public Float getGainScore() {
		return gainScore;
	}
	public void setGainScore(Float gainScore) {
		this.gainScore = gainScore;
	}
}
