import java.lang.reflect.Array;

public class OAuthClass {

	private static String oauthKey = "dj0yJmk9blVQVkEwbXJNN1VEJmQ9WVdrOVZWUjNUMlF6Yld3bWNHbzlNQT09JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTY2";
	private static String oauthSecret = "'578c0e43044c1b09df6d36af7ce4ede86dedc073";
	
	public static void main(String[] args) {
		
		String url = "https://fantasysports.yahooapis.com/fantasy/v2/users;use_login=1/games;game_keys=nfl/teams";
		String scope = "test";

		// MODIFY: Insert your own consumer key and secret here!
		Array consumer_data = new Array();
		consumer_data["test"]["key"] = oauthKey;
		consumer_data["test"]["secret"] = oauthSecret;

	}

}
