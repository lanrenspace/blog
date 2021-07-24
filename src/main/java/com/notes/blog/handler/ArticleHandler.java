package com.notes.blog.handler;

import com.notes.blog.base.Constant;
import com.notes.blog.dto.PublishArticleBodyDTO;
import com.notes.blog.entity.Article;
import com.notes.blog.entity.ArticleLabel;
import com.notes.blog.entity.ArticleSort;
import com.notes.blog.entity.Labels;
import com.notes.blog.exceptions.SubmitDataNullException;
import com.notes.blog.repository.ArticleLabelRepository;
import com.notes.blog.repository.ArticleRepository;
import com.notes.blog.repository.ArticleSortRepository;
import com.notes.blog.repository.LabelsRepository;
import com.notes.blog.utils.PageParams;
import com.notes.blog.utils.UserUtil;
import com.notes.blog.vo.ResultData;
import com.notes.blog.vo.ResultPage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by HeLongJun on 2021/7/24 14:27
 *
 * @author lanrenspace@163.com
 * @Description:
 */
@RequiredArgsConstructor
@Component
public class ArticleHandler {

    private final ArticleRepository articleRepository;
    private final LabelsRepository labelsRepository;
    private final LabelsHandler labelsHandler;
    private final ArticleSortRepository articleSortRepository;
    private final ArticleLabelRepository articleLabelRepository;

    private static Logger logger = LoggerFactory.getLogger(ArticleHandler.class);

    /**
     * 初始化创建文章
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> initCreateArticle(ServerRequest request) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Article article = new Article();
        article.setArticleTitle(date);
        article.setArticleCommentCount(0);
        article.setArticleLikeCount(0);
        article.setArticleViews(0);
        article.setStatus(Constant.ArticleStatus.STATUS_UNPUBISHED);
        article.setWordCount(0);
        return articleRepository.save(article).map(ResultData::ok).flatMap(ResultData::getSuccess);
    }


    /**
     * 创建文章
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> createArticle(ServerRequest request) {
        return request.bodyToMono(PublishArticleBodyDTO.class).flatMap(publishArticleBodyDTO -> {
            if (publishArticleBodyDTO.getArticle() == null) {
                return Mono.error(new SubmitDataNullException("文章信息不能为空!"));
            }
            if (StringUtils.isEmpty(publishArticleBodyDTO.getArticle().getArticleContent())) {
                return Mono.error(new SubmitDataNullException("文章内容不能为空!"));
            }
            /**
             * 文章信息
             */
            Article article = publishArticleBodyDTO.getArticle();
            if (Constant.ArticleStatus.STATUS_PUBLISHED == article.getStatus() && article.getArticleDatetime() == null) {
                article.setArticleDatetime(LocalDateTime.now());
            }
            /**
             * 分类
             */
            List<Integer> sorts = publishArticleBodyDTO.getSorts();
            List<ArticleSort> articleSorts = new ArrayList<>();
            if (!CollectionUtils.isEmpty(sorts)) {
                articleSorts = sorts.stream().map(sortId -> new ArticleSort(article.getArticleId(), sortId)).collect(Collectors.toList());
            }
            /**
             * 标签
             */
            List<String> labels = publishArticleBodyDTO.getLabels();
            List<ArticleSort> finalArticleSorts = articleSorts;
            return articleRepository.findById(article.getArticleId()).flatMap(queryArticleInfo -> {
                List<ArticleLabel> articleLabels = new ArrayList<>();
                // 遍历标签
                return Flux.fromIterable(labels).flatMap(labelName -> {
                    Labels queryLabels = new Labels();
                    queryLabels.setLabelName(labelName);
                    queryLabels.setDelFlag(0);
                    return labelsRepository.findOne(Example.of(queryLabels)).flatMap(labels1 -> {
                        labels1.setArticleCount(labels1.getArticleCount() + 1);
                        articleLabels.add(new ArticleLabel(queryArticleInfo.getArticleId(), labels1.getLabelId()));
                        return labelsRepository.save(labels1);
                    }).switchIfEmpty(labelsHandler.createLabels(labelName)).flatMap(saveLabel -> {
                        saveLabel.setArticleCount(saveLabel.getArticleCount() + 1);
                        articleLabels.add(new ArticleLabel(queryArticleInfo.getArticleId(), saveLabel.getLabelId()));
                        return labelsRepository.save(saveLabel);
                    });
                }).collectList().switchIfEmpty(Mono.empty()).flatMap(fluxLabel -> {
                    queryArticleInfo.setOptionValues(publishArticleBodyDTO.getOptionValues());
                    queryArticleInfo.setArticleTitle(article.getArticleTitle());
                    queryArticleInfo.setArticleContent(article.getArticleContent());
                    return articleRepository.save(queryArticleInfo).flatMap(articleInfo -> {
                        articleSortRepository.saveAll(finalArticleSorts);
                        articleLabelRepository.saveAll(articleLabels);
                        return ResultData.getSuccess(articleInfo);
                    });
                });
            }).switchIfEmpty(ResultData.getError("文章信息不存在!"));
        }).switchIfEmpty(ResultData.getError("提交文章数据不能为空!"));
    }


    /**
     * 发布文章
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> publishedArticle(ServerRequest request) {
        String articleId = request.pathVariable("id");
        return articleRepository.findById(Integer.parseInt(articleId)).flatMap(article -> {
            article.setArticleDatetime(LocalDateTime.now());
            article.setStatus(Constant.ArticleStatus.STATUS_PUBLISHED);
            return articleRepository.save(article).flatMap(ResultData::getSuccess);
        }).switchIfEmpty(ResultData.getError("文章不存在,请检查后重试!"));
    }

    /**
     * 查询文章列表
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> queryArticlePage(ServerRequest request) {
        Flux<Article> articleFlux = articleRepository.findAllPage(PageParams.getPageSize(request), PageParams.getPageNumber(request));
        Mono<Long> count = articleRepository.count();
        return articleFlux.collectList().flatMap(articles ->
                count.flatMap(total -> ResultData.getSuccess(new ResultPage<>(articles, total))))
                .switchIfEmpty(ResultData.getSuccess(new ResultPage<>(null, 0)));
    }


    /**
     * 获取
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllArticles(ServerRequest request) {
        return ResultData.getSuccess("");
    }
}
