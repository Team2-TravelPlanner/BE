package com.laioffer.travelplanner.entities;

public class DayOfPlanId {
	private Integer planId;
	private Integer dayId;
	public DayOfPlanId() {
		
	}
	public DayOfPlanId(Integer planId, Integer dayId) {
		this.planId = planId;
		this.dayId = dayId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayId == null) ? 0 : dayId.hashCode());
		result = prime * result + ((planId == null) ? 0 : planId.hashCode());
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
		DayOfPlanId other = (DayOfPlanId) obj;
		if (dayId == null) {
			if (other.dayId != null)
				return false;
		} else if (!dayId.equals(other.dayId))
			return false;
		if (planId == null) {
			if (other.planId != null)
				return false;
		} else if (!planId.equals(other.planId))
			return false;
		return true;
	}
	

}
