package com.arutha.repository.drawing;

import com.arutha.model.drawing.Drawing;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for drawing repository.
 */
public interface DrawingRepository extends JpaRepository<Drawing, Integer> {

}
