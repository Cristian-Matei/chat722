package test;

import movies.HorrorMovie;
import movies.Movie;
import movies.OrderLine;
import movies.SciFiMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineTest {

    OrderLine orderLine1, orderLine2;

    @BeforeEach // execute this before each test  // @BeforeAll -> execute this ONCE, THEN run all tests
    void setUp(){
        List<String> avatarCast = new ArrayList<>();
        avatarCast.add("Actor1");
        avatarCast.add("Actor2");

        //Variante 2
        List<String> itCast = new ArrayList<>(
                Arrays.asList("Actor1", "Actor2")
        );
        Movie avatar = new SciFiMovie("Avatar", 2009, 8.8, avatarCast, 4);
        Movie it = new HorrorMovie("IT", 1980, 7.2, itCast, 7, 2);
        orderLine1 = new OrderLine(avatar, 3);
        orderLine2 = new OrderLine(it, 4);
    }

    @Test
    void calculatePrice() {
        //orderLine1.calculatePrice(); // 4*3 = 12
        assertEquals(12.0, orderLine1.calculatePrice());
        assertEquals(7*4*0.9, orderLine2.calculatePrice());
    }

    @Test
    void testFilmTitle(){
        assertEquals("Avatar",orderLine1.getMovie().getTitle());
    }
}