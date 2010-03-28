package com.innovation.common.util;
/**
 * �����з�װ����ϵͳ���õ���һЩ�̶�����ĳ�����Ϣ�� ���еĳ�����Ϣ(����ͨ�õĳ���)����������Ϊ ����-�ֶ���-����
 * ������������һ���������ֶ����ĵ�һ��������ͬ��ʡ��һ����
 * 
 * @author winston
 */
public enum Constant
{
	USER_ISCLOSED_YES(1),
	USER_ISCLOSED_NO(0)
	;
	
	
	private String strValue = "";
	private int intValue = 0;

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
