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
public class ArtClassify extends IdEntity {
	@NotBlank
	private String name;
	@ManyToOne
	@JoinColumn(name = "fid")
	private ArtClassify father;
	private Date updateTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArtClassify getFather() {
		return father;
	}
	public void setFather(ArtClassify father) {
		this.father = father;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
