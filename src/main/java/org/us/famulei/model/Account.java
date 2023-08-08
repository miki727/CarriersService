package org.us.famulei.model;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    public Account() {}
    public Account(String accountType) {
        this.accountType = accountType;
    }

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "account_type")
    private String accountType;
//    @Column(name = "client_id")
//    private long clientId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
