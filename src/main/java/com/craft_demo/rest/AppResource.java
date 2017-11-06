package com.craft_demo.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.craft_demo.bean.TweetFeeds;
import com.craft_demo.component.Twitter;

@Path("/")
@Component
public class AppResource {

	private static final String POST_SUCCESS = "{\"result\":\"Post Success\"}";
	@Autowired
	private Twitter twitter;

	// To get feeds for logged in user
	@GET
	@Produces({ MediaType.APPLICATION_JSON })

	public Response getFeed(@DefaultValue("100") @QueryParam("count") int count,
			@DefaultValue("0") @QueryParam("start") int start) {
		try {
			TweetFeeds tf = twitter.getFeeds(getAuthenticatedUser(), start, count);
			return Response.status(Response.Status.OK).entity(tf.toJsonString()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e + "\"}").build();
		}
	}

	// To post a tweet by logged in user
	@POST
	@Path("tweet")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postTweet(String tweetValue) {
		try {
			twitter.postTweet(getAuthenticatedUser(), tweetValue);
			return Response.status(Response.Status.OK).entity(POST_SUCCESS).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e + "\"}").build();
		}
	}

	// To follow a user by logged in user
	@POST
	@Path("follow/{followee}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response follow(@PathParam("followee") String followee) {
		try {
			twitter.followUser(getAuthenticatedUser(), followee);
			return Response.status(Response.Status.OK).entity(POST_SUCCESS).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e + "\"}").build();
		}
	}

	// To unfollow a user by logged in user
	@POST
	@Path("unfollow/{followee}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response unfollow(@PathParam("followee") String followee) {
		try {
			twitter.unfollowUser(getAuthenticatedUser(), followee);
			return Response.status(Response.Status.OK).entity(POST_SUCCESS).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e + "\"}").build();
		}
	}

	private String getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		return userId;
	}

}
