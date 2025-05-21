package com.arutha.repository.objectidentifier;

import com.arutha.model.objectidentifier.ObjectIdentifierScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing ObjectIdentifierScore entities.
 * This interface extends JpaRepository to provide CRUD operations and custom query methods.
 */

@Repository
public interface ObjectIdentifierScoreRepository extends JpaRepository<ObjectIdentifierScore, Integer> {
    List<ObjectIdentifierScore> findByUserId(Integer userId);
}
