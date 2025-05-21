package com.arutha.model.users;


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

/**
 * Child Entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "child")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "teacher_id", nullable = false, foreignKey = @ForeignKey(name = "fk_teacher_child_id"))
    private Teachers teachers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "child_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_child_id"))
    private Users user;


}
