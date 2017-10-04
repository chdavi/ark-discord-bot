package io.chrisdavis.ark.bots.manager.listeners;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

@Slf4j
public class ReadyListener extends ListenerAdapter {

    public void onReady(ReadyEvent event) {
        String out = "\nThe ARK Manager is running on the following servers: \n";

        for (Guild g : event.getJDA().getGuilds()) {
            out += "     - " + g.getName() + " (" + g.getId() + ") \n";
        }

        log.info(out);

    }
}
