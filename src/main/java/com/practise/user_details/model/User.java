package com.practise.user_details.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user_details")
public class User {

    @Id
    private String id;

    private String name;

    private Date birthDate;

    @OneToMany(mappedBy ="user")
    @JsonManagedReference
    private List<Car> carowned;

    private transient Job jobdone;

}
