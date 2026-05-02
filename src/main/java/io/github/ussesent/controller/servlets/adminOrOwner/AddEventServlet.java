package io.github.ussesent.controller.servlets.adminOrOwner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import io.github.ussesent.model.Event;
import io.github.ussesent.service.EventService;
import io.github.ussesent.util.S3ClientProvider;
import io.github.ussesent.util.ServletUtils;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet("/secure/addEvent")
@MultipartConfig
public class AddEventServlet extends HttpServlet {

    private static final String BUCKET_NAME = "events-image";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        try {
            String name = req.getParameter("name");
            String shortDescription = req.getParameter("shortDescription");
            String fullDescription = req.getParameter("fullDescription");
            int gameId = Integer.parseInt(req.getParameter("gameId"));

            // Загрузка файла
            Part filePart = req.getPart("avatar");
            String fileName = UUID.randomUUID() + "_" + filePart.getSubmittedFileName();

            // Загрузка в S3
            S3Client s3 = S3ClientProvider.getClient();
            try (InputStream inputStream = filePart.getInputStream()) {
                s3.putObject(PutObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key(fileName)
                        .acl("public-read")
                        .build(), software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, filePart.getSize()));
            }

            // Генерация публичного URL
            String avatarUrl = "https://storage.yandexcloud.net/" + BUCKET_NAME + "/" + fileName;

            Event event = new Event();
            event.setName(name);
            event.setShortDescription(shortDescription);
            event.setFullDescription(fullDescription.isEmpty() ? shortDescription : fullDescription);
            event.setAvatarUrl(avatarUrl);
            event.setGameId(gameId);

            // Если указали время начала/конца события - записываем их в объект, иначе null
            String eventStart = req.getParameter("event_start");
            if (!eventStart.isEmpty()) {
                event.setEventStart(LocalDateTime.parse(eventStart));
            }

            String eventEnd = req.getParameter("event_end");
            if (!eventEnd.isEmpty()) {
                event.setEventEnd(LocalDateTime.parse(eventEnd));
            }

            event.setFormat(Event.EventFormat.valueOf(req.getParameter("format")));


            EventService eventService = ServletUtils.getEventService(req);
            eventService.addEvent(event);


            resp.sendRedirect(req.getContextPath() + "/pages/secure/events");

        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("addEventError", "Ошибка при добавлении события");
            resp.sendRedirect(req.getContextPath() + "/pages/secure/events");
        }
    }
}
