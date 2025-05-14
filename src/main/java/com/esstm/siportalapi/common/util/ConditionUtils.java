package com.esstm.siportalapi.common.util;

import com.esstm.siportalapi.common.dto.Condition;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Condition 리스트를 Map 으로 변환해 주는 유틸 클래스
 */
public class ConditionUtils {

    private ConditionUtils() { /* 유틸 클래스라 인스턴스화 금지 */ }

    /**
     * Condition 리스트를 field → value 맵으로 변환
     */
    public static Map<String, Object> toMap(List<Condition> conditions) {
        if (conditions == null) {
            throw new IllegalArgumentException("conditions must not be null");
        }

        try {
            return conditions.stream()
                    .collect(Collectors.toMap(
                            Condition::getField,
                            Condition::getValue,
                            (oldV, newV) -> newV  // 같은 키가 중복되면 나중 값으로 덮어씌움
                    ));
        } catch (Exception e) {
            throw new RuntimeException("Condition 변환 중 오류 발생: " + e.getMessage(), e);
        }
    }
}
