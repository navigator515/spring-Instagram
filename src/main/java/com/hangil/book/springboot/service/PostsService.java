package com.hangil.book.springboot.service;

import com.hangil.book.springboot.domain.posts.Posts;
import com.hangil.book.springboot.domain.posts.PostsRepository;
import com.hangil.book.springboot.web.dto.PostsListResponseDto;
import com.hangil.book.springboot.web.dto.PostsResponseDto;
import com.hangil.book.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(com.hangil.book.springboot.web.dto.PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto)
    {
        Posts posts= postsRepository.findById(id)
                .orElseThrow(()->new
                        IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
            return  id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new
                        IllegalArgumentException("해당 게시글이 없습니다. id "+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts=postsRepository.findById(id)
                .orElseThrow(()->new
                        IllegalArgumentException("해당 게시글이 없습니다. id="+id));

                postsRepository.delete(posts);
    }
}
