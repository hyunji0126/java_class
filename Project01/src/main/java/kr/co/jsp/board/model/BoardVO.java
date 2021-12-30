package kr.co.jsp.board.model;

import java.sql.Timestamp;

public class BoardVO {
	private int boardId;
	private String author;
	private String title;
	private String content;
	private Timestamp regDate;
	

	
	private boolean newMark;

	public BoardVO() {}

	public BoardVO(int boardId, String author, String title, String content, Timestamp regDate) {
		super();
		this.boardId = boardId;
		this.author = author;
		this.title = title;
		this.content = content;
		this.regDate = regDate;

	}

	//boolean 타입 변수의 getter는 is로 시작하는 것이 관례입니다.
	public boolean isNewMark() {
		return newMark;
	}

	public void setNewMark(boolean newMark) {
		this.newMark = newMark;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}


}
