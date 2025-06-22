package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createReviewDTO{
        @NotNull(message = "유저 Id 작성은 필수입니다.")
        private Long memberId;

        @ExistStore
        @NotNull(message = "가게 Id 작성은 필수입니다.")
        private Long storeId;

        @NotNull(message = "리뷰 제목 작성은 필수입니다.")
        private String title;

        private String body;

        @NotNull(message = "리뷰 별점은 필수입니다.")
        private Float score;
    }
}
