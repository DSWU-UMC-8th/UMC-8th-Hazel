package umc.study.service.MissionService;

import umc.study.domain.Mission;
import umc.study.web.dto.MissionRequestDTO;

public abstract class MissionCommandService {
    // 가게에 미션 추가하기
    public abstract Mission createMission(MissionRequestDTO.createMissionDTO request);

    public abstract boolean isAlreadyChallenging(Long memberId, Long missionId);

    public abstract void challengeMission(MissionRequestDTO.challengeMissionDTO request);

    public abstract void completeMission(Long memberId, Long missionId);
}
