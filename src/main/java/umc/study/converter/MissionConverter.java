package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static Mission createMission(MissionRequestDTO.createMissionDTO request, Store store) {
        return Mission.builder()
                .store(store)
                .reward(request.getReward())
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadline())
                .build();
    }

    public static MissionResponseDTO.getMissionDTO getMissionDTO(Mission mission) {
        return MissionResponseDTO.getMissionDTO.builder()
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResponseDTO.getMissionsDTO getStoreMissionsDTO(Page<Mission> missions) {
        List<MissionResponseDTO.getMissionDTO> missionsDTOList = missions.stream()
                .map(MissionConverter::getMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.getMissionsDTO.builder()
                .missions(missionsDTOList)
                .listSize(missionsDTOList.size())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }

    public static MissionResponseDTO.getMissionDTO getMissionDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();

        return MissionResponseDTO.getMissionDTO.builder()
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResponseDTO.getMissionsDTO getMemberMissionsDTO(Page<MemberMission> missions) {
        List<MissionResponseDTO.getMissionDTO> missionsDTOList = missions.stream()
                .map(MissionConverter::getMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.getMissionsDTO.builder()
                .missions(missionsDTOList)
                .listSize(missionsDTOList.size())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }
}
