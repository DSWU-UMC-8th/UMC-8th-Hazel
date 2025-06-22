package umc.study.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RegionHandler;
import umc.study.converter.StoreConverter;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl extends StoreCommandService {
    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Store createStore(StoreRequestDTO.createStoreDTO request) {

        // 지역 찾기 + 만약, 지역이 없을 경우, 에러
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        // Converter 사용
        Store store = StoreConverter.toStore(request, region);

        // 가게 추가
        return storeRepository.save(store);
    }

    @Override
    public boolean existsById(Long value) {
        return storeRepository.existsById(value);
    }
}
