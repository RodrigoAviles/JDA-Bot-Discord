import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Conectador extends Lineador {
    public String link;

    public Conectador(String link) {
        this.link = link;

    }


    @Override
    public String Conectar() {
        String line = "";
        try {

            URL UrlO = new URL(link);// "http://www.omdbapi.com/?t=" + pelicula + "&apikey=9695e938"        https://www.omdbapi.com/?t=spider-man&apikey=9695e938 se define la url
            URLConnection UrlC = UrlO.openConnection();//se conecta a la url
            BufferedReader BReader = new BufferedReader(new InputStreamReader(UrlC.getInputStream()));//hasta aca,lee los datos de la url (en este caso una api de imdb)
            line = BReader.readLine();
        } catch (Exception e) {
            System.out.println("e=" + e);
        }


        return line;
    }
}
