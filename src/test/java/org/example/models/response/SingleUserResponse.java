package org.example.models.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SingleUserResponse {
    private Data data;
    private Support support;
}
