package io.github.ussesent.repositories;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.github.ussesent.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // ПОЛУЧИТЬ СПИСОК ВСЕХ ПОЛЬЗОВАТЕЛЕЙ
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = """
            SELECT * FROM users
            ORDER BY
                CASE
                    WHEN role = 'OWNER' THEN 0
                    WHEN role = 'ADMIN' THEN 1
                    ELSE 2
                END,
                id ASC
            """;

        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                users.add(User.builder()
                        .id(rs.getInt("id"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password_hash"))
                        .role(User.UserRole.valueOf(rs.getString("role")))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка пользователей", e);
        }

        return users;
    }


    // ПОЛУЧИТЬ ПОЛЬЗОВАТЕЛЯ ПО ID
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return User.builder()
                            .id(rs.getInt("id"))
                            .username(rs.getString("username"))
                            .password(rs.getString("password_hash"))
                            .role(User.UserRole.valueOf(rs.getString("role")))
                            .build();
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения пользователя по ID", e);
        }
    }


    // ПРОВЕРКА ЗАНЯТ НИК ИЛИ НЕТ
    public boolean userExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка проверки уникальности имени", e);
        }
        return false;
    }

    // РЕГИСТРАЦИЯ ПОЛЬЗОВАТЕЛЯ
    public void registerUser(String username, String password) {

        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";

        String hashedPassword = BCrypt.withDefaults().hashToString(12,password.toCharArray());

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка регистрации пользователя", e);
        }
    }

    // ВХОД В АККАУНТ
    public User loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");

                    // Проверка пароля через BCrypt
                    BCrypt.Result result = BCrypt.verifyer()
                            .verify(password.toCharArray(), storedHash);

                    if (result.verified) {
                        return User.builder()
                                .id(rs.getInt("id"))
                                .username(rs.getString("username"))
                                .password(storedHash)
                                .role(User.UserRole.valueOf(rs.getString("role")))
                                .build();
                    } else {
                        // пароль неверный
                        return null;
                    }

                } else {
                    // пользователь с таким username не найден
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при попытке входа", e);
        }
    }

    // УДАЛЕНИЕ ПОЛЬЗОВАТЕЛЯ ПО ID
    public boolean deleteUserById(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            int affectedRows = ps.executeUpdate();

            return affectedRows == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления пользователя по ID", e);
        }
    }

    // ПОВЫСИТЬ USER ДО ADMIN
    public boolean makeAdmin(int userId){
        String sql = "UPDATE users SET role = 'ADMIN' WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, userId);

            int updated = ps.executeUpdate();
            return updated == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при попытке назначить пользователя админом", e);

        }
    }

    // ПОНИЗИТЬ ADMIN ДО USER
    public boolean makeUser(int userId){
        String sql = "UPDATE users SET role = 'USER' WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, userId);

            int updated = ps.executeUpdate();
            return updated == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при попытке понизить админа до пользователя", e);
        }
    }

    // ПОЛУЧИТЬ КОЛИЧЕСТВО АДМИНОВ
    public long countAdmins() {
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'ADMIN'";

        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getLong(1);
            }
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении количества админов", e);
        }
    }
}
