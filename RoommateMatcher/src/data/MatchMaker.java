package data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class matches players to stored users in the list of users
 * 
 * @author ksrinivas788
 *
 */
public class MatchMaker {
	
	private ArrayList<User> users;
	private HashMap<String, Double> types;
	
	public MatchMaker(ArrayList<User> users) {
		this.users = users;
	}
	
	public User findBestMatch(User user) {
		User best = users.get(0);
		for (User u : users) {
			if (user.compare(u) > u.compare(best)) best = u;
		}
		return best;
	}

}
