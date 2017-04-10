package platform.comm;

import java.util.List;

/**
 * 页码对象
 * @author gggao
 *
 */
public class Pagination {
	
	/**
	 * 默认每页记录数
	 */
	private static final Integer DEFAULT_PAGESIZE = 10;

	/**
	 * 总记录数
	 */
	private Long totalResults;

	/**
	 * 每页的记录数
	 */
	private Integer pageSize;

	/**
	 * 当前页
	 */
	private Integer currentPage = 1;

	/**
	 * 数据集合
	 */
	@SuppressWarnings("unchecked")
	private List datas;

	public Pagination() {
		super();
		this.pageSize = DEFAULT_PAGESIZE;
	}

	public Pagination(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前页码
	 * @return
	 */
	public Integer getCurrentPage() {
		if(currentPage <= 0) return 1;
		return currentPage;
	}

	/**
	 * 设置当前页码
	 * @param currentPage
	 */
	public void setCurrentPage(Integer currentPage) {
		if (currentPage < 1)
			currentPage = 1;
		this.currentPage = currentPage;
	}

	/**
	 * 取得总页数
	 * 
	 * @return
	 */
	public Integer getTotalPages() {
		return ((Double) Math.ceil(totalResults / (double) pageSize)).intValue();
	}

	/**
	 * 获取每页记录数
	 * @return
	 */
	public Integer getPageSize() {
		if(pageSize <= 0){
			pageSize = DEFAULT_PAGESIZE;
		}
		return pageSize;
	}

	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getTotalResults() {
		return totalResults;
	}

	/**
	 * 获取上一页
	 * 
	 * @return
	 */
	public Integer getPreviousPage() {
		if (currentPage <= 1)
			return 1;
		else
			return currentPage - 1;
	}

	/**
	 * 获取下一页
	 * 
	 * @return
	 */
	public Integer getNextPage() {
		if (this.getCurrentPage() >= this.getTotalPages())
			return getTotalPages();
		else
			return (getCurrentPage() + 1);
	}
	
	/**
	 * 是否有上一页
	 * @return
	 */
	public Boolean getHasPreviousPage(){
		if(this.getCurrentPage() <= 1){
			return false;
		}
		return true;
	}
	
	/**
	 * 是否有下一页
	 * @return
	 */
	public Boolean getHasNextPage(){
		if(this.getCurrentPage() >= getTotalPages()){
			return false;
		}
		return true;
	}

	/**
	 * 分页时, 起始记录数
	 * <LI>如果当前页大于总页数，则重置为最后一页
	 * @return
	 */
	public Integer getFirstResult() {
		if(currentPage > getTotalPages()){
			this.setCurrentPage(getTotalPages());
		}
		return pageSize * (currentPage - 1);
	}
	
	/**
	 * 分页时, 截止记录数
	 * @return
	 */
	public Integer getEndResult(){
	    return getFirstResult() + getPageSize();
	}

	/**
	 * 获取数据集合
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getDatas() {
		return datas;
	}

	/**
	 * 设置数据集合
	 * 
	 * @param datas
	 *            数据集合
	 */
	@SuppressWarnings("unchecked")
	public void setDatas(List datas) {
		this.datas = datas;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
