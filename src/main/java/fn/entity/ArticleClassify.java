package fn.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

//JPA标识
@Entity
@Table(name = "fn_art_classify")
public class ArticleClassify extends IdEntity {
	@NotBlank
	private String name;
	@ManyToOne
	@JoinColumn(name = "fid")
	private ArticleClassify father;
	private Date updateTime;
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE },fetch=FetchType.LAZY,mappedBy="artClassify")
	private Set<Article> artList;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArticleClassify getFather() {
		return father;
	}
	public void setFather(ArticleClassify father) {
		this.father = father;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Set<Article> getArtList() {
		return artList;
	}
	public void setArtList(Set<Article> artList) {
		this.artList = artList;
	}
	
	
}
