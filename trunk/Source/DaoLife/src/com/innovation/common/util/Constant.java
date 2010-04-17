package com.innovation.common.util;
/**
 * 此类中封装了在系统中用到的一些固定不变的常量信息。 所有的常量信息(除了通用的除外)的命名规则为 表名-字段名-含义
 * 如果表名的最后一个单词与字段名的第一将单词相同则省略一个。
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
	MESSAGE_ISREAD_NO(0),
	MESSAGE_ISREAD_YES(1),
	/*********角色************/
	//默认角色
	WEBSITE_ROLES_DEFAULT("daolife_user"),
	/*********消息类型*********/
	//@消息
	MESSAGE_TYPE_AT("0"),
	//站内信
	MESSAGE_TYPE_MAIL("1"),
	//关注
	MESSAGE_TYPE_FOLLOW("2"),
	/*********消息内容*********/
	//@消息内容
	MESSAGE_MESSAGEBODY_ATMESSAGE("你被人@了"),
	/*********UP错误信息*********/
	//没有登录
	UPDAO_ERRORMESSAGE_NOUSER("您还没有登录，请先登录"),
	//多次顶同一叨
	UPDAO_ERRORMESSAGE_ONLYONE("您已经顶过一次该叨，请不要重复顶"),
	//操作异常
	UPDAO_ERRORMESSAGE_EXCEPTION("操作失败，请联系网站管理员"),
	/*******session默认存储user信息key*************/
	SESSION_USER_KEY("daoLifeUser"),
	
	//个人链接前缀
	LINK_USER_PREFIX("http://www.daolife.com/"),
	//话题链接前缀
	LINK_TOPIC_PREFIX("http://www.daolife.com/topicDetail.action?topicId="),
	ERROR_RETPASSWORD_NOPASSWORD("新密码不能为空"),
	ERROR_RETPASSWORD_PASSWORDCONFIRMERROR("两次密码不一致"),
	ERROR_RETPASSWORD_NOIDERROR("用户不存在")
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
