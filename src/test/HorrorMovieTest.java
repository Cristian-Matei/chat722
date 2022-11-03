package test;

import movies.HorrorMovie;
import movies.Movie;
import movies.OrderLine;
import movies.SciFiMovie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HorrorMovieTest {



    @Test
    void testCast(){

        List<String> avatarCast = new ArrayList<>();
        avatarCast.add("Actor1");
        avatarCast.add("Actor2");

        //Variante 2
        List<String> itCast = new ArrayList<>(
                Arrays.asList("Actor1", "Actor2")
        );
        Movie avatar = new SciFiMovie("Avatar", 2009, 8.8, avatarCast, 4);
        Movie it = new HorrorMovie("IT", 1980, 7.2, itCast, 7, 2);
        OrderLine orderLine1 = new OrderLine(avatar, 3);
        OrderLine orderLine2 = new OrderLine(it, 4);

        assertTrue(avatarCast.contains("Actor1"));
    }

}