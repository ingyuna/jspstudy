package controller;

import command.member.LoginCommand;
import command.member.LoginPageCommand;
import command.member.LogoutCommand;
import command.member.MemberCommand;
import command.member.joinCommand;
import command.member.joinPageCommand;

public class CommandMapper {

	// singleton pattern
	private static CommandMapper instance = new CommandMapper();
	private CommandMapper() {}
	public static CommandMapper getInstance() {
		if (instance == null) {
			instance = new CommandMapper();
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
		case "login.m":
			command = new LoginCommand();
			break;	
		case "logout.m":
			command = new LogoutCommand();
			break;	
		case "join.m":
			command = new joinCommand();
			break;	
		}
		return command;
	}
}
