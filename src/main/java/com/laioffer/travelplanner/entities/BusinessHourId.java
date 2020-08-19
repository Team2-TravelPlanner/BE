package com.laioffer.travelplanner.entities;

import com.laioffer.travelplanner.entities.BusinessHour.DayOfWeek;

public class BusinessHourId {
	private Integer placeId;
	private DayOfWeek dayOfWeek;
	
	public BusinessHourId() {
		
	}

	public BusinessHourId(Integer placeId, DayOfWeek dayOfWeek) {
		super();
		this.placeId = placeId;
		this.dayOfWeek = dayOfWeek;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
		result = prime * result + ((placeId == null) ? 0 : placeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessHourId other = (BusinessHourId) obj;
		if (dayOfWeek != other.dayOfWeek)
			return false;
		if (placeId == null) {
			if (other.placeId != null)
				return false;
		} else if (!placeId.equals(other.placeId))
			return false;
		return true;
	}
	

}
