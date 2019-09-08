package edu.prj.bean;

import java.io.Serializable;
/**
 * 管理员
 * @author LEE HAO KKK
 *
 */
public class Manager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8579564501684014709L;

	public Manager() {
		
	}

	private Long managertId;
	private String loginName;
	private String loginPwd;
	private String nickName;
	private Long isDisabled;

	public Long getManagertId() {
		return managertId;
	}
	public void setManagertId(Long managertId) {
		this.managertId = managertId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Long getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Long isDisabled) {
		this.isDisabled = isDisabled;
	}


	 
}
