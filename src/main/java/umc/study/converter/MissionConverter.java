package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.web.dto.MissionRequestDTO;

public class MissionConverter {
    public static Mission createMission(MissionRequestDTO.createMissionDTO request, Store store) {
        return Mission.builder()
                .store(store)
                .reward(request.getReward())
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadline())
                .build();
    }
}
