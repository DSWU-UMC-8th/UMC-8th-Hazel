package umc.study.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Store;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
    // 가게 찾기
    Optional<Store> findById(Long id);
}