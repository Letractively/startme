package com.yehongyu.mansys.ao.vo;

import java.util.List;

import com.yehongyu.mansys.dao.domain.SysMenuDO;

public class CascadeMenuVO extends SysMenuDO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5141535203468339080L;

	private List<CascadeMenuVO> cascadeMenuVOList;

	public List<CascadeMenuVO> getCascadeMenuVOList() {
		return cascadeMenuVOList;
	}

	public void setCascadeMenuVOList(List<CascadeMenuVO> cascadeMenuVOList) {
		this.cascadeMenuVOList = cascadeMenuVOList;
	}
	
	public void addCascadeMenuVOList(CascadeMenuVO cascadeMenuVO) {
		this.cascadeMenuVOList.add(cascadeMenuVO);
	}

}
