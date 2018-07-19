package card.validator.server.vo;


public class InspectData {
	//[검사원ID]#[버스ID]#[카드데이터]#[검사 결과 코드]#[검사시각]
	private String cardID;
	private String busID;
	private String inspectorID;
	private String cardInfo;
	private String inspectCode;
	private String inspectStartTime;
	public String getCardID() {
		return cardID;
	}
	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	public String getBusID() {
		return busID;
	}
	public void setBusID(String busID) {
		this.busID = busID;
	}
	public String getInspectorID() {
		return inspectorID;
	}
	public void setInspectorID(String inspectorID) {
		this.inspectorID = inspectorID;
	}
	public String getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}
	public String getInspectCode() {
		return inspectCode;
	}
	public void setInspectCode(String inspectCode) {
		this.inspectCode = inspectCode;
	}
	public String getInspectStartTime() {
		return inspectStartTime;
	}
	public void setInspectStartTime(String inspectStartTime) {
		this.inspectStartTime = inspectStartTime;
	}
	
	
	
}

