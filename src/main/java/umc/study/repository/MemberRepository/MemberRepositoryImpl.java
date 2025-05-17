package umc.study.repository.MemberRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.Member;
import umc.study.domain.QMember;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    @Override
    public Member getMyPageInfo(Long memberId) {
        BooleanBuilder builder = new BooleanBuilder();

        if(memberId != null) {
            builder.and(member.id.eq(memberId));
        }

        return jpaQueryFactory
                .selectFrom(member)
                .where(builder)
                .fetchOne();   // 한 건만 가져오기
    }
}
