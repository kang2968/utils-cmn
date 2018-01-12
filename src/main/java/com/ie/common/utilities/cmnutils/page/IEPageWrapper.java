package com.ie.common.utilities.cmnutils.page;

import java.util.List;

/**
 * @author bradly
 */
public class IEPageWrapper<T> {
    
    /**
     * current page number
     */
    private int page;
    
    /**
     * page size
     */
    private int pageSize;
    
    /**
     * the count of satisfied data in DB
     */
    private long total;
    
    /**
     * the collection of current page
     */
    private List<T> list;
    
    public IEPageWrapper() {
        super();
    }
    
    public IEPageWrapper(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }
    
    /**
     * whether invoke function to get data
     *
     * @param wrapper
     * @return
     */
    public static boolean query(IEPageWrapper wrapper) {
        return wrapper.getTotal() > wrapper.getPage() * wrapper.getPageSize();
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public long getTotal() {
        return total;
    }
    
    public void setTotal(long total) {
        this.total = total;
    }
    
    public List<T> getList() {
        return list;
    }
    
    public void setList(List<T> list) {
        this.list = list;
    }
    
}
