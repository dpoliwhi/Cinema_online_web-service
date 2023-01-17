package ru.sbercources.cinema.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public abstract class GenericDto {
    private Long id;
    private LocalDateTime createdWhen;
    private String createdBy;
    private LocalDateTime updatedWhen;
    private String updatedBy;
    private boolean isDeleted;
    private LocalDateTime deletedWhen;
    private LocalDateTime deletedBy;
}
