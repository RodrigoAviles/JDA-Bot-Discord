import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {
        JDABuilder jda = JDABuilder.createDefault("TOKEN");//no va la clave porque podria afectar nuestro programa, en caso de querer probar esto de forma practica, crear un bot en discord developer,y copiar y pegar la clave aca
        jda.setActivity(Activity.watching("!test"));
        jda.setStatus(OnlineStatus.ONLINE);
        jda.addEventListeners(new Commands());
        jda.build();

    }


}
