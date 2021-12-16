import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest extends Commands{

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void chuckComando() {
        assertEquals(chuck.getComando(),"chuck");
        assertTrue(linkKeyChuck.getLink()=="https://api.chucknorris.io/jokes/random");

    }

    @Test
    void dogComando() {
        assertTrue(dog.getComando()=="dog");
        assertEquals(linkKeyDog.getLink(),"https://dog.ceo/api/breeds/image/random");
    }

    @Test
    void movieComando() {
        assertEquals(movie.getComando(),"movie");
        assertTrue(linkKeyMovie.getLink()=="http://www.omdbapi.com/?t=");
        assertEquals(linkKeyMovie.getKey(),"&apikey=9695e938");
    }

    @Test
    void helpComando() {
        assertTrue(help.getComando()=="help");
    }

    @Test
    void memeComand() {
        assertEquals(meme.getComando(),"meme");
        assertTrue(linkKeyMeme.getLink()=="https://meme-api.herokuapp.com/gimme");
    }

}