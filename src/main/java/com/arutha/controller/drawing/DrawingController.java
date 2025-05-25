package com.arutha.controller.drawing;

import com.arutha.api.request.drawing.DrawingApi;
import com.arutha.constants.AppErrorCodes;
import com.arutha.helper.CustomExceptionHandler;
import com.arutha.service.drawing.DrawingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for drawing.
 */
@RestController
@RequestMapping("/drawing")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class DrawingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrawingController.class);

    private final DrawingService drawingService;

    /**
     * Endpoint to create a drawing.
     *
     * @param drawingApi drawing data to create.
     */
    @PostMapping("/")
    public ResponseEntity<Object> createDrawing(@RequestBody @Valid DrawingApi drawingApi,
                                                BindingResult result) {
        LOGGER.info("POST request for drawing received.");
        if (result.hasErrors()) {
            String errMsg = "Error while creating drawing";
            return CustomExceptionHandler.handleValidationExceptions(result.getAllErrors(), errMsg,
                    AppErrorCodes.DrawingErrorCodes.INVALID_DRAWING);
        }
        try {
            drawingService.saveDrawing(drawingApi);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return CustomExceptionHandler.handleUsersResourceExceptions(e);
        }
    }
}
