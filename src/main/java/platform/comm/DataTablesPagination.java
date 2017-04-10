package platform.comm;

import java.util.List;

public class DataTablesPagination {

	
	/**
	 * 每页条数
	 */
	private int iDisplayLength;
	
	/**
	 *开始数 
	 */
	private int iDisplayStart;
	
	/**
	 *数据总数 
	 */
	private int iTotalRecords;
	
	/**
	 * 
	 */
	private int iTotalDisplayRecords;
	
	/**
	 * 数据
	 */
	private List<?> data;
	
	/**
	 * 
	 */
	private String sEcho;
	
	/**
	 * 查询参数
	 */
	private String sSearch;
	/**
	 * 查询成功
	 */
	private boolean success=true;
	

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
