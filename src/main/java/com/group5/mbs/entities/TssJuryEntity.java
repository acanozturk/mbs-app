package com.group5.mbs.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "tss_jury_entities")
public class TssJuryEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "jury_member_id")
    private JuryMemberEntity juryMemberEntity;

    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "tss_status")
    @Enumerated(EnumType.STRING)
    private ThesisTssStatus thesisTssStatus;
}
