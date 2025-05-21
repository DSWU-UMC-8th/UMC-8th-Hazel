package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.domain.Mission;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.web.dto.MissionRequestDTO;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "미션 API", description = "미션 관련 API 입니다.")
public class MissionController {
    private final MissionCommandService MissionCommandService;

    @PostMapping("/api/mission/create")
    @Operation(summary = "미션 목록 추가",description = "특정 가게에 미션을 추가합니다.")
    public ApiResponse<Mission> createMission(
            @RequestBody @Valid MissionRequestDTO.createMissionDTO request
    ) {
        Mission response = MissionCommandService.createMission(request);
        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/api/mission/challenge")
    @Operation(summary = "미션 도전",description = "특정 유저가 미션을 도전합니다.")
    public ApiResponse<String> challengeMission(
            @RequestBody @Valid MissionRequestDTO.challengeMissionDTO request
    ){
        MissionCommandService.challengeMission(request);
        return ApiResponse.onSuccess("미션 도전에 성공했습니다.");
    }

}
