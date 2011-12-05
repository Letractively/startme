package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Clob;
import java.math.BigDecimal;

/**
 *≥÷æ√¿‡
 */

public class AContent implements Serializable {


    private long id;

    private long contype;

    private String content;

    private long state;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the contype
	 */
	public long getContype() {
		return contype;
	}

	/**
	 * @param contype the contype to set
	 */
	public void setContype(long contype) {
		this.contype = contype;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the state
	 */
	public long getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(long state) {
		this.state = state;
	}

}