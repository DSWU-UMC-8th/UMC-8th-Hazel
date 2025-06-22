package umc.study.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import umc.study.web.dto.MemberResponseDTO;

public interface MemberQueryService {
    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
