package searchOnInternet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import reduceExample.Element;
import reduceExample.ElemwntList;


public class Example25 {
	private static final char OUTPUT_SEPARATOR = '\t';
	List<TwoTuple> output1 = new ArrayList<TwoTuple>() ;
	String v ="";
	Pattern lastPlay = Pattern.compile("\\t([4|5])\\t(-?\\d*)\\t(\\d\\d)\\t");
    public void reduce(ElemwntList list)  {

    	String key = (String)list.getList().get(0).getList().get(0);

    	ArrayList<String> allValues = new ArrayList<String>();
		
		// Find the last play of the game to see who wins
		// Note: this should be done with a secondary sort and not have to cache the values
		// but more importantly because of sorting
		int currentLowMinute = 60;
		int currentLowSecond = 60;
		int currentHighestQuarter = 0;
		String currentEnd = null;

		for (Element value : list.getList()) {
			allValues.add(value.getList().get(1).toString());
			
			// Process the game output
			String play = value.getList().get(1).toString();

			Matcher gameMatcher = lastPlay.matcher(play);

			// Minutes in overtime are negative
			// 20120923_KC@NO 5 -9 32 KC NO 3 6 13 (6:32) R.Succop 31 yard field goal is GOOD Center-T.Gafford
			// Holder-D.Colquitt. 24 24 2012 null null false false false false RUN NO KC 20120923 NO
			if (gameMatcher.find()) {
				int quarter = Integer.parseInt(gameMatcher.group(1));
				int minutes = Integer.parseInt(gameMatcher.group(2));
				int seconds = Integer.parseInt(gameMatcher.group(3));

				if (quarter >= currentHighestQuarter && minutes <= currentLowMinute && seconds < currentLowSecond) {
					currentHighestQuarter = quarter;
					currentLowMinute = minutes;
					currentLowSecond = seconds;

					currentEnd = play;
				}

				continue;
			}
		}
		
		if (currentEnd == null) {
			// Game doesn't contain the ending.  Skip the game
			//logger.warn("Current end is null");
			
			return;
		}
		
		StringBuilder output = new StringBuilder();

		String[] pieces = currentEnd.split("\\t", -1);

		int offenseScore = Integer.parseInt(pieces[10].trim());
		int defenseScore = Integer.parseInt(pieces[11].trim());
		// Desc at 9 - Home team at 22 Away team at 23
		if (offenseScore == defenseScore) {
			// Last play of the game won
			output.append(pieces[4]).append(OUTPUT_SEPARATOR);
			
			// Try to figure out what the last play was
			if (pieces[9].toUpperCase().indexOf("TOUCHDOWN") != -1) {
				// It was a touchdown
				offenseScore += 7;
			} else {
				// Otherwise, it was probably a field goal
				offenseScore += 3;
			}
		} else if (offenseScore > defenseScore) {
			// Offense won the game
			output.append(pieces[4]).append(OUTPUT_SEPARATOR);
		} else {
			// Defense won the game
			output.append(pieces[5]).append(OUTPUT_SEPARATOR);
		}
		
		// Was the home team on offense to output the winning score?
		int homeTeamScore, awayTeamScore;
		
		if (pieces[4].equals(pieces[22])) {
			homeTeamScore = offenseScore;
			awayTeamScore = defenseScore;
		} else {
			homeTeamScore = defenseScore;
			awayTeamScore = offenseScore;
		}

		output.append(homeTeamScore).append(OUTPUT_SEPARATOR);
		output.append(awayTeamScore);
		
		for (String value : allValues) {
			output1.add(new TwoTuple(value, output.toString()));
		}
    }




}
