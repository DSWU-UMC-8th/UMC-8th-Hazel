package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MissionResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getStoreMissionsDTO{
        List<getMissionDTO> missions;
        Integer listSize;       // 반환된 데이터 개수
        Integer totalPage;      // 생성된 페이지 총 개수
        Long totalElements;     // 전체 데이터 총 개수
        Boolean isFirst;        // 현재 첫페이지가 맞는지
        Boolean isLast;         // 현재 마지막 페이지가 맞는지
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getMissionDTO{
        Integer reward;
        String missionSpec;
        LocalDate deadline;
    }
}
