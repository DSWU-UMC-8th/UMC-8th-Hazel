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
import umc.study.domain.Store;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.web.dto.StoreRequestDTO;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "가게 API", description = "가게 관련 API 입니다.")
public class StoreController {
    private final StoreCommandService StoreCommandService;
    private final StoreCommandService storeCommandService;

    @PostMapping("/api/store/create")
    @Operation(summary = "가게 목록 추가",description = "특정 지역에 가게를 추가합니다.")
    public ApiResponse<Store> createStore(
            @RequestBody @Valid StoreRequestDTO.createStoreDTO request
            ) {
        Store response = storeCommandService.createStore(request);
        return ApiResponse.onSuccess(response);
    }
}
