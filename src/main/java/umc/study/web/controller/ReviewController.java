package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.web.dto.ReviewRequestDTO;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "리뷰 API", description = "리뷰 관련 API 입니다.")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/api/review/create")
    @Operation(summary = "리뷰 목록 추가",description = "특정 가게에 리뷰를 추가합니다.")
    public ApiResponse<Review> createReview(
            @RequestBody @Valid ReviewRequestDTO.createReviewDTO request
    ) {
        Review response = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(response);
    }
}
