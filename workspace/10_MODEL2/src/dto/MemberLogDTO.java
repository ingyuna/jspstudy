package dto;

import oracle.sql.DATE;

public class MemberLogDTO {

	private long no;
	private String id;
	private DATE login;
	private DATE logout;
	
	public MemberLogDTO() {}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DATE getLogin() {
		return login;
	}

	public void setLogin(DATE login) {
		this.login = login;
	}

	public DATE getLogout() {
		return logout;
	}

	public void setLogout(DATE logout) {
		this.logout = logout;
	}
	
	
	
	
}
