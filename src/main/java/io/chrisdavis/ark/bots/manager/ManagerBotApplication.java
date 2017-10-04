package io.chrisdavis.ark.bots.manager;

import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JDA3Handler;
import io.chrisdavis.ark.bots.manager.listeners.ReadyListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.security.auth.login.LoginException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ManagerBotApplication implements CommandLineRunner {

    public static JDA jda;
    private static Logger logger = LoggerFactory.getLogger(ManagerBotApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ManagerBotApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken("MzYzMTUyMjE3NDI1MDUxNjUw.DK9DkA.UJbfJO0reVLHKdMfJbgxyNP4thM");
        builder.setAutoReconnect(true);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.addEventListener(new ReadyListener());

        try {
            jda = builder.buildBlocking();
        } catch (LoginException e) {
            logger.error("Failed Login", e);
        } catch (InterruptedException e) {
            logger.error("Interrupted", e);
        } catch (RateLimitedException e) {
            logger.error("Rate Limited!", e);
        }

        // Register the commands
        CommandHandler commandHandler = new JDA3Handler(jda);

        // Commands:
        // destroyWildDinos
        // update <servername>
        //
    }
}
