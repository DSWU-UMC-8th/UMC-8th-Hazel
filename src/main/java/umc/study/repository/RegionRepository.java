package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Region;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    // 지역 찾기
    Optional<Region> findById(Long id);
}
