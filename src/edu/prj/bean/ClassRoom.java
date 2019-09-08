package edu.prj.bean;

import java.io.Serializable;

public class ClassRoom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7941755757257123272L;
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	private Long roomId;
	private String roomName;
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	private Long gradeId;

}
