package com.mansur.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.mansur.rest.model.Link;
import com.mansur.rest.model.Message;
import com.mansur.rest.model.User;

@Path("/usrservice/")
public class UserService {

	private static List<User> USERS = new ArrayList<User>(Arrays.asList(
			new User[] { new User(1, "Juffy"), new User(2, "Samar"), new User(3, "Bebo"), new User(4, "Mansur") }));

	@GET
	@Path("/users")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<User> getUsers(@Context UriInfo uriInfo) {
		USERS.forEach(user -> {
			Message message = MessageService.getUserMessage();
			user.setUserMessage(message);
			String selfUrl = uriInfo.getBaseUriBuilder().path(UserService.class).path(Integer.toString(user.getId()))
					.build().toString();
			String messageUrl = uriInfo.getBaseUriBuilder().path(MessageService.class)
					.path(Integer.toString(message.getId())).build().toString();
			List<Link> links = new ArrayList<Link>(
					Arrays.asList(new Link[] { new Link(selfUrl, "self"), new Link(messageUrl, "message") }));
			user.setLink(links);
		});
		return USERS;
	}

	@GET
	@Path("/{userIndex}")
	//@Produces({ MediaType.APPLICATION_JSON })
	public User getUser(@PathParam(value = "userIndex") int idx, @Context UriInfo uriInfo) {
		return USERS.get(idx);
	}

}
