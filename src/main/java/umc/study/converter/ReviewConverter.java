package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static ReviewResponseDTO.getReviewDTO getReviewDTO(Review review){
            return ReviewResponseDTO.getReviewDTO.builder()
                    .nickname(review.getMember().getName())
                    .storeName(review.getStore().getName())
                    .title(review.getTitle())
                    .body(review.getBody())
                    .score(review.getScore())
                    .createdAt(review.getCreatedAt())
                    .build();
    }

    public static ReviewResponseDTO.getMemberReviewsDTO getMemberReviewsDTO(Page<Review> reviews){

        List<ReviewResponseDTO.getReviewDTO> reviewsDTOList = reviews.stream()
                .map(ReviewConverter::getReviewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.getMemberReviewsDTO.builder()
                .reviews(reviewsDTOList)
                .listSize(reviewsDTOList.size())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();
    }
}
