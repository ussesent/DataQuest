package io.github.ussesent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    public enum EventFormat {
        ONLINE,
        OFFLINE,
        ONLINE_OFFLINE
    }

    private int id;
    private String name;
    private String shortDescription;
    private String fullDescription;
    private String avatarUrl;
    private int gameId;

    @Builder.Default
    private LocalDate publicationDate = LocalDate.now();

    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private EventFormat format;

    public String getEventStartFormatted() {
        if (eventStart == null) return "";
        return eventStart.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public String getEventEndFormatted() {
        if (eventEnd == null) return "";
        return eventEnd.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }
}
