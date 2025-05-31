package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createStoreDTO{
        @NotNull(message = "지역 Id 작성은 필수입니다.")
        private Long regionId;

        @NotNull(message = "가게 이름 작성을 필수입니다.")
        private String name;

        @NotNull(message = "가게 주소 작성은 필수입니다.")
        private String address;

        private Float score;
    }
}
