package umc.study.converter;

import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.ReviewRequestDTO;

public class ReviewConverter {
    public static Review createReview(ReviewRequestDTO.createReviewDTO request, Member member, Store store){
        return Review.builder()
                .member(member)
                .store(store)
                .title(request.getTitle())
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }
}
