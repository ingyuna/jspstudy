package dto;

import java.sql.Timestamp;

public class Member {

	private long no;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String phone;
	private Timestamp regdate;
	
	public Member() {}

	public Member(String id, String pw, String name, String email, String phone) {	// 나머지 두개는 뺀 이유 id : sequence로 하면되고, 
																				//regdate systimestamp로 바꿔서 하면 된다.
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

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

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {		// 적재적소에서 값이 잘 넘어갔는지 log 찍어보려고 toString을 넣어줬다.
		return "Member [no=" + no + ", id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", regdate=" + regdate + "]";
	}
	
	
	
	
}
