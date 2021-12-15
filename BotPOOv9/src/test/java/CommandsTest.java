import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandsTest extends Commands {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void chuckComando() {
        assertEquals(chuck.getComando(), "chuck");
    }

    @Test
    void dogComando() {
        assertTrue(dog.getComando() == "dog");
    }

    @Test
    void movieComando() {
        assertEquals(movie.getComando(), "movie");
    }

    @Test
    void helpComando() {
        assertTrue(help.getComando() == "help");
    }

    @Test
    void memeComand() {
        assertEquals(meme.getComando(), "meme");
    }

}