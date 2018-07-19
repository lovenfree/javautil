
package card.validator.server.vo;

public class CardInfo {
	//[카드ID(8)][버스ID(7)][승차/하차 코드(1)][최근 승차시각(14)]
	private String cardID;
	private String busID;
	private String getInOutCode;
	private String recentRideTime;
	public CardInfo(String cardID, String busID, String getInOutCode,
			String recentRideTime) {
		super();
		this.cardID = cardID;
		this.busID = busID;
		this.getInOutCode = getInOutCode;
		this.recentRideTime = recentRideTime;
	}
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
	public String getGetInOutCode() {
		return getInOutCode;
	}
	public void setGetInOutCode(String getInOutCode) {
		this.getInOutCode = getInOutCode;
	}
	public String getRecentRideTime() {
		return recentRideTime;
	}
	public void setRecentRideTime(String recentRideTime) {
		this.recentRideTime = recentRideTime;
	}
	
	
}
