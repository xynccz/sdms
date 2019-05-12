package com.honest.sdms.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.honest.sdms.ApplicationTests;
import com.honest.sdms.system.entity.Resources;
import com.honest.sdms.system.service.IResourcesService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BusinessManagerTest extends ApplicationTests{
	
	@Autowired
	private IResourcesService resourcesService;
	private static final String INDEX = "index", ICON = "icon", TITLE = "title";
	
	/**
	 * 获取左侧菜单列表
	 */
	@Test
	public void testGetMenusList() {
		List<Resources> list = resourcesService.findResourcesByRoleIds(new Long[] {1l}, 360L);
		String result = getCurrentMenusList(list);
		System.out.println(result);
	}
	
	/**
	 * 拼装左侧菜单列表
	 * @param sourceMap
	 * @return
	 */
	private String getCurrentMenusList(List<Resources> list) {
		JSONObject menusList = new JSONObject();
		
		JSONArray array = new JSONArray();
		
		JSONObject dashboard = new JSONObject();
		dashboard.put(ICON, "el-icon-lx-home");
		dashboard.put(INDEX, "dashboard");
		dashboard.put(TITLE, "系统首页");
		array.add(dashboard);
		  
		Map<Integer,Resources> rootMap = new TreeMap<Integer, Resources>();
		Map<Long,Map<Integer,Resources>> childMap = new HashMap<Long, Map<Integer,Resources>>();
		
		for(Resources resource : list) {
			Long parentId = resource.getParentId();
			int order = resource.getSortOrder();//菜单排列顺序
			
			if(parentId == null)//说明是跟节点
			{
				rootMap.put(order,resource);
			}else
			{
				if(!childMap.containsKey(parentId))
				{
					Map<Integer,Resources> map = new TreeMap<Integer, Resources>();
					map.put(order,resource);
					childMap.put(parentId, map);
				}else
				{
					Map<Integer,Resources> map = childMap.get(parentId);
					map.put(order,resource);
				}
			}
		}
		
		for(Iterator<Resources> rit = rootMap.values().iterator();rit.hasNext();) {
			Resources rs = rit.next();
			JSONObject rootNode = setResouece(rs);
			
			Long resourceId = rs.getResourceId();
			setTreeNodes(childMap, resourceId, rootNode);
			
			array.add(rootNode);
		}
		menusList.put("menulist", array);
		return menusList.toString();
	}

	private void setTreeNodes(Map<Long, Map<Integer, Resources>> childMap, Long resourceId, JSONObject rootNode) {
		if(childMap.containsKey(resourceId))//说明此节点下有子节点 
		{
			Map<Integer,Resources> map = childMap.get(resourceId);
			JSONArray subArray = new JSONArray();
			for(Iterator<Resources> sit = map.values().iterator();sit.hasNext();) {
				Resources srs = sit.next();
				JSONObject subObj = setResouece(srs);
				subArray.add(subObj);
				
				Long rid = srs.getResourceId();
				setTreeNodes(childMap, rid, subObj);
			}
			rootNode.put("subs", subArray);
		}
	}
	
	private static JSONObject setResouece(Resources rs) {
		JSONObject json = new JSONObject();
		json.put(ICON, rs.getIcon());
		json.put(INDEX, rs.getPath());
		json.put(TITLE, rs.getTitle());
		return json;
	}

}
