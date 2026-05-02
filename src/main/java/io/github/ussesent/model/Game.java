package io.github.ussesent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {

    private int id;
    private String name;
    private String description;
    private String avatarUrl;

}
