package io.github.ussesent.repositories;

import io.github.ussesent.model.Event;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    private DataSource dataSource;

    public EventRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // ПОЛУЧИТЬ СПИСОК ВСЕХ СОБЫТИЙ
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        String sql = "SELECT * FROM events ORDER BY id DESC";

        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Timestamp startTs = rs.getTimestamp("event_start");
                Timestamp endTs = rs.getTimestamp("event_end");

                events.add(Event.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .shortDescription(rs.getString("short_description"))
                        .fullDescription(rs.getString("full_description"))
                        .avatarUrl(rs.getString("avatar_url"))
                        .gameId(rs.getInt("game_id"))
                        .eventStart(startTs != null ? startTs.toLocalDateTime() : null)
                        .eventEnd(endTs != null ? endTs.toLocalDateTime() : null)
                        .format(Event.EventFormat.valueOf(rs.getString("format")))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка событий", e);
        }

        return events;

    }

    // ПОЛУЧИТЬ СОБЫТИЯ ПО ИГРЕ
    public List<Event> getEventsByGameId(int gameId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE game_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, gameId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Timestamp startTs = rs.getTimestamp("event_start");
                    Timestamp endTs = rs.getTimestamp("event_end");

                    events.add(Event.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .shortDescription(rs.getString("short_description"))
                            .fullDescription(rs.getString("full_description"))
                            .avatarUrl(rs.getString("avatar_url"))
                            .gameId(rs.getInt("game_id"))
                            .eventStart(startTs != null ? startTs.toLocalDateTime() : null)
                            .eventEnd(endTs != null ? endTs.toLocalDateTime() : null)
                            .format(Event.EventFormat.valueOf(rs.getString("format")))
                            .build());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка событий по gameId = " + gameId, e);
        }

        return events;

    }

    // ПОЛУЧИТЬ 3 ПОСЛЕДНИХ СОБЫТИЯ
    public List<Event> getLastThreeEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events ORDER BY id DESC LIMIT 3";

        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Timestamp startTs = rs.getTimestamp("event_start");
                Timestamp endTs = rs.getTimestamp("event_end");

                events.add(Event.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .shortDescription(rs.getString("short_description"))
                        .fullDescription(rs.getString("full_description"))
                        .avatarUrl(rs.getString("avatar_url"))
                        .gameId(rs.getInt("game_id"))
                        .eventStart(startTs != null ? startTs.toLocalDateTime() : null)
                        .eventEnd(endTs != null ? endTs.toLocalDateTime() : null)
                        .format(Event.EventFormat.valueOf(rs.getString("format")))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка последних 3 событий", e);
        }

        return events;

    }

    // ПОЛУЧИТЬ СОБЫТИЕ ПО ID
    public Event getEventById(int id) {
        String sql = "SELECT * FROM events WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Timestamp startTs = rs.getTimestamp("event_start");
                    Timestamp endTs = rs.getTimestamp("event_end");

                    return Event.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .shortDescription(rs.getString("short_description"))
                            .fullDescription(rs.getString("full_description"))
                            .avatarUrl(rs.getString("avatar_url"))
                            .gameId(rs.getInt("game_id"))
                            .eventStart(startTs != null ? startTs.toLocalDateTime() : null)
                            .eventEnd(endTs != null ? endTs.toLocalDateTime() : null)
                            .format(Event.EventFormat.valueOf(rs.getString("format")))
                            .build();
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении события с id = " + id, e);
        }

    }

    // ОБНОВЛЕНИЕ СОБЫТИЯ
    public void updateEvent(Event event) {
        String sql = """
        UPDATE events
        SET name = ?, 
            short_description = ?,
            full_description = ?,
            avatar_url = ?,
            game_id = ?, 
            event_start = ?, 
            event_end = ?, 
            format = ?
        WHERE id = ?
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, event.getName());
            ps.setString(2, event.getShortDescription());
            ps.setString(3, event.getFullDescription());
            ps.setString(4, event.getAvatarUrl());
            ps.setInt(5, event.getGameId());
            ps.setTimestamp(6, Timestamp.valueOf(event.getEventStart()));
            ps.setTimestamp(7, Timestamp.valueOf(event.getEventEnd()));
            ps.setObject(8, event.getFormat().name(), Types.OTHER);

            ps.setInt(9, event.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении события с id = " + event.getId(), e);
        }
    }

    // ДОБАВЛЕНИЕ НОВОГО СОБЫТИЯ
    public void addEvent(Event event) {
        String sql = """
        INSERT INTO events (name, short_description, full_description, avatar_url, game_id, event_start, event_end, format)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, event.getName());
            ps.setString(2, event.getShortDescription());
            ps.setString(3, event.getFullDescription());
            ps.setString(4, event.getAvatarUrl());
            ps.setInt(5, event.getGameId());

            if (event.getEventStart() != null) {
                ps.setTimestamp(6, Timestamp.valueOf(event.getEventStart()));
            } else {
                ps.setTimestamp(6, null);
            }

            if (event.getEventEnd() != null) {
                ps.setTimestamp(7, Timestamp.valueOf(event.getEventEnd()));
            } else {
                ps.setTimestamp(7, null);
            }
            ps.setObject(8, event.getFormat().name(), Types.OTHER);

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении нового события", e);
        }
    }

    // УДАЛЕНИЕ СОБЫТИЯ ПО ID
    public boolean deleteEventById(int id) {
        String sql = "DELETE FROM events WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении события с id = " + id, e);
        }
    }


}
