package edu.prj.bean;

import java.io.Serializable;

public class PaperItem  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7239359726191712176L;
	private Long itemId;
	private Long paperId;
	private Long questionId;
	private String answer;
	private Float score;
	private String question;
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getPaperId() {
		return paperId;
	}
	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "PaperItem [itemId=" + itemId + ", paperId=" + paperId + ", questionId=" + questionId + ", answer="
				+ answer + ", score=" + score + ", question=" + question + ", qusetion=" + question + "]";
	}
	
}
