package com.group5.mbs.bootloader;

import com.group5.mbs.entities.*;
import com.group5.mbs.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.group5.mbs.constants.Constants.BOOTLOADER_SUCCESS_MESSAGE;

@Component
@AllArgsConstructor
@Slf4j
public class Bootloader implements CommandLineRunner {

    private final AdvisorRepository advisorRepository;
    private final DepartmentalBoardRepresentativeRepository departmentalBoardRepresentativeRepository;
    private final GraduateSchoolOfScienceAndEngineeringRepository graduateSchoolOfScienceAndEngineeringRepository;
    private final InstituteBoardOfDirectorsRepresentativeRepository instituteBoardOfDirectorsRepresentativeRepository;
    private final JuryMemberRepository juryMemberRepository;
    private final StudentRepository studentRepository;

    @Override
    public void run(String... args) throws RuntimeException {
        bootLoader();
        log.info(BOOTLOADER_SUCCESS_MESSAGE);
    }

    private void bootLoader() {
        advisorLoader();
        dbrLoader();
        gsesLoader();
        ibdrLoader();
        juryMemberLoader();
        studentLoader();
    }

    private void advisorLoader() {
        final List<AdvisorEntity> advisorEntityList = new ArrayList<>();

        final AdvisorEntity advisorEntity1 = new AdvisorEntity();
        final AdvisorEntity advisorEntity2 = new AdvisorEntity();
        final AdvisorEntity advisorEntity3 = new AdvisorEntity();
        final AdvisorEntity advisorEntity4 = new AdvisorEntity();
        final AdvisorEntity advisorEntity5 = new AdvisorEntity();

        advisorEntity1.setUserType(UserType.ADVISOR);
        advisorEntity1.setFirstName("Advisor1-FN");
        advisorEntity1.setLastName("Advisor1-LN");
        advisorEntity1.setEmail("advisor1@mbs.com");
        advisorEntity1.setPassword("advisor1");
        advisorEntityList.add(advisorEntity1);

        advisorEntity2.setUserType(UserType.ADVISOR);
        advisorEntity2.setFirstName("Advisor2-FN");
        advisorEntity2.setLastName("Advisor2-LN");
        advisorEntity2.setEmail("advisor2@mbs.com");
        advisorEntity2.setPassword("advisor2");
        advisorEntityList.add(advisorEntity2);

        advisorEntity3.setUserType(UserType.ADVISOR);
        advisorEntity3.setFirstName("Advisor3-FN");
        advisorEntity3.setLastName("Advisor3-LN");
        advisorEntity3.setEmail("advisor3@mbs.com");
        advisorEntity3.setPassword("advisor3");
        advisorEntityList.add(advisorEntity3);

        advisorEntity4.setUserType(UserType.ADVISOR);
        advisorEntity4.setFirstName("Advisor4-FN");
        advisorEntity4.setLastName("Advisor4-LN");
        advisorEntity4.setEmail("advisor4@mbs.com");
        advisorEntity4.setPassword("advisor4");
        advisorEntityList.add(advisorEntity4);

        advisorEntity5.setUserType(UserType.ADVISOR);
        advisorEntity5.setFirstName("Advisor5-FN");
        advisorEntity5.setLastName("Advisor5-LN");
        advisorEntity5.setEmail("advisor5@mbs.com");
        advisorEntity5.setPassword("advisor5");
        advisorEntityList.add(advisorEntity5);

        advisorRepository.saveAll(advisorEntityList);
    }

    private void dbrLoader() {
        final List<DepartmentalBoardRepresentativeEntity> dbrList = new ArrayList<>();

        final DepartmentalBoardRepresentativeEntity dbr1 = new DepartmentalBoardRepresentativeEntity();
        final DepartmentalBoardRepresentativeEntity dbr2 = new DepartmentalBoardRepresentativeEntity();

        dbr1.setUserType(UserType.DBR);
        dbr1.setFirstName("Dbr1-FN");
        dbr1.setLastName("Dbr1-LN");
        dbr1.setEmail("dbr1@mbs.com");
        dbr1.setPassword("dbr1");
        dbrList.add(dbr1);

        dbr2.setUserType(UserType.DBR);
        dbr2.setFirstName("Dbr2-FN");
        dbr2.setLastName("Dbr2-LN");
        dbr2.setEmail("dbr2@mbs.com");
        dbr2.setPassword("dbr2");
        dbrList.add(dbr2);

        departmentalBoardRepresentativeRepository.saveAll(dbrList);
    }

