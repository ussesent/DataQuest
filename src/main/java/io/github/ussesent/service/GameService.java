package io.github.ussesent.service;

import io.github.ussesent.exceptions.GameAlreadyExistsException;
import io.github.ussesent.model.Game;
import io.github.ussesent.repositories.GameRepository;

import javax.sql.DataSource;
import java.util.List;

public class GameService {

    private final GameRepository gameRepository;

    public GameService(DataSource dataSource) {
        this.gameRepository = new GameRepository(dataSource);
    }

    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }

    public Game getGameById(int id) {
        return gameRepository.getGameById(id);
    }

    public void addGame(Game game) throws GameAlreadyExistsException {

        if (gameRepository.gameExists(game.getName())) {
            throw new GameAlreadyExistsException("Игра с таким именем уже существует");
        }

        gameRepository.addGame(game);
    }

    public boolean deleteGameById(int id) {
        return gameRepository.deleteGameById(id);
    }

    public List<Game> getTop3Games() {
        return gameRepository.getTop3Games();
    }

}