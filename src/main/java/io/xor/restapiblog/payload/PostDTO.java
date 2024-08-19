package io.xor.restapiblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class PostDTO {
    private long id;
    private String title;
    private String content;
    private String description;
}
