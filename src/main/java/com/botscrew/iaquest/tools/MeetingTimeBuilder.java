package com.botscrew.iaquest.tools;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MeetingTimeBuilder {

	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	public List<String> suggestNewMeetingTime(LocalTime now, int minutesStep) {
		return suggestNewMeetingTime(now, 0, minutesStep);
	}

	public List<String> suggestNewMeetingTime(LocalTime now, int hoursStep, int minutesStep) {
		List<String> times = new ArrayList<String>(5);
		times.add("Now");
		LocalTime baseTime = buildBaseTime(now);
		for (int i = 1; i < 5; ++i) {
			baseTime = baseTime.plusHours(hoursStep).plusMinutes(minutesStep);
			times.add(baseTime.format(timeFormatter));
		}
		return times;

	}

	private LocalTime buildBaseTime(LocalTime baseTime) {
		int currentMinute = baseTime.getMinute();
		int difference = currentMinute - (currentMinute / 15) * 15;
		return baseTime.minusMinutes(difference);
	}
}
