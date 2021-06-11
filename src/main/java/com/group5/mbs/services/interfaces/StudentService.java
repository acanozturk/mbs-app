package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(Integer studentId);

    Student getStudentByEmailAndPassword(String studentMail, String password);

    List<Student> getAllStudentsWithNoAdvisor();

    List<Student> getGetApprovedStudentsByTheAdvisor(Integer advisorId);

    String getStudentNameByStudentId(Integer studentId);

    String getStudentNamesByStudentIds(List<Integer> studentIds);

}
