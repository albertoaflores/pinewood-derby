package io.cybertech.pd.model.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Table(name="racers")
@Getter
@Setter
public class Racer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String racerName;

	private String carName;
	private String groupName;

	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
	private Date created;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
	private Date updated;
	
	@Override
	public String toString() {
		return "Name: " + racerName + ", Car: " + carName + ", Group: " + groupName;
	}
}
