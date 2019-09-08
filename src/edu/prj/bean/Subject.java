package edu.prj.bean;

import java.io.Serializable;

public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1290457774829228996L;
	
	private Long subjectId;
	private String subjectName;
	private String STATUS;
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
}
