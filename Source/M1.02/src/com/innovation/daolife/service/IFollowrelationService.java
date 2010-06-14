/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.service;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.model.DlFriend;
import com.innovation.daolife.model.Followrelation;
import com.innovation.daolife.model.User;

public interface IFollowrelationService {
	public void addFollow(DlFriend friend);
	public void deleteFollow(DlFriend friend);
}
