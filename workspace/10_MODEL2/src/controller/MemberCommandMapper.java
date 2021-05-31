package controller;

import command.member.DeleteMemberCommand;
import command.member.LoginCommand;
import command.member.LoginPageCommand;
import command.member.LogoutCommand;
import command.member.MemberCommand;
import command.member.MyPageCommand;
import command.member.UpdateMemberCommand;
import command.member.UpdatePwCommand;
import command.member.UpdatePwPageCommand;
import command.member.joinCommand;
import command.member.joinPageCommand;

public class MemberCommandMapper {

	// singleton pattern
	private static MemberCommandMapper instance = new MemberCommandMapper();
	private MemberCommandMapper() {}
	public static MemberCommandMapper getInstance() {
		if (instance == null) {
			instance = new MemberCommandMapper();
		}
		return instance;		
	}
	
	// Command를 반환하는 메소드
	// 모든 Command는 인터페이스 MemberCommand를 구현하는 클래스이므로,
	// 모든 Command는 MemberCommand 타입을 가진다.
	public MemberCommand getCommand(String cmd) {
		MemberCommand command = null;
		switch (cmd) {
		case "loginPage.m":
			command = new LoginPageCommand();
			break;
		case "joinPage.m":
			command = new joinPageCommand();
			break;
		case "myPage.m":
			command = new MyPageCommand();
			break;
		case "updatePwPage.m":
			command = new UpdatePwPageCommand();
			break;
		case "login.m":
			command = new LoginCommand();
			break;	
		case "logout.m":
			command = new LogoutCommand();
			break;	
		case "join.m":
			command = new joinCommand();
			break;	
		case "updatePw.m":
			command = new UpdatePwCommand();
			break;
		case "updateMember.m":
			command = new UpdateMemberCommand();
			break;
		case "deleteMember.m":
			command = new DeleteMemberCommand();
			break;
		}
		return command;
	}
}
