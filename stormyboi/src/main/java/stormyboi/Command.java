package stormyboi;

import java.util.List;

import org.jsoup.select.Elements;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Command extends ListenerAdapter {
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		Build build = new Build();
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if (args[0].equalsIgnoreCase(StormyBoi.prefix + "info")) {
			EmbedBuilder info = new EmbedBuilder();
			info.setTitle("StormyBoi");
			info.setDescription("Gets hero talent builds for Heroes of the Storm from icy-veins.com");
			info.setColor(0xf45642);
			info.setFooter("Created by Narkomancer", event.getMember().getUser().getAvatarUrl());
			
			event.getChannel().sendMessage(info.build()).queue();
			info.clear();
		}
		
		if (args[0].equals(StormyBoi.prefix + "build")) {
			
			String heroName = args[1];
			
			if(args.length == 3) {
				if(args[2] != null) heroName += "-" + args[2];
			}
			
			String link = "https://www.icy-veins.com/heroes/" + heroName + "-build-guide";
			
			List<Elements> icons = build.getIcons(link);
			List<String> names = build.getTalentNames(icons);
			List<String> buildNames = build.getBuildNames(link);
			String output = build.formatOutput(buildNames, names);
			
			if(icons == null || names == null) {
				event.getChannel().sendMessage("Hero not found.").queue();
			}
			
			else {
				event.getChannel().sendMessage(output).queue();
			}
			
			
		}
	}
}
