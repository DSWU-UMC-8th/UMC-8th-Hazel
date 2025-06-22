package umc.study.service.MissionService;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;

public interface MissionQueryService {
    Page<Mission> getStoreMissions(Long storeId, Integer page);
    Page<MemberMission> getMemberChallengingMissions(Long memberId, Integer page);
}
