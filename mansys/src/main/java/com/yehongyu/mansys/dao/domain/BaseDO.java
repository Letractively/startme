package com.yehongyu.mansys.dao.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * DO的一些公共方法，字段。所有DO需要继承此BaseDO
 * @author yingyang
 * @since 2011-11-11
 */
public class BaseDO implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 覆盖toString方法，打印DO对象
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}
	
	/**
	 * =============================================================================
	 * 做批量更新时的Where条件，根据查询出来的IdList设置。批量更新的对象通过本DO传送
	 */
	private List<Long> idList;
	/**
	 * 批量更新对象时，Ibatis获取要更新的IDList
	 */
	public List<Long> getIdList() {
		return idList;
	}
	/**
	 * 在Oracle语句优化时，转成table(str2numlist(#idListStr))使用
	 * @return
	 */
	public String getIdListStr(){
		return (idList!=null&&!idList.isEmpty())?idList.toString().replace('[', ' ').replace(']', ' '):null;
	}
	/**
	 * 批量更新对象时，设置要更新的IDList。批量更新的对象通过本DO传送
	 */
	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}
	/**
	 * 批量更新对象时，加入要更新的IDList。批量更新的对象通过本DO传送
	 */
	public void addIdList(Long id) {
        if (idList == null) {
        	idList = new ArrayList<Long>();
        }
        idList.add(id);
    }
	
	
	/**
	 * ====================================================================
	 * 做插入操作时判断DO是否已经校验过了，只在TripAgentValidate里用。运行时检查
	 */
	private boolean isValidate;
	/**
	 * 做插入操作时判断DO是否已经校验过了，只在TripAgentValidate里用。运行时检查
	 * @return
	 */
	public boolean isValidate() {
		return isValidate;
	}
	/**
	 * 做插入操作时判断DO是否已经校验过了，只在TripAgentValidate里用。运行时检查
	 * @param isValidate
	 */
	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}
	
}
