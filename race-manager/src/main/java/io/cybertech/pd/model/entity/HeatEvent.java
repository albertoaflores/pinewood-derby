package io.cybertech.pd.model.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * A heat event reflects a specific heat run for the 3 racers. It also contains the result of this event.
 */
@Entity
@Table(name="heatevents")
@Getter
@Setter
public class HeatEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @ManyToOne
    @JoinColumn(name = "heat_sheet_id")
    private HeatSheet heatSheet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Racer racerInLane1;
    private Double timeInLane1;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Racer racerInLane2;
    private Double timeInLane2;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Racer racerInLane3;
    private Double timeInLane3;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updated;
}
