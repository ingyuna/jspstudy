package controller;

import command.board.BoardCommand;
import command.board.DeleteBoardCommand;
import command.board.DeleteReplyCommand;
import command.board.FindBoardCommand;
import command.board.InsertBoardCommand;
import command.board.InsertBoardPageCommand;
import command.board.SelectListBoardCommand;
import command.board.UpdateBoardCommand;
import command.board.UpdateBoardPageCommand;
import command.board.selectOneBoardCommand;
import insertReply.InsertReplyCommand;

public class BoardCommandMapper {

	// singleton으로 생성
	private static BoardCommandMapper instance = new BoardCommandMapper();
	private BoardCommandMapper() {}
	public static BoardCommandMapper getInstance() {
		if (instance == null) {
			instance = new BoardCommandMapper();
		}
		return instance;
	}
	
	public BoardCommand getCommand(String cmd) {
		BoardCommand command = null;
		switch (cmd) {
		case "selectListBoardPage.b":
			command = new SelectListBoardCommand();
			break;
		case "insertBoardPage.b":
			command = new InsertBoardPageCommand();
			break;
		case "insertBoard.b":
			command = new InsertBoardCommand();
			break;
		case "selectOneBoard.b":
			command = new selectOneBoardCommand();
			break;
		case "findBoard.b":
			command = new FindBoardCommand();
			break;
		case "deleteBoard.b":
			command = new DeleteBoardCommand(); 
			break;
		case "updateBoardPage.b":
			command = new UpdateBoardPageCommand();
			break;
		case "updateBoard.b":
			command = new UpdateBoardCommand();
			break;
		case "insertReply.b":
			command = new InsertReplyCommand();
			break;
		case "deleteReply.b":
			command = new DeleteReplyCommand();
			break;
		}
		return command;
	}
	
}
