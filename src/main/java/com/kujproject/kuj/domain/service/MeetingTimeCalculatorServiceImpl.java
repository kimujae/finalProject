package com.kujproject.kuj.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kujproject.kuj.domain.meetingTimeCalculator.TimeSetupEntity;
import com.kujproject.kuj.domain.meetingTimeCalculator.TimeSetup_UserEntity;
import com.kujproject.kuj.domain.meetingTimeCalculator.UnableTimeSlotEntity;
import com.kujproject.kuj.domain.repository.TimeSetupDao;
import com.kujproject.kuj.domain.repository.TimeSetup_UserDao;
import com.kujproject.kuj.domain.repository.UnableTimeSlotDao;
import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.meetingTimeCalculator.*;
import com.kujproject.kuj.dto.user.UserRespDto;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.common.utils.MeetingTimeDisjointSet;
import com.kujproject.kuj.web.common.utils.MeetingTimeResolver;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.*;

@Service
public class MeetingTimeCalculatorServiceImpl implements MeetingTimeCalculatorService{
    private final MeetingTimeResolver meetingTimeResolver;
    private final TimeSetupDao timeSetupDao;
    private final UnableTimeSlotDao unableTimeSlotDao;
    private final UserDao userDao;
    private final TimeSetup_UserDao timeSetup_userDao;



    public MeetingTimeCalculatorServiceImpl(MeetingTimeResolver meetingTimeResolver, TimeSetupDao timeSetupDao, UnableTimeSlotDao unableTimeSlotDao, UserDao userDao, TimeSetup_UserDao timeSetup_userDao) {
        this.meetingTimeResolver = meetingTimeResolver;
        this.timeSetupDao = timeSetupDao;
        this.unableTimeSlotDao = unableTimeSlotDao;
        this.userDao = userDao;
        this.timeSetup_userDao = timeSetup_userDao;
    }


    @Override
    @Transactional
    public TimeSetupRespDto initialTimeSetup(InitialTimeSetupReqDto initialTimeSetupReqDto, Long boardId) throws JsonProcessingException {
        List<User> users = initialTimeSetupReqDto.getUsers();

        TimeSetupEntity initialTimeSetup = TimeSetupEntity.convertedBy(initialTimeSetupReqDto);
        timeSetupDao.save(initialTimeSetup);

        for(User user : users) {
            String userId = user.getUserId();
            Optional<UserEntity> userEntity = userDao.findByUserId(userId);
            UserEntity invitedUser = userEntity.orElseThrow(() ->
                    new BusinessExceptionHandler(ErrorCode.BUSINESS_EXCEPTION_ERROR));

            TimeSetup_UserEntity initialTimeSetup_user = TimeSetup_UserEntity.convertedBy(users.size(), invitedUser, initialTimeSetup);
            timeSetup_userDao.save(initialTimeSetup_user);
        }
        return TimeSetupRespDto.convertedBy(initialTimeSetup);
    }

