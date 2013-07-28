package me.rafaelvargas.pincheBot

class BotJob {
	
	def twitterService
	def concurrent = false
	
	// New tweet every 3 minutes
	private static Long repeatInterval = 60000 * 3
	
	static triggers = {
		simple name: 'triggerBot', repeatInterval: repeatInterval
	}
	
	def group = "TwitterJob"
	
	def execute(){
		twitterService.searchAndMakeReply()
	}
}
