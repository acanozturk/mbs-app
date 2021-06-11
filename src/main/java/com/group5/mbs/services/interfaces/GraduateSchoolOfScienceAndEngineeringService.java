package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.GraduateSchoolOfScienceAndEngineering;

import java.util.List;

public interface GraduateSchoolOfScienceAndEngineeringService {

    List<GraduateSchoolOfScienceAndEngineering> getAllGses();

    GraduateSchoolOfScienceAndEngineering getGsesById(Integer gsesId);

    GraduateSchoolOfScienceAndEngineering getGsesByEmailAndPassword(String gsesMail, String password);

    void gsesManageStudent(Integer studentId);

}
