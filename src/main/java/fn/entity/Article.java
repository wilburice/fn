package fn.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

//JPA标识
@Entity
@Table(name = "fn_art_classify")
public class Article extends IdEntity {
	@NotBlank
	private String title;
	@ManyToOne
	@JoinColumn(name = "cid")
	private ArticleClassify artClassify;
	private String keyword;
	private String descri;
	private String pic;
	@ManyToOne
	@JoinColumn(name = "author")
	private User author;
	private Date addTime;
	private Date updateTime;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArticleClassify getArtClassify() {
		return artClassify;
	}
	public void setArtClassify(ArticleClassify artClassify) {
		this.artClassify = artClassify;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDescri() {
		return descri;
	}
	public void setDescri(String descri) {
		this.descri = descri;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
}
