package com.innovation.common.util;
/**
 * 常量配置，表名-字段名-类型
 * 
 * @author winston
 */
public enum Constant
{
	USER_ISCLOSED_YES("1"),
	USER_ISCLOSED_NO("0"),
	USER_USERLOCK_NO("0"),
	USER_USERLOCK_YES("1"),
	USER_AUTHMAIL_NOMAIL("0"),
	USER_RECOMMENDIND_NO("0"),
	USER_RECOMMENDIND_YES("1"),
	CONTENT_STATUS_INIT("0"),
	COMMENT_STATUS_INIT("0"),
	MESSAGE_ISREAD_NO(0),
	MESSAGE_ISREAD_YES(1),
	/*********每页数量********/
	PAGESIZE_FRIENDDAO(20),
	PAGESIZE_MYDAO(20),
	PAGESIZE_DAOCOMMENT(5),
	/*********是否是新浪用户********/
    USER_SINAFLAG_NO("0"),
    USER_SINAFLAG_YES("1"),
	/*********网站角色***********/
	//普通用户
	WEBSITE_ROLES_DEFAULT("daolife_user"),
	/*********消息类型********/
	//被人@
	MESSAGE_TYPE_AT("0"),
	//私信
	MESSAGE_TYPE_MAIL("1"),
	//被人关注
	MESSAGE_TYPE_FOLLOW("2"),
	//有人回复自己的叨
	MESSAGE_TYPE_COMMENT("3"),
	/*********消息*********/
	//被人@消息
	MESSAGE_MESSAGEBODY_ATMESSAGE("您被人@了"),
	
	//被人关注
	MESSAGE_MESSAGEBODY_ATTENTION("您被人关注了"),
	
	//被人回复
	MESSAGE_MESSAGEBODY_COMMENT("您的叨被人回复了"),
	/*********UP叨消息*********/
	//未登录
	UPDAO_ERRORMESSAGE_NOUSER("您还未登录，请您先登录"),
	//仅顶一次߶
	UPDAO_ERRORMESSAGE_ONLYONE("您已经顶过一次了"),
	//顶叨异常
	UPDAO_ERRORMESSAGE_EXCEPTION("顶叨出错了"),
	/*******session个人信息key*************/
	SESSION_USER_KEY("daoLifeUser"),
	
	ERROR_RETPASSWORD_NOPASSWORD("您未提交新的密码"),
	ERROR_RETPASSWORD_PASSWORDCONFIRMERROR("密码和确认密码不匹配"),
	ERROR_RETPASSWORD_NOIDERROR("您的帐户不存在")
	;
	
	
	private String strValue;
	private int intValue;

	Constant(String strValue)
	{
		this.strValue = strValue;
	}

	private Constant(int intValue)
	{
		this.intValue = intValue;
	}

	public String getStrValue()
	{
		return strValue;
	}

	public int getIntValue()
	{
		return intValue;
	}
}
