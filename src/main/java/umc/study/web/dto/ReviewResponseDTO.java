package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.validation.annotation.ExistStore;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getMemberReviewsDTO{
        List<getReviewDTO> reviews;
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
    public static class getReviewDTO{
        String nickname;
        String storeName;
        String title;
        String body;
        Float score;
        LocalDateTime createdAt;
    }
}
