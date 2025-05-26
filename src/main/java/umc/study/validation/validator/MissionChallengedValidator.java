package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.validation.annotation.UnchallengedMission;
import umc.study.web.dto.MissionRequestDTO;

@Component
@RequiredArgsConstructor
public class MissionChallengedValidator implements ConstraintValidator<UnchallengedMission, MissionRequestDTO.challengeMissionDTO> {

    private final MissionCommandService missionCommandService;

    @Override
    public void initialize(UnchallengedMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionRequestDTO.challengeMissionDTO request, ConstraintValidatorContext constraintValidatorContext) {

        Long memberId = request.getMemberId();
        Long missionId = request.getMissionId();

        if (memberId == null || missionId == null) return true;

        boolean isValid = missionCommandService.isAlreadyChallenging(memberId, missionId);

        if (isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGED.toString())
                    .addPropertyNode("missionId")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
