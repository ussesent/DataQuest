package io.github.ussesent.repositories;

import io.github.ussesent.model.Game;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {

    private DataSource dataSource;

    public GameRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // ПОЛУЧИТЬ СПИСОК ВСЕХ ИГР
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games ORDER BY id DESC";

        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                games.add(Game.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
                        .avatarUrl(rs.getString("avatar_url"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка игр", e);
        }

        return games;
    }

    // ПОЛУЧИТЬ ИГРУ ПО ID
    public Game getGameById(int id) {
        String sql = "SELECT * FROM games WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Game.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .avatarUrl(rs.getString("avatar_url"))
                            .build();
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении игры с id = " + id, e);
        }
    }

    // ДОБАВЛЕНИЕ НОВОЙ ИГРЫ
    public void addGame(Game game) {
        String sql = "INSERT INTO games (name, description, avatar_url) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, game.getName());
            ps.setString(2, game.getDescription());
            ps.setString(3, game.getAvatarUrl());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении новой игры", e);
        }
    }

    // УДАЛЕНИЕ ИГРЫ ПО ID
    public boolean deleteGameById(int id) {
        String sql = "DELETE FROM games WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении игры с id = " + id, e);
        }
    }

    // ТОП-3 ИГР ПО ПОДПИСКАМ
    public List<Game> getTop3Games() {
        List<Game> topGames = new ArrayList<>();
        String sql = """
            SELECT g.id, g.name, g.description, g.avatar_url, COUNT(s.user_id) as sub_count
            FROM games g
            LEFT JOIN subscriptions s ON g.id = s.game_id
            GROUP BY g.id
            ORDER BY sub_count DESC
            LIMIT 3
        """;

        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                topGames.add(Game.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
                        .avatarUrl(rs.getString("avatar_url"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении топ-3 игр по подпискам", e);
        }

        return topGames;
    }

    public boolean gameExists(String name) {
        String sql = "SELECT COUNT(*) FROM games WHERE name = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка проверки уникальности имени игры", e);
        }
        return false;

    }
}