package com.kuehnenagel.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UC_STOCK_LEVEL_IFD")
public class StockLevel {

	private ControlSeg controlSeg;
	private Integer responseStatusCode;
	private String responseMessage;

	public ControlSeg getControlSeg() {
		return controlSeg;
	}

	@XmlElement(name="CTRL_SEG")
	public void setControlSeg(ControlSeg controlSeg) {
		this.controlSeg = controlSeg;
	}

	public Integer getResponseStatusCode() {
		return responseStatusCode;
	}

	public void setResponseStatusCode(Integer responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
