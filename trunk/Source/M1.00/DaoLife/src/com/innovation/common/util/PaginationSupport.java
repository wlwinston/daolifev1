/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.common.util;

import java.io.Serializable;
import java.util.List;

public class  PaginationSupport implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1570146059476357813L;

	public final static int PAGESIZE = 30;

 
	private int pageSize = PAGESIZE;
	 private String pageSizeString ;
	 
	
	 private int totalCount;

	 private int currentPage;

	 private int startIndex;
	 
	 private String startIndexString;
	 
	 private int[] indexes = new int[0];
	 
	 private int[] pages = new int[0];
	 
	 private int nextIndex;

	 private int previousIndex;


	 private int pageCount;

	
	 private List items;
	 
	 private int lastIndex;
	 
	 private short nowUid ;
	 
	 public PaginationSupport(){
		 
	 }
	 
	 public  PaginationSupport(int pageSize,
	   int startIndex) {
	  setPageSize(pageSize);
	  //setStartIndex(startIndex);
	  this.startIndex = startIndex;
	 }

	 public  PaginationSupport(List items, int totalCount) {
	  setPageSize(PAGESIZE);
	  setTotalCount(totalCount);
	  setItems(items);
	  setStartIndex(0);
	 
	 }

	 public  PaginationSupport(List items, int totalCount, int startIndex) {
	  setPageSize(PAGESIZE);
	  setTotalCount(totalCount);
	  setItems(items);
	  setStartIndex(startIndex);
	  
	 }

	 public  PaginationSupport(List items, int totalCount, int pageSize,
	   int startIndex) {
	  setPageSize(pageSize);
	  setTotalCount(totalCount);
	  setItems(items);
	  setStartIndex(startIndex);
	  
	 }

	 
	 public void setTotalCount(int totalCount) {
	  if (totalCount > 0) {
	   this.totalCount = totalCount;
	   int count = totalCount / pageSize;
	   if (totalCount % pageSize > 0)
	    count++;
	   indexes = new int[count];
	   pages = new int[count];
	   for (int i = 0; i < count; i++) {
	    indexes[i] = pageSize * i;
	    pages[i]=i+1;
	   }
	    } else {
	   this.totalCount = 0;
	   //pages[0]=1;
	  }
	 }
	 public int getTotalCount() {
	  return totalCount;
	 }
	 public void setIndexes(int[] indexes) {
	  this.indexes = indexes;
	 }
	 public int[] getIndexes() {
	  return indexes;
	 }

	 
	 public void setStartIndex(int startIndex) {
	  if (totalCount <= 0)
	   this.startIndex = 0;
	  else if (startIndex >= totalCount)
	   this.startIndex = indexes[indexes.length - 1];
	  else if (startIndex < 0)
	   this.startIndex = 0;
	  else {
	   this.startIndex = indexes[startIndex / pageSize];
	  }
	   }
	 public int getStartIndex() {
	  return startIndex;
	 }

	 
	 public void setNextIndex(int nextIndex) {
	  this.nextIndex = nextIndex;
	 }
	 public int getNextIndex() {
	  int nextIndex = getStartIndex() + pageSize;
	  if (nextIndex >= totalCount)
	   return getStartIndex();
	  else
	   return nextIndex;
	 }
	 public void setPreviousIndex(int previousIndex) {
	  this.previousIndex = previousIndex;
	 }
	 
	 public int getPreviousIndex() {
	  int previousIndex = getStartIndex() - pageSize;
	  if (previousIndex < 0)
	   return 0;
	  else
	   return previousIndex;
	 }
	 public void setPageCount(int pageCount) {
	  this.pageCount = pageCount;
	 }
	 public int getPageCount() {
	  int count = totalCount / pageSize;
	  if (totalCount % pageSize > 0)
	   count++;
	  return count;
	 }
	 

	 public int getCurrentPage() {
	  return getStartIndex() / pageSize + 1;
	 }

	 public void setCurrentPage(int currentPage) {
	  this.currentPage = currentPage;
	 }

	 public void setLastIndex(int lastIndex) {
	  this.lastIndex =lastIndex ;
	 }
	 public int getLastIndex() {
		if(indexes.length>0)
			return indexes[indexes.length-1];
		else{
			return 0;
		}
	 }

	 
	 public int getPageSize() {
	  return pageSize;
	 }

	 public void setPageSize(int pageSize) {
	  this.pageSize = pageSize;
	 }

	 

	 public List getItems() {
	  return items;
	 }

	 public void setItems(List items) {
	  this.items = items;
	 }

	public String getStartIndexString() {
		return String.valueOf(startIndex);
	}

	public void setStartIndexString(String startIndexString) {
		startIndex = Integer.parseInt(startIndexString);
	}

	public String getPageSizeString() {
		return String.valueOf(pageSize);
	}

	public void setPageSizeString(String pageSizeString) {
		setPageSize(Integer.parseInt(pageSizeString));
	}

	public short getNowUid() {
		return nowUid;
	}

	public void setNowUid(short nowUid) {
		this.nowUid = nowUid;
	}


	}


