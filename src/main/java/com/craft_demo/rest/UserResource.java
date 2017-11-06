package com.craft_demo.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.craft_demo.component.Twitter;


/* IMPORTANT : This resource is to populate the data in in-memory database during App start.
 * App resource is the main resource class.
 */

@Path("/user")
@Component
public class UserResource {

	private static final String POST_SUCCESS = "{\"result\":\"Post Success\"}";
	@Autowired
	private Twitter twitter;

	// To post a tweet by logged in user
	@POST
	@Path("/{userId}/tweet")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postTweet(@PathParam("userId") String userId, String tweetValue) {
		try {
			twitter.postTweet(userId, tweetValue);
			return Response.status(Response.Status.OK).entity(POST_SUCCESS).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e + "\"}").build();
		}
	}

	// To follow a user by logged in user
	@POST
	@Path("/{userId}/follow/{followee}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response follow(@PathParam("userId") String userId, @PathParam("followee") String followee) {
		try {
			twitter.followUser(userId, followee);
			return Response.status(Response.Status.OK).entity(POST_SUCCESS).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e + "\"}").build();
		}
	}

	// To unfollow a user by logged in user
	@POST
	@Path("/{userId}/unfollow/{followee}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response unfollow(@PathParam("userId") String userId, @PathParam("followee") String followee) {
		try {
			twitter.unfollowUser(userId, followee);
			return Response.status(Response.Status.OK).entity(POST_SUCCESS).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e + "\"}").build();
		}
	}

	/*
	 * To add a user to in memory DB. No use of this method in case of
	 * persistant db
	 */
	@POST
	@Path("/{userId}/username/{userName}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addUser(@PathParam("userId") String userId, @PathParam("userName") String userName) {
		try {
			twitter.registerUser(userId, userName);
			return Response.status(Response.Status.OK).entity(POST_SUCCESS).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + userId + "\"}")
					.build();
		}
	}

}
