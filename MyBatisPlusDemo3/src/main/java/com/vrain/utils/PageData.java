package com.vrain.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class PageData extends HashMap implements Map {
	private static final long serialVersionUID = 1L;
	Map map = null;
	HttpServletRequest request;

	public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();//获取
		Map<String ,String> tempMap = new HashMap<>();

		if (properties != null) {
			for (Iterator iter = properties.entrySet().iterator(); iter.hasNext();) {
				Entry entry = (Entry) iter.next();

				String key = (String) entry.getKey();
				String value="";
				Object valueObj = entry.getValue();

				if (null == valueObj) {
					valueObj = "";
				} else if (valueObj instanceof String[]) {
					StringBuilder temp = new StringBuilder();
					for (String str : (String[]) valueObj) {
						temp.append(str + ",");
					}
					value = temp.toString().substring(0, temp.toString().length() - 1);// 去掉最后的逗号
				} else {
					value = valueObj.toString();
				}

				tempMap.put(key, value);
			}

			for(Entry<String , String> entry:tempMap.entrySet()) {
				System.out.println("PageData-PageData:"+entry.getKey()+"--"+entry.getValue());
			}
		}
		
		this.map=tempMap;

	}

	public PageData() {
		map = new HashMap();
	}

	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);// 获取到值
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		return (String) get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	/**
	 * 从此映射中移除所有映射关系
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * 如果此映射包含指定键的映射关系，则返回 true。
	 */
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	/**
	 * 如果此映射将一个或多个键映射到指定值，则返回 true。
	 */
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	/**
	 * 返回此映射中包含的映射关系的 Set 视图。
	 */
	public Set entrySet() {
		return map.entrySet();
	}

	/**
	 * 如果此映射未包含键-值映射关系，则返回 true。
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * 返回此映射中包含的键的 Set 视图。
	 */
	public Set keySet() {
		return map.keySet();
	}

	/**
	 * 从指定映射中将所有映射关系复制到此映射中（可选操作）。
	 */
	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		map.putAll(t);
	}

	/**
	 * 返回此映射中的键-值映射关系数。
	 */
	public int size() {
		return map.size();
	}

	/**
	 * 返回此映射中包含的值的 Collection 视图。
	 */
	public Collection values() {
		return map.values();
	}

}
