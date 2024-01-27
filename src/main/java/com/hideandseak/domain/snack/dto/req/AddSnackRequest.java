package com.hideandseak.domain.snack.dto.req;

public record AddSnackRequest(String name, Long price, String data, String description, String link) {
}
