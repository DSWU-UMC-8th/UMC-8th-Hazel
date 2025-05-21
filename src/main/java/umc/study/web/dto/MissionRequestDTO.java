package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.validation.annotation.UnchallengedMission;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createMissionDTO{
        @NotNull(message = "가게 Id 작성은 필수입니다.")
        private Long storeId;

        private Integer reward;

        @NotNull(message = "미션 내용 작성은 필수입니다.")
        private String missionSpec;

        private LocalDate deadline;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @UnchallengedMission
    public static class challengeMissionDTO{
        @NotNull(message = "유저 Id 작성은 필수입니다.")
        private Long memberId;

        @NotNull(message = "미션 Id 작성은 필수입니다.")
        private Long missionId;
    }
}
