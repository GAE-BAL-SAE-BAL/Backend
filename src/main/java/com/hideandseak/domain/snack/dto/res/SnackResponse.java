package com.hideandseak.domain.snack.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SnackResponse {
    private Long id;
    private String image;
    private String name;
    private Long price;
    private String data;
    private String description;
    private String link;

}

