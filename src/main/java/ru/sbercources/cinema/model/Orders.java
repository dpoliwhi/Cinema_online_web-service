package ru.sbercources.cinema.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "orders_seq", allocationSize = 1)
public class Orders extends GenericModel {
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_ORDERED_USER"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "film_id", foreignKey = @ForeignKey(name = "FK_ORDERED_FILM"))
    private Film film;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "rent_period")
    private LocalDate rentPeriod;
}
