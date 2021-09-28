
/*계산기 class안에 두 개의 정수와 한 개의 연산자를 필드로 만들고 입력할 수 있는 메소드와 연산한 결과를 반환하는 메소드를  만들었다.
 * main함수 에서는 data값 하나를 반복문을 통해 받아 놓고 두번째 반복문을 이용해 두번쨰 data값을 받고 연산 후에 그 값을 첫번째 data에
 * 덮어씌우고 또 두번째 data값을 받는 알고리즘을 만들었다.
 * '='을 받았을 때 좀 더 깔끔하게 하고 싶었으나 방법을 몰라 식을 이용하여 강제로 넘어가게 만들었다.  
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
			return Integer.toString(data2);           //=은 연산자의 기능이 없기 때문에 자연스럽게 넘어가기 위해 이러한 식을 추가. 예를 들어 3=5은 오류가 나기 떄문에 =은 연산의 기능을 없애준다/ 
		else
			return "잘못된 연산자입니다.";
	}
}

public class CalculatorSource {
	public static void main(String[] args) {
		Cal c1= new Cal();
		String operator="";           //연산자 받을 문자열, 굳이 필요없지만 가독성을 위해 추가
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
		c1.setData1(Integer.parseInt(str)); //첫번째 data를 클래스에 저장
		c1.setOperator(operator);    //첫번째 연산자를 클래스에 저장
		String str2="";           //두번쨰 수를 저장할 문자열
		String str3="";           //두번째 수를 받기 위한 문자열
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
			    	break;                         //첫번째가 아닌 이후의 연산자가 나타나면 계산 결과가 창에 뜨게 장치
			    }
			    else if(str3.equals("=")) {
			    	c1.setData2(Integer.parseInt(str2));
			    	str=c1.getResult();
			    	c1.setData1(0);                   //data1을 0으로 만들어 초기화시킨다.
 			    	operator=str3;               
			    	break;
			    }
			    else {
			    	str2+=str3;    
			    }
			}
				c1.setOperator(operator);    //입력받았던 연산자를 클래스의 연산자에 저장
				}
		}
	}

}
