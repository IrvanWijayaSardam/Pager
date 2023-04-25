package com.aminivan.pager.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "tb_pager",schema = "public")
public class Pager implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pagerid",nullable = false,unique = true)
    private int pagerId;

    @Column(name = "pagername")
    private String pagername;

    @Column(name = "userid")
    private int userid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private User user;

    @Column(name = "xstatus")
    private Boolean xstatus;

    @Column(name = "ssid")
    private String ssid;

    @Column(name = "ssidpass")
    private String ssidpass;
}
