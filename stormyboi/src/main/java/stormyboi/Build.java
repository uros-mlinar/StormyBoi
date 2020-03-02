package stormyboi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Build {

	List<Elements> getIcons(String link) {

		List<Elements> icons = new ArrayList<Elements>();

		try {
			Document doc = Jsoup.connect(link).userAgent("Mozilla").get();
			Elements buildList = doc.select("a.heroes_build_talent_tier_recommended");

			for (Element build : buildList) {
				icons.add(build.getElementsByTag("img"));
			}

		} catch (IOException e) {
			return null;
		}

		return icons;
	}

	List<String> getBuildNames(String link) {

		List<String> buildNames = new ArrayList<String>();

		Document doc;
		try {
			doc = Jsoup.connect(link).userAgent("Mozilla").get();
			Elements buildNamesList = doc.select("div.heroes_build_header");

			for (Element name : buildNamesList) {
				String s = name.getElementsByTag("h3").text();
				buildNames.add("\n--------------------------------\n**" + s + "**\n");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return buildNames;

	}

	List<String> getTalentNames(List<Elements> icons) {

		if (icons == null)
			return null;

		List<String> names = new ArrayList<String>();
		String name = "";
		int i = 0;

		for (Elements e : icons) {
			String alt = e.attr("alt");

			if (i == 0 || i == 7 || i == 15) {
				name = "\n**Level 1 ** ";
			}

			if (i == 1 || i  == 8 || i == 16) {
				name = "\n**Level 4 ** ";
			}

			if (i == 2 || i == 9 || i == 17) {
				name = "\n**Level 7 ** ";
			}

			if (i == 3 || i == 10 || i == 18) {
				name = "\n**Level 10** ";
			}

			if (i == 4 || i == 11 || i == 19) {
				name = "\n**Level 13** ";
			}

			if (i == 5 || i == 12 || i == 20) {
				name = "\n**Level 16** ";
			}

			if (i == 6 || i == 13 || i == 21) {
				name = "\n**Level 20** ";
			}

			i++;

			name = name + alt.substring(0, alt.length() - 5);
			names.add(name);
		}

		return names;

	}

	String formatOutput(List<String> buildNames, List<String> builds) {

		String finalOutput = "";

		for (int cnt = 0; cnt < buildNames.size(); cnt++) {

			String title = buildNames.get(cnt);
			finalOutput += title;

			for (int i = cnt * 7; i < cnt * 7 + 7; i++) {
				String build = builds.get(i);
				finalOutput += build;
			}
		}

		return finalOutput;

	}

	public static void main(String[] args) {

		Build build = new Build();

		String link = "https://www.icy-veins.com/heroes/artanis-build-guide";
		List<Elements> icons = build.getIcons(link);
		List<String> names = build.getTalentNames(icons);
		List<String> buildNames = build.getBuildNames(link);
		String output = build.formatOutput(buildNames, names);
		System.out.println(output);

	}

}