package com.java1234.lucha.util;

public class Article {
	
	private Long id;
	private String content;
	private Long postTime;
	private String writer;
	private Long votes;
	
	public Long getVotes() {
		return votes;
	}
	public void setVotes(Long votes) {
		this.votes = votes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getPostTime() {
		return postTime;
	}
	public void setPostTime(Long postTime) {
		this.postTime = postTime;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
