package com.craft_demo.component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.craft_demo.bean.TweetFeed;
import com.craft_demo.bean.TweetFeeds;

@Service
public class Twitter {

	private Map<String, User> twitterUsers;

	public Twitter() {
		this.twitterUsers = new HashMap<String, User>();
	}

	public void registerUser(String userId, String userName) {
		if (!this.twitterUsers.containsKey(userId)) {
			User user = new User(userId, userName);
			this.twitterUsers.put(userId, user);
		} else {
			throw new RuntimeException("User is Already registered");
		}
	}

	public void postTweet(String userId, String tweetValue) {
		User user = this.twitterUsers.get(userId);
		Tweet tweet = new Tweet(tweetValue, user, System.currentTimeMillis());
		user.post(tweet);
	}

	public void followUser(String userId, String followeeId) {

		if (this.twitterUsers.containsKey(userId) && this.twitterUsers.containsKey(followeeId)) {
			this.twitterUsers.get(userId).addFollowee(followeeId);
		} else {

			throw new RuntimeException("User or Follower not exist");
		}
	}

	public void unfollowUser(String userId, String followeeId) {

		if (this.twitterUsers.containsKey(userId) && this.twitterUsers.containsKey(followeeId)) {
			Set<String> followes = this.twitterUsers.get(userId).getFollowees();
			if (followes != null && !followes.isEmpty()) {
				followes.remove(followeeId);
				return;
			}
		}
		throw new RuntimeException("User or Follower not exist");
	}

	public TweetFeeds getFeeds(String userId, int start, int count) {

		TweetFeeds tweetFeeds = new TweetFeeds();

		Set<String> followeeList = this.twitterUsers.get(userId).getFollowees();

		if (followeeList.isEmpty()) {
			return tweetFeeds;
		}

		int totalFeeds = 0;

		Queue<Tweet> queue = new PriorityQueue<Tweet>(followeeList.size(), new Comparator<Tweet>() {

			public int compare(Tweet o1, Tweet o2) {
				if (o1.getTimestamp() > o2.getTimestamp()) {
					return 1;
				} else if (o1.getTimestamp() < o2.getTimestamp()) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		for (String followeeId : followeeList) {
			User user = this.twitterUsers.get(followeeId);
			totalFeeds += user.getTweetCount();
			Tweet orderedTweetHead = user.getOrderedTweetListHead();
			if (orderedTweetHead != null) {
				queue.add(orderedTweetHead);
			}
		}

		if (totalFeeds <= start) {
			return tweetFeeds;
		}

		int currentCout = 0;
		while (!queue.isEmpty() && currentCout < (start + count)) {
			currentCout++;
			Tweet tweet = queue.poll();
			TweetFeed tweetfeed = new TweetFeed(tweet.getUser().getUserId(), tweet.getUser().getUserName(),
					tweet.getTweetValue());
			if (currentCout > start) {
				tweetFeeds.addTweetfeed(tweetfeed);
			}
			if (tweet.getNextTweet() != null) {
				queue.add(tweet.getNextTweet());
			}
		}
		return tweetFeeds;
	}
}
