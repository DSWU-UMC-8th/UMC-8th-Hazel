package umc.study.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.*;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.QMemberMission;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;
    private final QRegion region = QRegion.region;

    @Override
    public List<Mission> findChallengingAndCompletedMissions(Long memberId, LocalDateTime lastCreatedAt, int pageSize) {
        BooleanBuilder builder = new BooleanBuilder();

        if(memberId != null) {
            builder.and(memberMission.member.id.eq(memberId))
                    .and(memberMission.status.in(MissionStatus.CHALLENGING, MissionStatus.COMPLETE));
            // 해당 멤버의 미션만 조회
            // 미션 상태가 challenging, complete인 것만 조회

            if(lastCreatedAt != null) {
                builder.and(memberMission.createdAt.before(lastCreatedAt));
                // 커서 기반 페이징
                // 정렬 기준 desc -> before()
                // 이전 페이지의 마지막 미션보다 더 이전의 미션을 가지고 오라는 뜻으로 사용
            }
        }

        return jpaQueryFactory
                .select(memberMission.mission)
                .from(memberMission)
                .join(memberMission.mission, mission)
                .where(builder)
                .orderBy(memberMission.createdAt.desc())    // 최신순 정렬
                .limit(pageSize)    // pageSize 만큼 조회
                .fetch();
    }

    @Override
    public List<Mission> findAvailableMissionsInRegion(String regionName, LocalDateTime lastCreatedAt, int pageSize) {
        BooleanBuilder builder = new BooleanBuilder();

        if(regionName != null) {
            builder.and(store.region.name.eq(regionName));
        }

        if(lastCreatedAt != null) {
            builder.and(mission.createdAt.after(lastCreatedAt));
            // 커서 기반 페이징
            // 정렬 기준 asc -> after()
            // 커서보다 이후의 미션을 가지고 오라는 뜻으로 사용
        }

        return jpaQueryFactory
                .selectFrom(mission)
                .join(mission.store, store).fetchJoin()
                .join(store.region, region).fetchJoin()     // fetchJoin() -> N+1 방지
                .where(builder)
                .orderBy(mission.createdAt.asc())   // 오래된 순 정렬
                .limit(pageSize)
                .fetch();
    }

}
