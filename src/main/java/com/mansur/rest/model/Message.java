package com.mansur.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

	public Message(int id, String content) {
		this.id = id;
		this.content = content;
	}

	public Message(Message message) {
		this.id = message.id;
		this.content = message.content;
	}
	
	public Message() {
	}

	private int id;
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		return id + ", " + content;
	}

}
