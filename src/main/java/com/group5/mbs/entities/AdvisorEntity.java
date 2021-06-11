package com.group5.mbs.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "advisor_entities")
@Proxy(lazy = false)
public class AdvisorEntity extends UserEntity {

    @OneToMany(mappedBy = "advisorEntity",
               cascade = CascadeType.ALL,
               fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"advisorEntity"})
    private List<StudentEntity> studentEntities = new ArrayList<>();

}
