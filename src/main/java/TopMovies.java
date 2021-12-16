import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class TopMovies extends Lineador {

    public String link;

    public TopMovies(String link) {
        this.link = link;

    }


    @Override
    public String Conectar() {
        String conectar = this.link;
        String topMovies = "";
        try {
            Document doc = Jsoup.connect(conectar).get();
            Elements temp = doc.select("div.lister-item-content");
            int i = 0;
            for (Element movieList : temp) {
                i++;
                topMovies = topMovies + i + ") " + movieList.getElementsByTag("a").first().text() + "\n";

            }

        } catch (Exception e) {
            System.out.println("e=" + e);
        }

        return topMovies;
    }

}
