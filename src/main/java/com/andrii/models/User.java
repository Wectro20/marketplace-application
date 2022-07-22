package com.andrii.models;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user", schema = "marketplace")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @NotNull(message = "Missing first_name")
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @NotNull(message = "Missing second_name")
    @Column(name = "second_name")
    private String secondName;

    @Basic
    @NotNull(message = "Missing amount_of_name")
    @Column(name = "amount_of_money")
    private Float amountOfMoney;

    public User(String firstName, String secondName, Float amountOfMoney) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.amountOfMoney = amountOfMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(amountOfMoney, user.amountOfMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, amountOfMoney);
    }
}