    private void gsesLoader() {
        final List<GraduateSchoolOfScienceAndEngineeringEntity> gsesList = new ArrayList<>();

        final GraduateSchoolOfScienceAndEngineeringEntity gses1 = new GraduateSchoolOfScienceAndEngineeringEntity();
        final GraduateSchoolOfScienceAndEngineeringEntity gses2 = new GraduateSchoolOfScienceAndEngineeringEntity();

        gses1.setUserType(UserType.GSES);
        gses1.setFirstName("Gses1-FN");
        gses1.setLastName("Gses1-LN");
        gses1.setEmail("gses1@mbs.com");
        gses1.setPassword("gses1");
        gsesList.add(gses1);

        gses2.setUserType(UserType.GSES);
        gses2.setFirstName("Gses2-FN");
        gses2.setLastName("Gses2-LN");
        gses2.setEmail("gses2@mbs.com");
        gses2.setPassword("gses2");
        gsesList.add(gses2);

        graduateSchoolOfScienceAndEngineeringRepository.saveAll(gsesList);
    }

    private void ibdrLoader() {
        final List<InstituteBoardOfDirectorsRepresentativeEntity> ibdrList = new ArrayList<>();

        final InstituteBoardOfDirectorsRepresentativeEntity ibdr1 = new InstituteBoardOfDirectorsRepresentativeEntity();
        final InstituteBoardOfDirectorsRepresentativeEntity ibdr2 = new InstituteBoardOfDirectorsRepresentativeEntity();

        ibdr1.setUserType(UserType.IBDR);
        ibdr1.setFirstName("Ibdr1-FN");
        ibdr1.setLastName("Ibdr1-LN");
        ibdr1.setEmail("ibdr1@mbs.com");
        ibdr1.setPassword("ibdr1");
        ibdrList.add(ibdr1);

        ibdr2.setUserType(UserType.IBDR);
        ibdr2.setFirstName("Ibdr2-FN");
        ibdr2.setLastName("Ibdr2-LN");
        ibdr2.setEmail("ibdr2@mbs.com");
        ibdr2.setPassword("ibdr2");
        ibdrList.add(ibdr2);

        instituteBoardOfDirectorsRepresentativeRepository.saveAll(ibdrList);
    }

    private void juryMemberLoader() {
        final List<JuryMemberEntity> juryMemberEntityList = new ArrayList<>();

        final JuryMemberEntity juryMemberEntity1 = new JuryMemberEntity();
        final JuryMemberEntity juryMemberEntity2 = new JuryMemberEntity();
        final JuryMemberEntity juryMemberEntity3 = new JuryMemberEntity();
        final JuryMemberEntity juryMemberEntity4 = new JuryMemberEntity();
        final JuryMemberEntity juryMemberEntity5 = new JuryMemberEntity();

        juryMemberEntity1.setUserType(UserType.JURY);
        juryMemberEntity1.setFirstName("JuryMember1-FN");
        juryMemberEntity1.setLastName("JuryMember1-LN");
        juryMemberEntity1.setEmail("jurymember1@mbs.com");
        juryMemberEntity1.setPassword("jurymember1");
        juryMemberEntityList.add(juryMemberEntity1);

        juryMemberEntity2.setUserType(UserType.JURY);
        juryMemberEntity2.setFirstName("JuryMember2-FN");
        juryMemberEntity2.setLastName("JuryMember2-LN");
        juryMemberEntity2.setEmail("jurymember2@mbs.com");
        juryMemberEntity2.setPassword("jurymember2");
        juryMemberEntityList.add(juryMemberEntity2);

        juryMemberEntity3.setUserType(UserType.JURY);
        juryMemberEntity3.setFirstName("JuryMember3-FN");
        juryMemberEntity3.setLastName("JuryMember3-LN");
        juryMemberEntity3.setEmail("jurymember3@mbs.com");
        juryMemberEntity3.setPassword("jurymember3");
        juryMemberEntityList.add(juryMemberEntity3);

        juryMemberEntity4.setUserType(UserType.JURY);
        juryMemberEntity4.setFirstName("JuryMember4-FN");
        juryMemberEntity4.setLastName("JuryMember4-LN");
        juryMemberEntity4.setEmail("jurymember4@mbs.com");
        juryMemberEntity4.setPassword("jurymember4");
        juryMemberEntityList.add(juryMemberEntity4);

        juryMemberEntity5.setUserType(UserType.JURY);
        juryMemberEntity5.setFirstName("JuryMember5-FN");
        juryMemberEntity5.setLastName("JuryMember5-LN");
        juryMemberEntity5.setEmail("jurymember5@mbs.com");
        juryMemberEntity5.setPassword("jurymember5");
        juryMemberEntityList.add(juryMemberEntity5);

        juryMemberRepository.saveAll(juryMemberEntityList);
    }

