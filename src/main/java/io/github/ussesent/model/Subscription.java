package io.github.ussesent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscription {

    private int id;
    private int userId;
    private int gameId;
    private LocalDateTime subscribedAt;
}
