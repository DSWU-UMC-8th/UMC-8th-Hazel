package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDTO;

public abstract class ReviewCommandService {
    public abstract Review createReview(ReviewRequestDTO.createReviewDTO request);
}
