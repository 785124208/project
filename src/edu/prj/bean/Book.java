package edu.prj.bean;

import java.io.Serializable;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8435043463135097058L;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private Long id;
	private String name;
	public Long getRemainnum() {
		return remainnum;
	}
	public void setRemainnum(Long remainnum) {
		this.remainnum = remainnum;
	}
	public Long getBorrownum() {
		return borrownum;
	}
	public void setBorrownum(Long borrownum) {
		this.borrownum = borrownum;
	}
	private Long remainnum;
	private Long borrownum;
}