    @Override
    public TimeSetupRespDto findByTimeSetupId(Long timeSetupId) throws JsonProcessingException {
        Optional<TimeSetupEntity> timeSetupEntity = timeSetupDao.findByTimeSetupId(timeSetupId);
        TimeSetupEntity timeSetup = timeSetupEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.MEETINGTIME_NOT_FOUND));

       return TimeSetupRespDto.convertedBy(timeSetup);
    }

    @Override
    public void deleteByTimeSetupId(Long timeSetupId) {
        int deletedCount = timeSetupDao.deleteByTimeSetupId(timeSetupId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.MEETINGTIME_NOT_FOUND);
        }
    }


    @Override
    public UnableTimeSlotEntity updateUnableTimeSlot(UpdateUnableTimeSlotDto updateUnableTimeSlotDto, Long timeSetupId) throws JsonProcessingException {
        Optional<TimeSetupEntity> timeSetupEntity = timeSetupDao.findByTimeSetupId(timeSetupId);
        TimeSetupEntity timeSetup = timeSetupEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.MEETINGTIME_NOT_FOUND));

        UnableTimeSlotEntity unableTimeSlotEntity = UnableTimeSlotEntity.convertedBy(updateUnableTimeSlotDto, timeSetup);
        unableTimeSlotDao.save(unableTimeSlotEntity);

        return null;
    }

    @Override
    public MeetingTimeResultRespDto getResultByTimeSetupId(Long timeSetupId) throws JsonProcessingException {
        Optional<TimeSetupEntity> meetingTimeEntity = timeSetupDao.findByTimeSetupId(timeSetupId);
        TimeSetupEntity timeSetup = meetingTimeEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.MEETINGTIME_NOT_FOUND));

        List<TimeSetup_UserEntity> meetingUserList = timeSetup_userDao.findAllByTimeSetup(timeSetup);
        if(meetingUserList.size() != meetingUserList.get(0).getInvitedUsersCount()) return null; // Business Error Throw 변경


        ObjectMapper objectMapper = new ObjectMapper();
        // 1. 미팅 링크 개설 시 입력한 days, times 파싱
        Days[] days = objectMapper.readValue(timeSetup.getDays(), Days[].class);
        Times[] times = objectMapper.readValue(timeSetup.getTimes(), Times[].class);

        List<MeetingTimeDisjointSet> disjointSetList = getDisjointSet(days.length, times);


        // 4. 사용자 입력 제외 시간 find
        List<UnableTimeSlotEntity> unableTimeSlotEntityList = unableTimeSlotDao.findAllByTimeSetup(timeSetup);



        List<List<LocalTime>> timeList= new ArrayList<>();
        for(int i = 0; i < days.length; i++){
            timeList.add(new ArrayList<>());
        }

        // 5. 제외 시간을 가지고 union-find, fset counter 진행
        for (int ds = 0; ds < days.length; ds++) {
            for (UnableTimeSlotEntity unableMeetingTimeSlot : unableTimeSlotEntityList) {
                String et = unableMeetingTimeSlot.getExclusionTimes();
                Times[][] excludedTimes = objectMapper.readValue(et, Times[][].class);

                for (int i = 0; i < excludedTimes.length; i++) {
                    for (int j = 0; j < excludedTimes[i].length; j++) {
                        LocalTime startTime = LocalTime.parse(excludedTimes[i][j].getStartTime());
                        LocalTime endTime = LocalTime.parse(excludedTimes[i][j].getEndTime());

                        int[] idxs = meetingTimeResolver.convertTimeToIdx(LocalTime.parse(times[i].getStartTime()),
                                startTime, endTime);

                        for (int k = idxs[0]; k <= idxs[1]; k++) {
                            disjointSetList.get(i).union(k, k + 1);
                            disjointSetList.get(i).setFrequencySet(k);
                        }
                    }
                }
            }

            // 6. 슬라이딩 윈도우로 target 시간만큼 최빈값 추출(우선순위로 저장)
            Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            }); // idx, frequency sum(min heapify)
            int sum = 0;
            int block = 5;

            for (int i = 0; i < block; i++) {
                sum += disjointSetList.get(ds).getFrequencySet()[i];
            }

            for (int i = 0; i < disjointSetList.get(ds).getFrequencySet().length - block; i++) {
                sum += disjointSetList.get(ds).getFrequencySet()[i + block];
                sum -= disjointSetList.get(ds).getFrequencySet()[i];

                pq.add(new int[]{i, sum});
            }

            int size = pq.size();
            for (int i = 0; i < size; i++) {
                int[] c = pq.poll();
                int id = c[0];
                LocalTime[] l = meetingTimeResolver.convertIdxToTime(LocalTime.parse(times[0].getStartTime()), id, id + block);
                System.out.println(l[0] + " ~ " + l[1] + " : " + c[1]);
            }

            for(int j = 0; j < days.length; j++) {
                for (int i = 0; i < disjointSetList.get(j).getSet().length; i++) {
                    LocalTime[] l = meetingTimeResolver.convertIdxToTime(LocalTime.parse(times[0].getStartTime()), i, i + 1);
                    timeList.get(j).add(l[0]);
                }
            }
        }
        MeetingTimeResultRespDto meetingTimeResultRespDto = new MeetingTimeResultRespDto();
        meetingTimeResultRespDto.setTimes(timeList);
        meetingTimeResultRespDto.setDays(days);
        meetingTimeResultRespDto.setResultSet(disjointSetList);

        return meetingTimeResultRespDto;
    }

    @Override
    public List<TimeSetupRespDto> findAllTimeSetupByUserId(String userId) throws JsonProcessingException {
        Optional<UserEntity> userEntity = userDao.findByUserId(userId);
        UserEntity user = userEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

        List<TimeSetup_UserEntity> timeSetupUserEntityList = timeSetup_userDao.findAllByUser(user);
        List<TimeSetupRespDto> timeSetupRespDtoList = new ArrayList<>();
        for(TimeSetup_UserEntity meetingUser : timeSetupUserEntityList) {
            TimeSetupEntity timeSetupEntity = meetingUser.getTimeSetup();

            timeSetupRespDtoList.add(TimeSetupRespDto.convertedBy(timeSetupEntity));
        }

        return timeSetupRespDtoList;
    }

    @Override
    public List<UserRespDto> findInvitedUsersByTimeSetupId(Long timeSetupId) throws JsonProcessingException {
        Optional<TimeSetupEntity> timeSetupEntity = timeSetupDao.findByTimeSetupId(timeSetupId);
        TimeSetupEntity timeSetup = timeSetupEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.MEETINGTIME_NOT_FOUND));

        List<TimeSetup_UserEntity> timeSetupUserEntityList = timeSetup_userDao.findAllByTimeSetup(timeSetup);
        List<UserRespDto> userRespDtoList = new ArrayList<>();

        for(TimeSetup_UserEntity timeSetupUser : timeSetupUserEntityList) {
            UserEntity invitedUser = timeSetupUser.getUser();
            userRespDtoList.add(UserRespDto.convertedBy(invitedUser).build());
        }

        return userRespDtoList;
    }

    public List<MeetingTimeDisjointSet> getDisjointSet(int dayLength, Times[] times) {
        List<MeetingTimeDisjointSet> disjointSetList = new ArrayList<>();

        for (int i = 0; i < dayLength; i++) {
            LocalTime startTime = LocalTime.parse(times[i].getStartTime());
            LocalTime endTime = LocalTime.parse(times[i].getEndTime());
            int block = meetingTimeResolver.calculateBlock(startTime, endTime);

            MeetingTimeDisjointSet meetingTimeDisjointSet = new MeetingTimeDisjointSet(block);
            meetingTimeDisjointSet.makeSet();
            disjointSetList.add(meetingTimeDisjointSet);
        }
        return disjointSetList;
    }
}
