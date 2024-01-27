package com.hideandseak.domain.drink.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DrinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String image;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String link;


    @Builder
    public DrinkEntity(String image, String name, Long price, String category, String data, String description, String link) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.category = category;
        this.data = data;
        this.description = description;
        this.link = link;
    }
}
