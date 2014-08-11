package fn.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import fn.entity.Article;


public interface ArticleDao extends PagingAndSortingRepository<Article,Long>, JpaSpecificationExecutor<Article> {

}
