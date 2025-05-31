package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getMemberReviews(Long memberId, Integer page) {
        // 멤버 있는지 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<Review> memberReviews = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));

        return memberReviews;
    }
}
