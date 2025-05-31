package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.service.ReviewService.ReviewQueryService;
import umc.study.validation.annotation.PageCheck;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "유저 API", description = "유저 관련 API 입니다.")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/api/member/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/api/mypage/reviews")
    @Operation(summary = "특정 유저의 리뷰 목록 조회", description = "특정 유저의 리뷰 목록 조회 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요.")
    public ApiResponse<ReviewResponseDTO.getMemberReviewsDTO> getMemberReviews(
            @RequestParam(name = "memberId") Long memberId,
            @PageCheck @RequestParam(name = "page") Integer page   // 커스텀 어노테이션 사용
    ){
        return ApiResponse.onSuccess(ReviewConverter.getMemberReviewsDTO(reviewQueryService.getMemberReviews(memberId, page)));
    }
}
