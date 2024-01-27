package com.hideandseak.domain.drink.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DrinkResponse {

    private Long id;
    private String image;
    private String name;
    private Long price;
    private String category;
    private String data;
    private String description;
    private String link;

}
