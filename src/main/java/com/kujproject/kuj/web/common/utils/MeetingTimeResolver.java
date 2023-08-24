package com.kujproject.kuj.web.common.utils;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;

@Component
public class MeetingTimeResolver {


    public MeetingTimeResolver() {
    }

    public int calculateBlock(LocalTime startTime, LocalTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        int minutesDifference = (int) duration.toMinutes();

        return minutesDifference/20;
    }


    public int[] convertTimeToIdx(LocalTime startTime, LocalTime exclusionStartTime, LocalTime exclusionEndTime) {
        int[] idxs = new int[2];
        int startHour = startTime.getHour();
        int startMin = startTime.getMinute();

        int exStartHour = exclusionStartTime.getHour();
        int exStartMin = exclusionStartTime.getMinute();
        int exEndHour = exclusionEndTime.getHour();
        int exEndMin = exclusionEndTime.getMinute();

        idxs[0] = ((((exStartHour - startHour) * 60) -startMin + exStartMin)/20);
        idxs[1] = ((((exEndHour - startHour) * 60) -startMin + exEndMin)/20) - 1;

        return idxs;
    }

    public LocalTime[] convertIdxToTime(LocalTime startTime,  int startIdx, int endIdx) {
        int st = startIdx * 20;
        int et = (endIdx) * 20;

        return new LocalTime[]{startTime.plusMinutes(st), startTime.plusMinutes(et)};
    }
}