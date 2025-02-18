package kt.pet.domain.code.controller;

import io.swagger.v3.oas.annotations.Operation;
import kt.pet.domain.code.dto.CodeGroupDto;
import kt.pet.domain.code.entity.CodeGroup;
import kt.pet.domain.code.service.CodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/code-groups")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")  // 프론트엔드와 CORS 허용
public class CodeGroupController {

    private final CodeGroupService codeGroupService;

    // 전체 코드 그룹 조회
    @Operation(summary = "전체 코드 그룹 조회", description = "코드 그룹 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<CodeGroup>> getAllCodeGroups() {
        return ResponseEntity.ok(codeGroupService.getAllCodeGroups());
    }

    // 코드 그룹 생성
    @Operation(summary = "코드 그룹 생성", description = "코드 그룹을 생성합니다.")
    @PostMapping
    public ResponseEntity<CodeGroup> createCodeGroup(@RequestBody CodeGroupDto codeGroupDto) {
        System.out.println(codeGroupDto.getGroupId());
        System.out.println(codeGroupDto.getGroupName());
        System.out.println(codeGroupDto.getDescription());
        return ResponseEntity.ok(codeGroupService.saveCodeGroup(codeGroupDto));
    }

    // 코드 그룹 수정
    @Operation(summary = "전체 코드 그룹 조회", description = "코드 그룹을 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<CodeGroup> updateCodeGroup(@PathVariable String id, @RequestBody CodeGroup codeGroup) {
        return ResponseEntity.ok(codeGroupService.updateCodeGroup(id, codeGroup));
    }

    // 코드 그룹 삭제
    @Operation(summary = "전체 코드 그룹 조회", description = "코드 그룹의 코드 상세 목록을 조회합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCodeGroup(@PathVariable String id) {
        codeGroupService.deleteCodeGroup(id);
        return ResponseEntity.noContent().build();
    }
}
