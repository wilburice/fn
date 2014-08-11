package fn.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import fn.entity.ArticleClassify;

public interface ArticleClassifyDao extends PagingAndSortingRepository<ArticleClassify,Long>,JpaSpecificationExecutor<ArticleClassify> {

}
