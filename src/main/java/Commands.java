import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Commands extends ListenerAdapter implements Searcher {
    Comando movie = new Comando("movie");
    Comando chuck = new Comando("chuck");
    Comando dog = new Comando("dog");
    Comando meme = new Comando("meme");
    Comando help = new Comando("help");
    Comando topMovies = new Comando("topMovies");


    public String prefix = "!";//el signo de exclamacion que tiene que ir en el comando

    @Override//el override asegura que funcione
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");//obtiene Strings separados por un espacio(los agrega de forma separada en el String[])

        if (args[0].equalsIgnoreCase(prefix + movie.getComando())) {//esto capta el commando no es case sensitive

            EmbedBuilder embed = new EmbedBuilder();
            embed = MovieComando(args);
            event.getChannel().sendMessage(embed.build()).queue();
            embed.clear();
            //https://www.imdb.com/find?q= scrapped
        }

        if (args[0].equalsIgnoreCase(prefix + topMovies.getComando())) {
            String topMovies;
            TopMovies movies = new TopMovies("https://www.imdb.com/list/ls055386972/");
            topMovies = movies.Conectar();
            event.getChannel().sendMessage(topMovies).tts(true).queue();
        }

        if (args[0].equalsIgnoreCase(prefix + chuck.getComando())) {
            EmbedBuilder embed = new EmbedBuilder();
            embed = ChuckComando(args);


            event.getChannel().sendMessage(embed.build()).queue();
            embed.clear();


        }

        if (args[0].equalsIgnoreCase(prefix + dog.getComando())) {
            EmbedBuilder embed = new EmbedBuilder();
            embed = DogComando(args);


            event.getChannel().sendMessage(embed.build()).queue();
            embed.clear();


        }
        if (args[0].equalsIgnoreCase(prefix + meme.getComando())) {
            String postlink = "";
            String title = "";
            String url = "";
            Conectador conector = new Conectador("https://meme-api.herokuapp.com/gimme");

            try {
                EmbedBuilder embed = new EmbedBuilder();
                embed = MemeComand(conector);
                event.getChannel().sendMessage(embed.build()).queue();
            } catch (Exception e) {
                event.getChannel().sendMessage(":no_entry: Oye, la busqueda es incorrecta. intenta mas tarde!").queue();
                e.printStackTrace();
            }
        }
        if (args[0].equalsIgnoreCase(prefix + help.getComando())) {
            EmbedBuilder embed = new EmbedBuilder();
            embed = HelpComando();
            event.getChannel().sendMessage("A todos los comandos tienes que agregarles el prefijo ! ej: si el comando es 'test' , !test").queue();

            event.getChannel().sendMessage(embed.build()).queue();


        }
        if (args[0].equalsIgnoreCase(prefix + "repeat")) {
            event.getChannel().sendMessage(VozComando(args)).tts(true).queue();
            event.getMessage().delete().queue();
        }

    }

    ///

    public EmbedBuilder ChuckComando(String[] args) {
        String chiste = "";
        String imagen = "";
        Conectador conector = new Conectador("https://api.chucknorris.io/jokes/random");
        EmbedBuilder embed = new EmbedBuilder();
        try {
            String line = conector.Conectar();
            JSONParser parser = new JSONParser();

            while (line != null) {
                JSONArray a = new JSONArray();
                a.add(parser.parse(line)); //toma cada
                System.out.println(line);

                for (Object o : a) {
                    JSONObject chuck = (JSONObject) o;
                    chiste = (String) chuck.get("value");
                    imagen = (String) chuck.get("icon_url");


                }
                System.out.println(chiste);
                line = null;
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }

        if (args[0] != null) {

            embed.setTitle("Chuck Norris Joke", "")
                    .setThumbnail(imagen)
                    .setDescription(chiste);


        } else {
            System.out.println("el comando dio error");
        }
        return embed;

    }

    public EmbedBuilder DogComando(String[] args) {
        EmbedBuilder embed = new EmbedBuilder();
        String perro = "";
        Conectador conector = new Conectador("https://dog.ceo/api/breeds/image/random");
        try {
            String line = conector.Conectar();
            JSONParser parser = new JSONParser();


            while (line != null) {
                JSONArray a = new JSONArray();
                a.add(parser.parse(line)); //toma cada

                for (Object o : a) {
                    JSONObject dog = (JSONObject) o;
                    perro = (String) dog.get("message");

                }
                line = null;
            }
        } catch (Exception e) {
            System.out.println("e=" + e);

        }
        if (args[0] != null) {

            embed.setTitle("Perro", "")
                    .setImage(perro);
        } else {
            System.out.println("equivocao amigo");
        }
        return embed;

    }


    public EmbedBuilder MovieComando(String[] args) {
        String pelicula = "";
        String titulo = "";
        String anno = "";
        String edadRecomendada = "";
        String estreno = "";
        String duracion = "";
        String poster = "";
        String director = "";
        String writers = "";
        String trama = "";
        pelicula = Frase(args);
        EmbedBuilder embed = new EmbedBuilder();
        Conectador conector = new Conectador("http://www.omdbapi.com/?t=" + pelicula + "APIKEY");
        try {
            String line = conector.Conectar();
            JSONParser parser = new JSONParser();

            while (line != null) {
                JSONArray a = new JSONArray();
                a.add(parser.parse(line)); //toma cada

                for (Object o : a) {
                    JSONObject movie = (JSONObject) o;
                    titulo = (String) movie.get("Title");
                    poster = (String) movie.get("Poster");
                    duracion = (String) movie.get("Runtime");
                    anno = (String) movie.get("Year");
                    edadRecomendada = (String) movie.get("Rated");
                    director = (String) movie.get("Director");
                    writers = (String) movie.get("Writer");
                    trama = (String) movie.get("Plot");
                }

                System.out.println(line);
                line = null;

            }

        } catch (Exception e) {
            System.out.println("e=" + e);

        }
        if (args[1] != null) {
            //aca se crea el marco

            embed.setTitle(titulo, "")
                    .addField("Año:", anno, false)
                    .addField("Duracion:", duracion, false)
                    .setAuthor("Director: " + director)
                    .addField("Edad Recomendada:", edadRecomendada, false)
                    .addField("Escritor/es:", writers, false)
                    .addField("Trama:", trama, false)
                    .setImage(poster)
                    //.setThumbnail()
                    .addField("Poster", "Official Poster", false);


        } else {
            System.out.println(" el comando es erroneo");


        }

        return embed;
    }


    private String VozComando(String[] args) {
        String unido = "";
        for (int i = 1; i < args.length; i++) {
            unido = unido + args[i] + " ";
        }

        return unido;
    }


    public EmbedBuilder HelpComando() {
        EmbedBuilder embed = new EmbedBuilder();
        List<Comando> comandosA = new ArrayList<Comando>();
        comandosA.add(help);
        comandosA.add(chuck);
        comandosA.add(meme);
        comandosA.add(dog);
        comandosA.add(movie);
        List<String> comandos = new ArrayList<String>();
        for (int i = 0; i < comandosA.size(); i++) {
            comandos.add("-" + comandosA.get(i).getComando());
        }

        for (int i = 0; i < comandos.size(); i++) {
            embed.addField("Nombre comando " + (i + 1), comandos.get(i), false);
        }
        // event.getChannel().sendMessage(embed.build()).queue();
        comandos = comandos.stream().map(comando -> comando.replace("-", "!")).collect(Collectors.toList());
        for (int i = 0; i < comandos.size(); i++) {
            embed.addField("Como se usa el comando " + (i + 1), comandos.get(i), false);
        }

        return embed;
    }

    public EmbedBuilder MemeComand(Conectador conector) {
        String postlink = "";
        String title = "";
        String url = "";
        EmbedBuilder builder = new EmbedBuilder();
        try {
            String lines = conector.Conectar();
            JSONParser parser = new JSONParser();
            while (lines != null) {
                JSONArray array = new JSONArray() {
                };
                array.add(parser.parse(lines));

                for (Object o : array) {
                    JSONObject jsonObject = (JSONObject) o;

                    postlink = (String) jsonObject.get("postlink");
                    title = (String) jsonObject.get("title");
                    url = (String) jsonObject.get("url");

                }
                lines = null;
            }

            builder.setTitle(title, postlink)
                    .setImage(url)
                    .setColor(Color.green);

        } catch (Exception e) {
            System.out.println("e=" + e);

            e.printStackTrace();
        }

        return builder;
    }

    public static String Frase(String[] args) {
        String pelicula = "";
        if (args.length > 2) {
            for (int i = 2; i < args.length; i++) {//esto se puede convertir en programacion funcional

                pelicula = args[1] + "+" + args[i];//recursividad el + al inicio (no importa si va o no) pero, si entre palabras
                //por algun motivo al cambiar args[1] por pelicula y no tener el for en un if , se muestran las peliculas que no se muestran con esta funcion de manera normal
            }
        } else {

            pelicula = args[1];

        }
        return pelicula;
    }

}
