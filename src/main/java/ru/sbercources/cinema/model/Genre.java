package ru.sbercources.cinema.model;

public enum Genre {
    FANTASY("Фантастика"),
    COMEDY("Комедия"),
    DRAMA("Драма"),
    THRILLER("Триллер"),
    HORROR("Ужасы");

    private final String genreText;

    Genre(String genreText) {
        this.genreText = genreText;
    }

    public String getGenreText() {
        return this.genreText;
    }
}
