package edu.prj.bean;

import java.io.Serializable;

public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5282049121844513129L;

	private Long questionId;
	private Long qType;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	private String question;
	private String itemA;
	private String itemB;
	private String itemC;
	private String itemD;
	private String itemE;
	private String itemF;
	private String answer;
	private Long subjectid;
	private String tag;
	
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Long getqType() {
		return qType;
	}
	public void setqType(Long qType) {
		this.qType = qType;
	}
	public String getItemA() {
		return itemA;
	}
	public void setItemA(String itemA) {
		this.itemA = itemA;
	}
	public String getItemB() {
		return itemB;
	}
	public void setItemB(String itemB) {
		this.itemB = itemB;
	}
	public String getItemC() {
		return itemC;
	}
	public void setItemC(String itemC) {
		this.itemC = itemC;
	}
	public String getItemD() {
		return itemD;
	}
	public void setItemD(String itemD) {
		this.itemD = itemD;
	}
	public String getItemE() {
		return itemE;
	}
	public void setItemE(String itemE) {
		this.itemE = itemE;
	}
	public String getItemF() {
		return itemF;
	}
	public void setItemF(String itemF) {
		this.itemF = itemF;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Long getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(Long subjectid) {
		this.subjectid = subjectid;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
