package org.us.famulei.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {
    public Client() {}

    public Client(String name, String firstName, String lastName, String email, String address, BigDecimal monthlyFee) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.monthlyFee = monthlyFee;
    }
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "monthly_fee")
    private BigDecimal monthlyFee;

    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Account> accounts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Carrier getCarrier(Carrier c1) {
        return carrier;
    }
    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }
}
