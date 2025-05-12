package umc.study.repository.ReviewRepository;

import umc.study.domain.Review;

public interface ReviewRepositoryCustom {
    void createReview(Long memberId, Long storeId, String title, String body, Float score);
}
