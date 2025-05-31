package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.repository.FoodCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCategoryService {
    private final FoodCategoryRepository foodCategoryRepository;

    public boolean existsByIds(List<Long> values) {
        return values.stream()
                .allMatch(value -> foodCategoryRepository.existsById(value));
    }
}
