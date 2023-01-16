package ru.sbercources.cinema.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "films")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "films_seq", allocationSize = 1)
public class Film extends GenericModel {
    @Column(name = "title")
    private String title;

    @Column(name = "premier_year")
    private Date premierYear;

    @Column(name = "country")
    private String country;

    @Column(name = "genre")
    @Enumerated
    private Genre genre;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JsonIgnore
    @JoinTable(
            name = "film_directors",
            joinColumns = @JoinColumn(name = "film_id"),
            foreignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"),
            inverseJoinColumns = @JoinColumn(name = "director_id"),
            inverseForeignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"))
    private Set<Directors> directors = new HashSet<>();
}
