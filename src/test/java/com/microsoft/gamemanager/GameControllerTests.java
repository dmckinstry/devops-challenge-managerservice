package com.microsoft.gamemanager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameControllerTests {

    @Test
    public void scoreBestOfOne() {
        GameController gc = new GameController();

        UUID id = gc.Create("Test 1", "Test 2", 1);

        assertTrue(gc.Score(id, 1));
        assertTrue(gc.Complete(id).compareTo("Test 1") == 0);
    }

    @Test
    public void scoreBestOfTwo() {
        GameController gc = new GameController();

        UUID id = gc.Create("Test 1", "Test 2", 2);

        assertFalse(gc.Score(id, 1));
        assertFalse(gc.Score(id, 2));
        assertTrue(gc.Score(id, 1));
        assertTrue(gc.Complete(id).compareTo("Test 1") == 0);
    }

    @Test
    public void scoreBestOfThree() {
        GameController gc = new GameController();

        UUID id = gc.Create("Test 1", "Test 2", 3);

        assertFalse(gc.Score(id, 1));
        assertFalse(gc.Score(id, 2));
        assertTrue(gc.Score(id, 2));
        assertTrue(gc.Complete(id).compareTo("Test 2") == 0);
    }

}
