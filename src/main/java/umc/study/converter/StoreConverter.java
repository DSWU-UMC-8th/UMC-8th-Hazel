package umc.study.converter;

import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;

public class StoreConverter {
    public static Store toStore(StoreRequestDTO.createStoreDTO request, Region region){
        return Store.builder()
                .region(region)
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .build();
    }
}
