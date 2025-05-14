package com.esstm.siportalapi.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateUtils(데이트유틸스)
 * 날짜 및 시간 관련 공통 메서드를 제공하는 유틸리티 클래스
 */
public class DateUtils {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 현재 시간을 기본 패턴(yyyy-MM-dd HH:mm:ss)으로 포맷하여 반환
     */
    public static String now() {
        return format(LocalDateTime.now(), DEFAULT_PATTERN);
    }

    /**
     * 주어진 LocalDateTime을 지정된 패턴으로 포맷
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 주어진 LocalDate를 지정된 패턴으로 포맷
     */
    public static String format(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}