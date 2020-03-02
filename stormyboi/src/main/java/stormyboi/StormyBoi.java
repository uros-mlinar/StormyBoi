package stormyboi;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;



public class StormyBoi {
	public static JDA jda;
	public static String prefix = "!";
	
	
	public static void main(String[] args) throws LoginException {
		
		jda = new JDABuilder(AccountType.BOT).setToken("NjI2MDA1MDE3OTIwNTM2NTg3.XYu3aQ.21t7SpFM0OAl23ynU6R6BD1ELNE").build();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		
		jda.addEventListener(new Command());
	}
}
