package com.craft_demo.component;

import java.util.HashSet;
import java.util.Set;

public class User {

	private int tweetCount;
	private String userId;
	private String userName;
	private Set<String> followees;

	private Tweet orderedTweetListHead;

	public User(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
		this.followees = new HashSet<String>();
		this.followees.add(userId);
		this.tweetCount = 0;
	}

	public int getTweetCount() {
		return tweetCount;
	}


	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public Set<String> getFollowees() {
		return followees;
	}

	public Tweet getOrderedTweetListHead() {
		return orderedTweetListHead;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFollowees(Set<String> followees) {
		this.followees.addAll(followees);
	}

	public void addFollowee(String followee) {
		this.followees.add(followee);
	}

	public void post(Tweet tweet) {
		if (this.orderedTweetListHead == null) {
			this.orderedTweetListHead = tweet;
		} else {
			tweet.setNextTweet(this.orderedTweetListHead);
			this.orderedTweetListHead = tweet;
		}
		this.tweetCount++;
	}
}
