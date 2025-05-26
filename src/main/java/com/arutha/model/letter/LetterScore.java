package com.arutha.model.letter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Letter score entity.
 */

@Entity
@Table(name = "letter_scores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LetterScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String language;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    private int score;

    @Column(name = "total_items")
    private int totalItems;

    @Column(name = "user_id")
    private Long userId;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
