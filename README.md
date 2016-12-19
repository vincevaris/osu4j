# osu4j
an easy-to-use wrapper for the osu!API, with users, beatmaps, matches, customizable rate limits, et cetera.

## Example 1 - GetUserTest
```java
public class GetUserTest {
	private static final String USER = "oopsjpeg";
	private static final GameMode MODE = GameMode.STANDARD;
	private static final int TOP_SCORE_LIMIT = 3;

	public static void main(String[] args) throws OsuAPIException, MalformedURLException {
		// Create a new Osu object with an API key

		// Get the user
		System.out.println("Getting user...");
		OsuUser user = osu.users.query(new EndpointUsers.ArgumentsBuilder(USER).setMode(MODE).build());

		// Print some user info
		System.out.println(user.getUsername() + "(" + user.getID() + ")");
		System.out.println(user.getURL());
		System.out.println("Rank: #" + user.getRank());
		System.out.println("Performance: #" + user.getRank() + " (" + user.getPP() + "pp)");
		System.out.println("Total Score: " + user.getTotalScore());

		// Get the user's top scores
		List<OsuScore> topScores = user.getTopScores(TOP_SCORE_LIMIT).get();

		// Print the top scores
		for (int i = 0; i < topScores.size(); i++) {
			OsuScore score = topScores.get(i);
			OsuBeatmap beatmap = score.getBeatmap().get();
			System.out.println("Top score #" + (i + 1) + ": " + beatmap.toString() + " " + beatmap.getURL());
		}
	}
}
```

## Example 2 - GetBeatmapTest
```java
public class GetBeatmapTest {
	private static final int BEATMAP_ID = 131891;

	public static void main(String[] args) throws OsuAPIException, MalformedURLException {
		// Create a new Osu object with an API key
		String KEY = args[0];
		Osu osu = Osu.getAPI(KEY);

		// Get the beatmap
		OsuBeatmap beatmap = osu.beatmaps.getAsQuery(new EndpointBeatmaps.ArgumentsBuilder()
				.setBeatmapID(BEATMAP_ID).build())
				.resolve().get(0);

		// Print information
		System.out.println(beatmap.toString());
		System.out.println(beatmap.getURL());
		System.out.println("Creator: " + beatmap.getCreatorName());
		System.out.println("BPM: " + beatmap.getBPM());
		System.out.println("Difficulty: " + beatmap.getDifficulty());
	}
}
```
