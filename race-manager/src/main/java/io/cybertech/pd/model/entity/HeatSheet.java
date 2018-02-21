package io.cybertech.pd.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="heatsheets")
@Getter
@Setter
public class HeatSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @OneToMany(mappedBy = "heatSheet", cascade = CascadeType.ALL)
	private List<HeatEvent> events = new ArrayList<>();

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date started;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updated;

}
