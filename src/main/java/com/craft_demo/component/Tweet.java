package com.craft_demo.component;

public class Tweet {

	private String tweetValue;
	private Long timestamp;
	private Tweet nextTweet;
	private User user;

	public Tweet(String tweetValue, User user, Long timestamp) {
		this.tweetValue = tweetValue;
		this.timestamp = timestamp;
		this.user = user;
		this.nextTweet = null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTweetValue() {
		return tweetValue;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public Tweet getNextTweet() {
		return nextTweet;
	}

	public void setTweetValue(String tweetValue) {
		this.tweetValue = tweetValue;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setNextTweet(Tweet nextTweet) {
		this.nextTweet = nextTweet;
	}

}
