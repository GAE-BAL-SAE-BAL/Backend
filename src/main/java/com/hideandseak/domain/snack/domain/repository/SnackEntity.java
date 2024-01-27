package com.hideandseak.domain.snack.domain.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SnackEntity {
    @Id
    private Long id;

    @Column
    private String image;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String link;

    @Builder
    public SnackEntity(String image, String name, Long price, String data, String description, String link) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.data = data;
        this.description = description;
        this.link = link;
    }
}