    private void studentLoader() {
        final List<StudentEntity> studentEntityList = new ArrayList<>();

        final StudentEntity studentEntity1 = new StudentEntity();
        final StudentEntity studentEntity2 = new StudentEntity();
        final StudentEntity studentEntity3 = new StudentEntity();
        final StudentEntity studentEntity4 = new StudentEntity();
        final StudentEntity studentEntity5 = new StudentEntity();
        final StudentEntity studentEntity6 = new StudentEntity();
        final StudentEntity studentEntity7 = new StudentEntity();
        final StudentEntity studentEntity8 = new StudentEntity();
        final StudentEntity studentEntity9 = new StudentEntity();
        final StudentEntity studentEntity10 = new StudentEntity();

        studentEntity1.setUserType(UserType.STUDENT);
        studentEntity1.setFirstName("Student1-FN");
        studentEntity1.setLastName("Student1-LN");
        studentEntity1.setEmail("student1@mbs.com");
        studentEntity1.setPassword("student1");
        studentEntity1.setCpga(mockGpa());
        studentEntity1.setGraduationStatus(GraduationStatus.NOT_APPROVED);
        studentEntityList.add(studentEntity1);

        studentEntity2.setUserType(UserType.STUDENT);
        studentEntity2.setFirstName("Student2-FN");
        studentEntity2.setLastName("Student2-LN");
        studentEntity2.setEmail("student2@mbs.com");
        studentEntity2.setPassword("student2");
        studentEntity2.setCpga(mockGpa());
        studentEntity2.setGraduationStatus(GraduationStatus.NOT_APPROVED);
        studentEntityList.add(studentEntity2);

        studentEntity3.setUserType(UserType.STUDENT);
        studentEntity3.setFirstName("Student3-FN");
        studentEntity3.setLastName("Student3-LN");
        studentEntity3.setEmail("student3@mbs.com");
        studentEntity3.setPassword("student3");
        studentEntity3.setCpga(mockGpa());
        studentEntity3.setGraduationStatus(GraduationStatus.NOT_APPROVED);
        studentEntityList.add(studentEntity3);

        studentEntity4.setUserType(UserType.STUDENT);
        studentEntity4.setFirstName("Student4-FN");
        studentEntity4.setLastName("Student4-LN");
        studentEntity4.setEmail("student4@mbs.com");
        studentEntity4.setPassword("student4");
        studentEntity4.setCpga(mockGpa());
        studentEntity4.setGraduationStatus(GraduationStatus.NOT_APPROVED);
        studentEntityList.add(studentEntity4);

        studentEntity5.setUserType(UserType.STUDENT);
        studentEntity5.setFirstName("Student5-FN");
        studentEntity5.setLastName("Student5-LN");
        studentEntity5.setEmail("student5@mbs.com");
        studentEntity5.setPassword("student5");
        studentEntity5.setCpga(mockGpa());
        studentEntity5.setGraduationStatus(GraduationStatus.NOT_APPROVED);
        studentEntityList.add(studentEntity5);

        studentEntity6.setUserType(UserType.STUDENT);
        studentEntity6.setFirstName("Student6-FN");
        studentEntity6.setLastName("Student6-LN");
        studentEntity6.setEmail("student6@mbs.com");
        studentEntity6.setPassword("student6");
        studentEntity6.setCpga(mockGpa());
        studentEntity6.setGraduationStatus(GraduationStatus.NOT_APPROVED);
        studentEntityList.add(studentEntity6);

        studentEntity7.setUserType(UserType.STUDENT);
        studentEntity7.setFirstName("Student7-FN");
        studentEntity7.setLastName("Student7-LN");
        studentEntity7.setEmail("student7@mbs.com");
        studentEntity7.setPassword("student7");
        studentEntity7.setCpga(mockGpa());
        studentEntity7.setGraduationStatus(GraduationStatus.NOT_APPROVED);
        studentEntityList.add(studentEntity7);

        studentEntity8.setUserType(UserType.STUDENT);
        studentEntity8.setFirstName("Student8-FN");
        studentEntity8.setLastName("Student8-LN");
        studentEntity8.setEmail("student8@mbs.com");
        studentEntity8.setPassword("student8");
        studentEntity8.setCpga(mockGpa());
        studentEntity8.setGraduationStatus(GraduationStatus.NOT_APPROVED);
        studentEntityList.add(studentEntity8);

        studentEntity9.setUserType(UserType.STUDENT);
        studentEntity9.setFirstName("Student9-FN");
        studentEntity9.setLastName("Student9-LN");
        studentEntity9.setEmail("student9@mbs.com");
        studentEntity9.setPassword("student9");
        studentEntity9.setCpga(mockGpa());
        studentEntity9.setGraduationStatus(GraduationStatus.NOT_APPROVED);
        studentEntityList.add(studentEntity9);

        studentEntity10.setUserType(UserType.STUDENT);
        studentEntity10.setFirstName("Student10-FN");
        studentEntity10.setLastName("Student10-LN");
        studentEntity10.setEmail("student10@mbs.com");
        studentEntity10.setPassword("student10");
        studentEntity10.setCpga(mockGpa());
        studentEntity10.setGraduationStatus(GraduationStatus.NOT_APPROVED);
        studentEntityList.add(studentEntity10);

        studentRepository.saveAll(studentEntityList);
    }

    private double mockGpa() {
        final double randomCgpa = ThreadLocalRandom.current().nextDouble(2.95, 4.00);

        BigDecimal cgpa = new BigDecimal(Double.toString(randomCgpa));

        cgpa = cgpa.setScale(2, RoundingMode.HALF_UP);

        return cgpa.doubleValue();
    }

}
