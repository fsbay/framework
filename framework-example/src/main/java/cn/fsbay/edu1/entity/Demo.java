/**
 * 
 */
package cn.fsbay.edu1.entity;

import java.io.Serializable;

/**
 * @author AnyAi
 *
 */
public class Demo implements Serializable{
	/** 
     */  
    private static final long serialVersionUID = 1L;
    private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
