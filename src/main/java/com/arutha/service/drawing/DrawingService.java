package com.arutha.service.drawing;

import com.arutha.api.request.drawing.DrawingApi;
import com.arutha.constants.AppErrorCodes;
import com.arutha.exception.CustomException;
import com.arutha.mapper.drawing.DrawingMapper;
import com.arutha.model.drawing.Drawing;
import com.arutha.repository.drawing.DrawingRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service class for Drawing.
 */
@Service
@AllArgsConstructor
public class DrawingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrawingService.class);

    private final DrawingMapper drawingMapper;

    private final DrawingRepository drawingRepository;

    /**
     * Endpoint to save drawing.
     *
     * @param drawingApi DrawingApi
     * @throws CustomException - custom exception
     */
    public void saveDrawing(DrawingApi drawingApi) throws CustomException {
        try {
            Drawing drawing = drawingMapper.toDrawingEntity(drawingApi);
            drawingRepository.save(drawing);
        } catch (Exception e) {
            String errMsg = "Error while saving drawing";
            LOGGER.error(errMsg);
            throw new CustomException(AppErrorCodes.DrawingErrorCodes.DRAWING_INSERT_QUERY_FAILED, errMsg);
        }
    }
}
