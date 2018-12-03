package com.mansur.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	private int id;
	private String name;
	private Message userMessage;
	private List<Link> links;

	public List<Link> getLinks() {
		return links;
	}

	public void setLink(List<Link> links) {
		this.links = links;
	}

	public Message getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(Message userMessage) {
		this.userMessage = userMessage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public User(User user) {
		this.id = user.id;
		this.name = user.name;
	}

	public User() {
	}
}
