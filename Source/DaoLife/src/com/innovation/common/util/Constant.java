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
