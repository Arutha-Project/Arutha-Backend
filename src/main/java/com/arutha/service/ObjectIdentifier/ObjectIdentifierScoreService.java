package com.arutha.service.ObjectIdentifier;

import com.arutha.api.request.ObjectIdentifier.ObjectIdentifierScoreRequest;
import com.arutha.model.ObjectIdentifier.ObjectIdentifierScore;

import java.util.List;

public interface ObjectIdentifierScoreService {
    ObjectIdentifierScore saveScore(ObjectIdentifierScoreRequest request);
    List<ObjectIdentifierScore> getScoresByUserId(Integer userId);
}
