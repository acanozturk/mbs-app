package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.Recommendation;

import java.util.List;

public interface RecommendationService {

    void recommendAdvisorsToStudent(Integer studentId, List<Integer> advisorIds);

    List<Recommendation> getRecommendationsByStudentId(Integer studentId);

}

