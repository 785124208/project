package edu.prj.bean;

import java.io.Serializable;
import java.util.Date;

public class Paper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1225285897163802545L;
	
	private Long paperId;
	private String paperName;
	private Float totalScore;
	private Float perScore;
	private Long questionNum;
	private Long examMinute;
	private Date StartOn;
	private Date EndOn;
	private Long HasGenerate;
	
	public Long getPaperId() {
		return paperId;
	}
	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Float getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}
	public Float getPerScore() {
		return perScore;
	}
	public void setPerScore(Float perScore) {
		this.perScore = perScore;
	}
	public Long getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(Long questionNum) {
		this.questionNum = questionNum;
	}
	public Long getExamMinute() {
		return examMinute;
	}
	public void setExamMinute(Long examMinute) {
		this.examMinute = examMinute;
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
	public Long getHasGenerate() {
		return HasGenerate;
	}
	public void setHasGenerate(Long hasGenerate) {
		HasGenerate = hasGenerate;
	}

}
