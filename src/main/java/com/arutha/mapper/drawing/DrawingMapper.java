package com.arutha.mapper.drawing;

import com.arutha.api.request.drawing.DrawingApi;
import com.arutha.mapper.resolver.roleresolver.UserResolve;
import com.arutha.model.drawing.Drawing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapstruct Mapper class for mapping Games entities.
 */
@Mapper(componentModel = "spring", uses = {UserResolve.class})
public interface DrawingMapper {

    @Mapping(source = "score", target = "score")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "userId", target = "user")
    Drawing toDrawingEntity(DrawingApi drawingApi);



}
