
/*���� class�ȿ� �� ���� ������ �� ���� �����ڸ� �ʵ�� ����� �Է��� �� �ִ� �޼ҵ�� ������ ����� ��ȯ�ϴ� �޼ҵ带  �������.
 * main�Լ� ������ data�� �ϳ��� �ݺ����� ���� �޾� ���� �ι�° �ݺ����� �̿��� �ι��� data���� �ް� ���� �Ŀ� �� ���� ù��° data��
 * ������ �� �ι�° data���� �޴� �˰����� �������.
 * '='�� �޾��� �� �� �� ����ϰ� �ϰ� �;����� ����� ���� ���� �̿��Ͽ� ������ �Ѿ�� �������.  
 */



import javax.swing.JOptionPane;

class Cal{
	private	int data1=0,data2=0;
    private String operator="";
	
    void setData1(int data) {
    	data1=data;
    }
    void setData2(int data) {
    	data2=data;
    }
    void setOperator(String operator) {
    	this.operator=operator;
    }
	String getResult() {
		if(operator.equals("+"))
			return Integer.toString(data1+data2);
		else if(operator.equals("-"))
			return Integer.toString(data1-data2);
		else if(operator.equals("*"))
			return Integer.toString(data1*data2);
		else if(operator.equals("/"))
			return Integer.toString(data1/data2);
		else if(operator.equals("%"))
			return Integer.toString(data1%data2);
		else if(operator.equals("="))
			return Integer.toString(data2);           //=�� �������� ����� ���� ������ �ڿ������� �Ѿ�� ���� �̷��� ���� �߰�. ���� ��� 3=5�� ������ ���� ������ =�� ������ ����� �����ش�/ 
		else
			return "�߸��� �������Դϴ�.";
	}
}

public class CalculatorSource {
	public static void main(String[] args) {
		Cal c1= new Cal();
		String operator="";           //������ ���� ���ڿ�, ���� �ʿ������ �������� ���� �߰�
		String str=JOptionPane.showInputDialog("0");
		String str1="";              
		out:while(true){
			if(str.equals("exit"))
				System.exit(0);        
			else {
				str1=JOptionPane.showInputDialog(str);
				if(str1.equals("exit"))
					System.exit(0);
				else if((str1.equals("+")||(str1.equals("-"))||(str1.equals("*"))||(str1.equals("/"))||(str1.equals("%")))) {
					operator = str1;
					break out;
				}
				else
					str+=str1;
			}
		}
		c1.setData1(Integer.parseInt(str)); //ù��° data�� Ŭ������ ����
		c1.setOperator(operator);    //ù��° �����ڸ� Ŭ������ ����
		String str2="";           //�ι��� ���� ������ ���ڿ�
		String str3="";           //�ι�° ���� �ޱ� ���� ���ڿ�
		while(true) {
			str2=JOptionPane.showInputDialog(str);
			if(str2.equals("exit"))
				System.exit(0);
			else {
				while(true) {
				str3=JOptionPane.showInputDialog(str2);
			    if((str3.equals("+")||(str3.equals("-"))||(str3.equals("*"))||(str3.equals("/"))||(str3.equals("%")))) {
			    	c1.setData2(Integer.parseInt(str2));
			    	str=c1.getResult();
			    	c1.setData1(Integer.parseInt(str));
			    	operator=str3;
			    	break;                         //ù��°�� �ƴ� ������ �����ڰ� ��Ÿ���� ��� ����� â�� �߰� ��ġ
			    }
			    else if(str3.equals("=")) {
			    	c1.setData2(Integer.parseInt(str2));
			    	str=c1.getResult();
			    	c1.setData1(0);                   //data1�� 0���� ����� �ʱ�ȭ��Ų��.
 			    	operator=str3;               
			    	break;
			    }
			    else {
			    	str2+=str3;    
			    }
			}
				c1.setOperator(operator);    //�Է¹޾Ҵ� �����ڸ� Ŭ������ �����ڿ� ����
				}
		}
	}

}
