package com.notes.blog.handler;

import com.notes.blog.entity.ArticleEncryption;
import com.notes.blog.exceptions.SubmitDataNullException;
import com.notes.blog.repository.ArticleEncryptionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

/**
 * Create by HeLongJun on 2021/7/23 15:27
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@RequiredArgsConstructor
@Component
public class ArticleEncryptionHandler {

    private final ArticleEncryptionRepository articleEncryptionRepository;


    /**
     * 默认密匙
     */
    @Value("${article.encryption.default}")
    private static String defaultEncryption;


    /**
     * 创建一个密匙
     *
     * @param articleId  文章ID
     * @param encryption 密匙内容
     * @return
     */
    public Mono<ArticleEncryption> createEncryption(Integer articleId, String encryption) {
        if (ObjectUtils.isEmpty(articleId) || StringUtils.isEmpty(encryption)) {
            return Mono.error(new SubmitDataNullException("文章ID或密匙不能为空!"));
        }
        ArticleEncryption articleEncryption = new ArticleEncryption();
        articleEncryption.setArticleId(articleId);
        articleEncryption.setEncryption(encryption);
        return articleEncryptionRepository.save(articleEncryption);
    }


    /**
     * 创建一个密匙
     *
     * @param articleId 文章ID
     * @return
     */
    public Mono<ArticleEncryption> createEncryption(Integer articleId) {
        return createEncryption(articleId, defaultEncryption);
    }
}
