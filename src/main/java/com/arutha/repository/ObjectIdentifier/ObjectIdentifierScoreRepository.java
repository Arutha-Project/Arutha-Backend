package com.arutha.repository.ObjectIdentifier;

import com.arutha.model.ObjectIdentifier.ObjectIdentifierScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectIdentifierScoreRepository extends JpaRepository<ObjectIdentifierScore, Integer> {
    List<ObjectIdentifierScore> findByUserId(Integer userId);
}
