package com.kuehnenagel.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UC_STOCK_LEVEL_IFD")
public class StockLevel {

	private ControlSeg controlSeg;

	public ControlSeg getControlSeg() {
		return controlSeg;
	}

	@XmlElement(name="CTRL_SEG")
	public void setControlSeg(ControlSeg controlSeg) {
		this.controlSeg = controlSeg;
	}

}
