package com.kuehnenagel.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CTRL_SEG")
public class ControlSeg {

	private String trnnam;
	private Integer trnver;
	private String uuid;
	private String whId;
	private String clientId;
	private String iso2CtryName;
	private String requestId;
	private Integer routeId;

	public String getTrnnam() {
		return trnnam;
	}

	@XmlElement(name = "TRNNAM")
	public void setTrnnam(String trnnam) {
		this.trnnam = trnnam;
	}

	public Integer getTrnver() {
		return trnver;
	}

	@XmlElement(name = "TRNVER")
	public void setTrnver(Integer trnver) {
		this.trnver = trnver;
	}

	public String getUuid() {
		return uuid;
	}

	@XmlElement(name = "UUID")
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWhId() {
		return whId;
	}

	@XmlElement(name = "WH_ID")
	public void setWhId(String whId) {
		this.whId = whId;
	}

	public String getClientId() {
		return clientId;
	}

	@XmlElement(name = "CLIENT_ID")
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getIso2CtryName() {
		return iso2CtryName;
	}

	@XmlElement(name = "ISO_2_CTRY_NAME")
	public void setIso2CtryName(String iso2CtryName) {
		this.iso2CtryName = iso2CtryName;
	}

	public String getRequestId() {
		return requestId;
	}

	@XmlElement(name = "REQUEST_ID")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Integer getRouteId() {
		return routeId;
	}

	@XmlElement(name = "ROUTE_ID")
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

}
