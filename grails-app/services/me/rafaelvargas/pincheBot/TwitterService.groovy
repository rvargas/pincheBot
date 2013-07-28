package me.rafaelvargas.pincheBot

import twitter4j.Query
import twitter4j.QueryResult
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.Status
import twitter4j.StatusUpdate
import twitter4j.conf.ConfigurationBuilder

class TwitterService {
	
	def grailsApplication
	
	private static String quote = "Que pinche pena."
	private static String searchQuery = "ola k ase"
	private static Integer maxReplies = 1
	
	void searchAndMakeReply(){
		
		Twitter twitter = getConfiguration().getInstance()
		Query query = new Query(searchQuery)
		query.setCount(maxReplies)
		QueryResult result = twitter.search(query)
		
		result.getTweets().each{ Status status ->
			StatusUpdate statusUpdate = new StatusUpdate("@" + status.getUser().getScreenName() + " " + quote)
			statusUpdate.inReplyToStatusId = status.getId()
			Status reply = twitter.updateStatus(statusUpdate)
		}
	}
	
	private TwitterFactory getConfiguration(){
		ConfigurationBuilder cb = new ConfigurationBuilder()
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(grailsApplication.config.oauth.consumerKey)
		  .setOAuthConsumerSecret(grailsApplication.config.oauth.consumerSecret)
		  .setOAuthAccessToken(grailsApplication.config.oauth.accessToken)
		  .setOAuthAccessTokenSecret(grailsApplication.config.oauth.accessTokenSecret)
		TwitterFactory tf = new TwitterFactory(cb.build())
	}
}
