package io.cybertech.pd.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
public class Racer {
	private Long id;

	@NonNull
	private String racerName;
	private String carName;
	private String groupName;
	private Date created;
	private Date updated;
	
	@Override
	public String toString() {
		return "Name: " + racerName + ", Car: " + carName + ", Group: " + groupName;
	}
}
