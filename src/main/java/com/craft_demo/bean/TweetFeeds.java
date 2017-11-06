package com.craft_demo.bean;

import java.util.ArrayList;
import java.util.List;

public class TweetFeeds extends ResponseBean {

	private List<TweetFeed> tweetfeeds;

	public List<TweetFeed> getTweetfeeds() {
		return tweetfeeds;
	}

	public void setTweetfeeds(List<TweetFeed> tweetfeeds) {
		this.tweetfeeds = tweetfeeds;
	}

	public void addTweetfeed(TweetFeed tweetfeed) {
		if (this.tweetfeeds == null) {
			this.tweetfeeds = new ArrayList<TweetFeed>();
		}
		this.tweetfeeds.add(tweetfeed);
	}

}
