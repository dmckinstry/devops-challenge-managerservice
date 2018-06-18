package com.microsoft.gamemanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();
    private class Game {
        public UUID id;
        public String team1;
        public int team1Count;
        public String team2;
        public int team2Count;
        public int bestOfCount;
    }
    private static List<Game> activeGames = new ArrayList<Game>();

    @RequestMapping("/api/game/create")
    @CrossOrigin(origins="*") // This is bad but I don't have a good way to control it in the wild (Challenge) 
    public UUID Create(@RequestParam(value="team1") String team1, @RequestParam(value="team2") String team2, @RequestParam(value="count") int numberOfGames) {
        Game g = new Game();
        g.id = UUID.randomUUID();
        g.team1 = team1;
        g.team2 = team2;
        g.bestOfCount = numberOfGames;
        activeGames.add(g);
        return g.id;
    }


    private Game FindGame(UUID gameId) {
        Optional<Game> theGame = activeGames
            .stream()
            .filter(g -> g.id.compareTo(gameId) == 0)
            .findFirst();

        if (theGame.isPresent())
            return theGame.get();

        return null;
    }

    @RequestMapping("/api/game/score")
    @CrossOrigin(origins="*") // This is bad but I don't have a good way to control it in the wild (Challenge) 
    public boolean Score(@RequestParam(value="id") UUID gameId, @RequestParam(value="winningTeam") int teamNumber) {
        Game game = this.FindGame(gameId);

        if (game != null) {
            if (teamNumber == 1) { game.team1Count++; }
            if (teamNumber == 2) { game.team2Count++; }

            // We have a winner if either team passed the threshold 
            return ((game.team1Count > (game.bestOfCount / 2)) || (game.team2Count > (game.bestOfCount / 2)));
        }

        return true; // end the game in case in error
    }

    @RequestMapping("/api/game/complete")
    @CrossOrigin(origins="*") // This is bad but I don't have a good way to control it in the wild (Challenge) 
    public String Complete(@RequestParam(value="id") UUID gameId) {
        Game game = this.FindGame(gameId);

        if (game != null) {
            if (game.team1Count > game.team2Count)
                return game.team1;
            else
                return game.team2;
        }

        return "bug in the code";
    }
}