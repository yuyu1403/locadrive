package com.younes.locadrive.model;

import com.younes.locadrive.model.enums.ContractType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "STAFF")
public class Staff {

    @Id
    @Column(name = "staff_id")
    private Integer staffId;

    @Column(name = "staff_entry_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date entryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "staff_contract", nullable = false)
    private ContractType contractType;

    @Column(name = "staff_RQTH", nullable = false)
    private Boolean isRQTH;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "staff_id", referencedColumnName = "user_id")
    private Utilisateur utilisateur;
}
