package com.producter.cagri.model;

import javax.persistence.*;

@Entity
public class Position {
    @Id
    @Column(name = "position_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "position_label", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EnumPositionType type;

    public Position() {
    }

    public Position(Long id) {
        this.id = id;
    }

    public Position(EnumPositionType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumPositionType getType() {
        return type;
    }

    public void setType(EnumPositionType type) {
        this.type = type;
    }
}
