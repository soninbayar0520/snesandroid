
public class returnModel {

	public String Date; 
	public String Total;
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getTotal() {
		return Total;
	}
	public void setTotal(String total) {
		Total = total;
	}
	public returnModel(String date, String total) {
		super();
		Date = date;
		Total = total;
	}
	public returnModel() {
		super();
	}
	
	
}
