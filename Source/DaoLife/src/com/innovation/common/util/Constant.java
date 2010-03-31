package com.innovation.common.util;
/**
 * �����з�װ����ϵͳ���õ���һЩ�̶�����ĳ�����Ϣ�� ���еĳ�����Ϣ(����ͨ�õĳ���)����������Ϊ ����-�ֶ���-����
 * ������������һ���������ֶ����ĵ�һ��������ͬ��ʡ��һ����
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
	ERROR_RETPASSWORD_NOPASSWORD("�����벻��Ϊ��"),
	ERROR_RETPASSWORD_PASSWORDCONFIRMERROR("�������벻һ��"),
	ERROR_RETPASSWORD_NOIDERROR("�û�������")
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
