package showAll;

public class IssueBean {

	public String nname;
	public String mobile;
	public String hospital;
	public String reason;
	public String doi;
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public IssueBean() {}
	public IssueBean(String nname, String mobile, String hospital, String reason, String doi) {
		super();
		this.nname = nname;
		this.mobile = mobile;
		this.hospital = hospital;
		this.reason = reason;
		this.doi = doi;
	}
	
	
}
