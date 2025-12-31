package in.co.rays.project_3.dto;

import java.util.Date;


/**
 * Initiative DTO contains intiative attributes.
 * 
 * @author Vedik Vishwakarma
 *
 */
public class InitiativeDTO extends BaseDTO {

	private String initiativeName;
	private String type;
	private Date StartDate;

	public String getInitiativeName() {
		return initiativeName;
	}

	public void setInitiativeName(String initiativeName) {
		this.initiativeName = initiativeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	private int version;

	public String getKey() {

		return id + "";
	}

	public String getValue() {

		return initiativeName;
	}

}
