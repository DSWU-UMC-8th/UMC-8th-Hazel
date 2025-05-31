package umc.study.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.apiPayload.exception.handler.MissionHandler;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.converter.MemberMissionConverter;
import umc.study.converter.MissionConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MissionRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionCommandServiceImpl extends MissionCommandService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public Mission createMission(MissionRequestDTO.createMissionDTO request) {

        // 가게 찾기 + 만약, 가게가 없을 경우, 에러
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        // Converter 사용
        Mission mission = MissionConverter.createMission(request, store);

        // 미션 저장
        return missionRepository.save(mission);
    }

    @Override
    public boolean isAlreadyChallenging(Long memberId, Long missionId) {
        return memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId);
    }

    @Override
    public void challengeMission(MissionRequestDTO.challengeMissionDTO request) {

        // 유저 찾기
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // 미션 찾기
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        // Converter 사용
        MemberMission memberMission = MemberMissionConverter.createMemberMission(member, mission);

        // 저장
        memberMissionRepository.save(memberMission);
    }

    @Override
    public void completeMission(Long memberId, Long missionId) {
        // 유저 찾기
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // 미션 찾기
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = memberMissionRepository.findByMemberAndMission(member, mission)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_MISSION_NOT_FOUND));

        if (memberMission.getStatus() != MissionStatus.CHALLENGING) {
            throw new MemberHandler(ErrorStatus.MISSION_ALREADY_COMPLETED);
        }

        memberMission.changeStatus(MissionStatus.COMPLETE);
    }
}
