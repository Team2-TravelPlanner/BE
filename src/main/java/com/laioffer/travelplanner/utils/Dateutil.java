package com.laioffer.travelplanner.utils;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Dateutil {

	public static Date getTwoWeeksLater(){
		Date date = new Date();
		Date endDate = new Date(date.getTime() + (MILLISECONDS.convert(14, TimeUnit.DAYS)));
		return endDate;
	}
}
