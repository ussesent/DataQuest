package io.github.ussesent.listeners;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.io.InputStream;
import java.util.Properties;
import io.github.ussesent.service.EventService;
import io.github.ussesent.service.GameService;
import io.github.ussesent.service.UserService;

@WebListener
public class AppInitializer implements ServletContextListener {

    private HikariDataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            Properties props = new Properties();
            try (InputStream in = getClass().getClassLoader().getResourceAsStream("application.properties")) {
                if (in != null) {
                    props.load(in);
                } else {
                    System.err.println("application.properties not found!");
                }
            }

            Class.forName("org.postgresql.Driver");
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("db.url", "jdbc:postgresql://localhost:5432/GameEvents"));
            config.setUsername(props.getProperty("db.user", "postgres"));
            config.setPassword(props.getProperty("db.password", ""));
            config.setMaximumPoolSize(10);
            dataSource = new HikariDataSource(config);

            UserService userService = new UserService(dataSource);
            EventService eventService = new EventService(dataSource);
            GameService gameService = new GameService(dataSource);

            ServletContext servletContext = sce.getServletContext();
            servletContext.setAttribute("userService", userService);
            servletContext.setAttribute("eventService", eventService);
            servletContext.setAttribute("gameService", gameService);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка создания пула соединений", e);

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        if (dataSource != null) {
            dataSource.close();
        }
    }
}
