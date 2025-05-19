package com.arutha.model.users;

import com.arutha.model.role.Role;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Teacher Entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_teacher_user_id"))
    private Users user;
}
