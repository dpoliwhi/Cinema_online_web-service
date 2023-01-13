package ru.sbercources.cinema.model;

import javax.persistence.*;
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

    @ManyToMany(mappedBy = "films", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Directors> directors = new HashSet<>();
}
