package com.botscrew.iaquest.tools;

import static org.junit.Assert.assertArrayEquals;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingTimeBuilderTests {

	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	@Test
	public void Case_current_minutes_in_0_14() {
		MeetingTimeBuilder builder = new MeetingTimeBuilder();
		LocalTime time = LocalTime.of(14, 7);

		List<String> times = builder.suggestNewMeetingTime(time, 15);

		assertArrayEquals(new String[] { "Now", "14:15", "14:30", "14:45", "15:00" }, times.toArray());
	}

	@Test
	public void Case_current_minutes_in__15_29() {
		MeetingTimeBuilder builder = new MeetingTimeBuilder();
		LocalTime time = LocalTime.of(14, 21);

		List<String> times = builder.suggestNewMeetingTime(time, 15);

		assertArrayEquals(new String[] { "Now", "14:30", "14:45", "15:00", "15:15" }, times.toArray());

	}

	@Test
	public void Case_current_minutes_in__30_44() {
		MeetingTimeBuilder builder = new MeetingTimeBuilder();
		LocalTime time = LocalTime.of(14, 33);

		List<String> times = builder.suggestNewMeetingTime(time, 15);

		assertArrayEquals(new String[] { "Now", "14:45", "15:00", "15:15", "15:30" }, times.toArray());

	}

	@Test
	public void Case_current_minutes_in__45_59() {
		MeetingTimeBuilder builder = new MeetingTimeBuilder();
		LocalTime time = LocalTime.of(14, 47);

		List<String> times = builder.suggestNewMeetingTime(time, 15);

		assertArrayEquals(new String[] { "Now", "15:00", "15:15", "15:30", "15:45" }, times.toArray());

	}
}
