package com.example.b_team_shopping_mall.controller;


import com.example.b_team_shopping_mall.dto.Claim.ClaimCreateRequestDto;
import com.example.b_team_shopping_mall.dto.Claim.ClaimEditRequestDto;
import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class ClaimController {

    private final ClaimService claimService;

    // 문의글 전체 조회
    @GetMapping("/claims")
    @ResponseStatus(HttpStatus.OK)
    public Response getBoards() {
        return Response.success(claimService.findAll());
    }

    // 문의글 단일 조회
    @GetMapping("/claims/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getBoard(@PathVariable Long id) {
        return Response.success(claimService.findBoard(id));
    }

    // 문의글 생성
    @PostMapping("/claims")
    @ResponseStatus(HttpStatus.CREATED)
    public Response saveBoard(@RequestBody @Valid ClaimCreateRequestDto requestDto) {
        return Response.success(claimService.save(requestDto));
    }

    // 문의글 수정
    @PutMapping("/claims/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response editBoard(@RequestBody @Valid ClaimEditRequestDto requestDto, @PathVariable Long id) {
        return Response.success(claimService.edit(requestDto, id));
    }

    // 문의글 삭제
    @DeleteMapping("/claims/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable Long id) {
        claimService.delete(id);
    }
}
