package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.dto.*;
import com.example.b_team_shopping_mall.entity.Claim;
import com.example.b_team_shopping_mall.exception.ClaimNotFoundException;
import com.example.b_team_shopping_mall.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;

    @Transactional(readOnly = true)
    public List<ClaimListResponseDto> findAll() {
        return claimRepository.findAll().stream().map(s -> new ClaimListResponseDto().toDto(s)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClaimGetResponseDto findBoard(Long id) {
        Claim claim = claimRepository.findById(id).orElseThrow(ClaimNotFoundException::new);
        return new ClaimGetResponseDto().toDto(claim);
    }
    @Transactional
    public ClaimCreateResponseDto save(ClaimCreateRequestDto requestDto) {
        Claim serviceboards = new Claim(requestDto.getTitle(), requestDto.getContent(), requestDto.getTitle());
        claimRepository.save(serviceboards);

        return new ClaimCreateResponseDto().toDto(serviceboards);
    }

    @Transactional
    public ClaimEditResponseDto edit(ClaimEditRequestDto requestDto, Long id) {
        Claim findItem = claimRepository.findById(id).orElseThrow(ClaimNotFoundException::new);

        findItem.setTitle(requestDto.getTitle());
        findItem.setContent(requestDto.getContent());

        return new ClaimEditResponseDto().toDto(findItem);
    }
    @Transactional
    public void delete(Long id) {
        claimRepository.deleteById(id);
    }
}
