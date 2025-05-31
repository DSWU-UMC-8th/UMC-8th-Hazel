package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.service.ReviewService.ReviewQueryService;

import static umc.study.domain.enums.MissionStatus.CHALLENGING;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<Mission> getStoreMissions(Long storeId, Integer page) {
        // store 있는지 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.STORE_NOT_FOUND));

        Page<Mission> storeMissions = missionRepository.findAllByStore(store, PageRequest.of(page, 10));

        return storeMissions;
    }

    @Override
    public Page<MemberMission> getMemberChallengingMissions(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<MemberMission> memberMissions = memberMissionRepository.findAllByMemberAndStatus(member, CHALLENGING, PageRequest.of(page, 10));

        return memberMissions;
    }
}
