package com.yehongyu.mansys.ao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.yehongyu.mansys.ao.PermissionAO;
import com.yehongyu.mansys.dao.domain.SysMenuDO;
import com.yehongyu.mansys.dao.domain.SysUserMenuDO;
import com.yehongyu.mansys.dao.ibatis.SysMenuDAO;
import com.yehongyu.mansys.dao.ibatis.SysUserMenuDAO;
import com.yehongyu.mansys.dao.query.SysMenuQuery;
import com.yehongyu.mansys.dao.query.SysUserMenuQuery;

public class PermissionAOImpl implements PermissionAO {
	@Resource
	SysMenuDAO sysMenuDAO;
	
	@Resource
	SysUserMenuDAO sysUserMenuDAO;

	/**
	 * 判断用户是否具有菜单权限
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public boolean isAuthenMenu(long userid, long menuid) {
		SysUserMenuQuery sysUserMenuQuery = new SysUserMenuQuery();
		sysUserMenuQuery.setUserid(userid);
		sysUserMenuQuery.setMenuid(menuid);
		SysUserMenuDO sysUserMenuDO = sysUserMenuDAO.getSysUserMenuDO(sysUserMenuQuery);
		return sysUserMenuDO != null ? true : false;
	}
	
	public List<SysMenuDO> getAllMenu(){
		//查询出所有有效的菜单
		SysMenuQuery sysMenuQuery = new SysMenuQuery();
		sysMenuQuery.setStatus(1);
		sysMenuQuery.orderbyDisplayorder(true);
		List<SysMenuDO> sysMenuList = sysMenuDAO.getSysMenuDOList(sysMenuQuery);
		//分级去菜单列表
		List<SysMenuDO> sysMenuLevelOneList = new ArrayList<SysMenuDO>();
		List<SysMenuDO> sysMenuLevelTwoList = new ArrayList<SysMenuDO>();
		List<SysMenuDO> sysMenuLevelThreeList = new ArrayList<SysMenuDO>();
		for(SysMenuDO s:sysMenuList){
			if(s.getMenulevel()==null){
				continue;
			}else if(s.getMenulevel()==1){
				sysMenuLevelOneList.add(s);
			}else if(s.getMenulevel()==2){
				sysMenuLevelTwoList.add(s);
			}else if(s.getMenulevel()==3){
				sysMenuLevelThreeList.add(s);
			}
		}

		for(SysMenuDO s1:sysMenuLevelOneList){	//把三级菜单加入二级菜单
			for(SysMenuDO s2:sysMenuLevelTwoList){	//把三级菜单加入二级菜单
				for(SysMenuDO s3:sysMenuLevelThreeList){
					if(s3.getParentscode()==s2.getMenucode()){
						s2.addSysMenuDOList(s3);
					}
				}
				if(s2.getParentscode()==s1.getMenucode()){
					s1.addSysMenuDOList(s2);
				}
			}
		}
		return sysMenuLevelOneList;
	}

}
