package com.honest.sdms.system.service.imp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.ResourcesMapper;
import com.honest.sdms.system.entity.Resources;
import com.honest.sdms.system.service.IResourcesService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ResourcesServiceImp extends BaseServiceImp<Resources, Long> implements IResourcesService{
	
	private ResourcesMapper resourcesMapper; 
	private static final String ID = "id", CHILDREN = "children", TITLE = "title";

	@Resource
	@Qualifier("resourcesMapper")
	@Override
	public void setBaseDao(IBaseMapper<Resources, Long> baseMapper) {
		this.baseMapper = baseMapper;
		resourcesMapper = (ResourcesMapper)baseMapper;
	}
	
	@Override
	public List<Resources> findResourcesByRoleIds(Long[] roleIds, Long organizationId) {
		return resourcesMapper.findResourcesByRoleIds(roleIds, organizationId);
	}

	/**
	 * 获取角色对应的资源信息
	 * @param roleId 角色id
	 * @return
	 */
	@Override
	public JSONArray getResourcesTree() {
		JSONArray result = new JSONArray();
		
		Resources cond = new Resources();
		cond.setOrganizationId(Constants.getCurrentSysUser().getOrganizationId());
		List<Resources> resourcesList = this.findByCond(cond); 
		
		if (resourcesList != null && resourcesList.size() > 0) 
		{
			Map<Long, Resources> sourceMap = new HashMap<Long, Resources>();
			for (Resources rs : resourcesList) {
				Long resoruceId = rs.getResourceId();
				if(!sourceMap.containsKey(resoruceId))
				{
					sourceMap.put(resoruceId, rs);
				}
			}
			result = getResourceTree(sourceMap);
		}
		
		return result;
	}
	
	/**
	 * 获取角色对应的资源树
	 * @param sourceMap
	 * @return
	 */
	private JSONArray getResourceTree(Map<Long, Resources> sourceMap) {
		JSONArray array = new JSONArray();
		
		Map<Integer,Resources> rootMap = new TreeMap<Integer, Resources>();
		Map<Long,Map<Integer,Resources>> childMap = new HashMap<Long, Map<Integer,Resources>>();
		
		for(Iterator<Entry<Long, Resources>> it = sourceMap.entrySet().iterator();it.hasNext();) {
			Entry<Long, Resources> entry = it.next();
			Resources resource = entry.getValue();
			Long parentId = resource.getParentId();
			int order = resource.getSortOrder();//菜单排列顺序
			
			if(parentId == null)//说明是根节点
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
		return array;
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
			rootNode.put(CHILDREN, subArray);
		}
	}
	
	private static JSONObject setResouece(Resources rs) {
		JSONObject json = new JSONObject();
		json.put(ID, rs.getResourceId());
		json.put(TITLE, rs.getTitle()+":"+rs.getResourceId());
		return json;
	}

}
