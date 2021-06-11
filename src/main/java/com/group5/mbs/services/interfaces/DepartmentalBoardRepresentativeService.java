package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.Advisor;
import com.group5.mbs.api.dtos.DepartmentalBoardRepresentative;
import com.group5.mbs.api.dtos.Student;

import java.util.List;

public interface DepartmentalBoardRepresentativeService {

    List<DepartmentalBoardRepresentative> getAllDbrs();

    DepartmentalBoardRepresentative getDbrById(Integer dbrId);

    DepartmentalBoardRepresentative getDbrByEmailAndPassword(String dbrMail, String password);

    List<Student> findStudentsWithNoAdvisor();

    List<Advisor> getAdvisors();

}
