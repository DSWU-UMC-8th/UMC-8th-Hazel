package umc.study.repository.ReviewRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public void createReview(Long memberId, Long storeId, String title, String body, Float score) {
        Review review = Review.builder()
                .member(Member.builder().id(memberId).build())     // 식별자 참조
                .store(Store.builder().id(storeId).build())
                .title(title)
                .body(body)
                .score(score)
                .build();

        entityManager.persist(review);
        // QueryDSL은 insert 없음
        // JPA의 EntityManager.persist() 사용해서 저장해야 함
    }
}
