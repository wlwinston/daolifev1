package com.innovation.common.util;
/**
 * �����з�װ����ϵͳ���õ���һЩ�̶�����ĳ���Ϣ�� ���еĳ���Ϣ(����ͨ�õĳ���)���������Ϊ ����-�ֶ���-����
 * ����������һ������ֶ���ĵ�һ��������ͬ��ʡ��һ��
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
	/*********ÿҳ���********/
	PAGESIZE_FRIENDDAO(20),
	PAGESIZE_MYDAO(20),
	
	/*********��ɫ************/
	//Ĭ�Ͻ�ɫ
	WEBSITE_ROLES_DEFAULT("daolife_user"),
	/*********��Ϣ����*********/
	//@��Ϣ
	MESSAGE_TYPE_AT("0"),
	//վ����
	MESSAGE_TYPE_MAIL("1"),
	//��ע
	MESSAGE_TYPE_FOLLOW("2"),
	/*********��Ϣ����*********/
	//@��Ϣ����
	MESSAGE_MESSAGEBODY_ATMESSAGE("�㱻��@��"),
	
	/*********��ע��Ϣ����*********/
	//@��Ϣ����
	MESSAGE_MESSAGEBODY_ATTENTION("�㱻�˹�ע��"),
	/*********UP������Ϣ*********/
	//û�е�¼
	UPDAO_ERRORMESSAGE_NOUSER("��û�е�¼�����ȵ�¼"),
	//��ζ�ͬһ߶
	UPDAO_ERRORMESSAGE_ONLYONE("���Ѿ�����һ�θ�߶���벻Ҫ�ظ���"),
	//�����쳣
	UPDAO_ERRORMESSAGE_EXCEPTION("����ʧ�ܣ���jϵ��վ����Ա"),
	/*******sessionĬ�ϴ洢user��Ϣkey*************/
	SESSION_USER_KEY("daoLifeUser"),
	
	//����t��ǰ׺
	LINK_USER_PREFIX("http://www.daolife.com/"),
	//����t��ǰ׺
	LINK_TOPIC_PREFIX("http://www.daolife.com/topicDetail.action?topicId="),
	ERROR_RETPASSWORD_NOPASSWORD("�����벻��Ϊ��"),
	ERROR_RETPASSWORD_PASSWORDCONFIRMERROR("}�����벻һ��"),
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
