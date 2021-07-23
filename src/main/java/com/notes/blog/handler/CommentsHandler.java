package com.notes.blog.handler;

import com.notes.blog.dto.CommentBodyDTO;
import com.notes.blog.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Create by HeLongJun on 2021/7/23 15:44
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@RequiredArgsConstructor
@Component
public class CommentsHandler {

    private final CommentsRepository commentsRepository;


    /**
     * 评论
     *
     * @param request
     * @return
     */
    /*public Mono<ServerResponse> comment(ServerRequest request) {
        request.bodyToMono(CommentBodyDTO.class).flatMap(commentBodyDTO -> {})

    }*/
}
