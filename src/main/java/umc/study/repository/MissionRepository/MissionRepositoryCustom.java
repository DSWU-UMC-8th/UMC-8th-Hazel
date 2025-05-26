package umc.study.repository.MissionRepository;

import umc.study.domain.Mission;

import java.time.LocalDateTime;
import java.util.List;

public interface MissionRepositoryCustom {
    // 6주차 미션 1) 내가 진행 중, 진행 완료한 미션 모아서 보는 쿼리 -> QueryDSL 리팩토링
    List<Mission> findChallengingAndCompletedMissions(Long memberId, LocalDateTime lastCreatedAt, int pageSize);

    // 6주차 미션 3) 홈 화면 쿼리 (현재 선택된 지역에서 도전 가능한 미션 목록 및 페이징 포함) -> QueryDSL 리팩토링
    List<Mission> findAvailableMissionsInRegion(String regionName, LocalDateTime lastCreatedAt, int pageSize);
}
