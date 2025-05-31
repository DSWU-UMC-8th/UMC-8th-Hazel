package umc.study.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.ReviewRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandServiceImpl extends ReviewCommandService {
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(ReviewRequestDTO.createReviewDTO request) {

        // 유저 찾기 + 만약, 유저 없을 경우 에러
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // 가게 찾기 + 만약, 가게 없을 경우 에러
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        // Converter 사용
        Review review = ReviewConverter.createReview(request, member, store);

        // 리뷰 저장
        return reviewRepository.save(review);
    }
}
