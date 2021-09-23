package pojo;

import java.util.List;

public class Courses {
	private List<WebAutomation> WebAutomation;//it will expect that in WebAutomation class there will be more than one json
	private List<api> api;
	private List<Mobile> mobile;
	
	public List<WebAutomation> getWebAutomation() {
		return WebAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		WebAutomation = webAutomation;
	}
	public List<api> getApi() {
		return api;
	}
	public void setApi(List<api> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	
	
	
	
}
