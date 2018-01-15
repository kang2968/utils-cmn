package com.ie.common.utilities.cmnutils.page;

import java.util.Map;


/**
 * @author bradly
 */
public class IEPageCriteria {
    
    private int page;
    
    private int pageSize;
    
    private Map<String, String> criteria;
    
    public int getPage() {
        return page < 0 ? 0 : page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getPageSize() {
        return pageSize <= 0 ? 20 : pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public Map<String, String> getCriteria() {
        return criteria;
    }
    
    public void setCriteria(Map<String, String> criteria) {
        this.criteria = criteria;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("IEPageCriteria:{");
        
        builder.append("page=").append(this.page).append(", ");
        builder.append("pageSize=").append(this.pageSize).append(", ");
        builder.append("criteria={");
        if (criteria != null && criteria.size() > 0) {
            criteria.forEach((k, v) -> builder.append(k).append("=").append(v).append(", "));
            builder.delete(builder.length() - 2, builder.length());
        }
        
        builder.append("}");
        return builder.toString();
    }
}
