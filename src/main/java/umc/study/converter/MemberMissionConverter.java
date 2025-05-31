package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

public class MemberMissionConverter {

    public static MemberMission createMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .status(MissionStatus.CHALLENGING)
                .mission(mission)
                .build();
    }

}
