package com.craft_demo.bean;

public class TweetFeed extends ResponseBean {
	private String userId;
	private String userName;
	private String tweetValue;

	public TweetFeed(String userId, String userName, String tweetValue) {
		this.userId = userId;
		this.userName = userName;
		this.tweetValue = tweetValue;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getTweetValue() {
		return tweetValue;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setTweetValue(String tweetValue) {
		this.tweetValue = tweetValue;
	}
}
