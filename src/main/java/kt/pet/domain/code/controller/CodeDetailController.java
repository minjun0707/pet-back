package kt.pet.domain.code.controller;

import io.swagger.v3.oas.annotations.Operation;
import kt.pet.domain.code.dto.CodeDetailDto;
import kt.pet.domain.code.entity.CodeDetail;
import kt.pet.domain.code.service.CodeDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/code-details")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // 프론트엔드와 CORS 허용
public class CodeDetailController {

    private final CodeDetailService codeDetailService;

    @Operation(summary = "특정 그룹의 코드 상세 목록 조회", description = "코드 그룹의 코드 상세 목록을 조회합니다.")
    // 특정 그룹의 코드 상세 목록 조회
    @GetMapping
    public ResponseEntity<List<CodeDetail>> getCodeDetails(@RequestParam String groupId) {
        return ResponseEntity.ok(codeDetailService.getCodeDetailsByGroupId(groupId));
    }

    // 코드 상세 생성
    @Operation(summary = "코드 상세 생성", description = "코드 그룹의 코드 상세를 생성합니다.")
    @PostMapping
    public ResponseEntity<CodeDetail> createCodeDetail(@RequestBody CodeDetailDto codeDetailDto) {
        return ResponseEntity.ok(codeDetailService.saveCodeDetail(codeDetailDto));
    }

    @Operation(summary = "코드 상세 수정", description = "코드 그룹의 코드 상세를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<CodeDetail> updateCodeDetail(@PathVariable String id, @RequestBody CodeDetail codeDetail) {
        return ResponseEntity.ok(codeDetailService.updateCodeDetail(id, codeDetail));
    }

    @Operation(summary = "코드 상세 삭제", description = "코드 그룹의 코드 상세를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCodeDetail(@PathVariable String id) {
        codeDetailService.deleteCodeDetail(id);
        return ResponseEntity.noContent().build();
    }
}
