package com.mansur.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;

import com.mansur.rest.model.Message;

@Path("/msgservice/")
public class MessageService {

	private static List<Message> MESSAGES = new ArrayList<Message>(Arrays
			.asList(new Message[] { new Message(1, "This is a 1st message"), new Message(2, "This is a 2nd message"),
					new Message(3, "This is a 3rd message"), new Message(4, "This is a 4th message") }));

	@GET
	@Path("/messages")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Message> getMessages(@PathParam("user") String name) {
		// String message = "Hello " + name + "! This is your rest application";
		// return Response.status(200).entity(message).build();
		// return message;
		return MESSAGES;
	}

	@POST
	@Path("/messages")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Message addMessage(Message message) {
		int size = MESSAGES.size();
		message.setId(size + 1);
		MESSAGES.add(message);
		return message;
	}

}
