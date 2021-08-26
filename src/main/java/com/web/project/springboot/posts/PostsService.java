package com.web.project.springboot.posts;

import com.web.project.springboot.domain.posts.PostsRepository;
import com.web.project.springboot.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto RequestDto){
        return postsRepository.save(RequestDto.toEntity()).getId();
    }
}
