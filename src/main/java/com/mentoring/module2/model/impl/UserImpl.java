package com.mentoring.module2.model.impl;

import com.mentoring.module2.model.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
//TODO check naming
@Entity(name = "users")
public class UserImpl implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    //Todo check structure
    @Column(name = "money_amount", nullable = false)
    private BigDecimal moneyAmount;
}
