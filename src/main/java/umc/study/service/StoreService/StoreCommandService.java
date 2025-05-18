package umc.study.service.StoreService;

import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;

public abstract class StoreCommandService {
    public abstract Store createStore(StoreRequestDTO.createStoreDTO request);
}
