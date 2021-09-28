import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

class Value{
	private ArrayList<String> list = new ArrayList<String>(); 
	private int fact;
	
	public Value(int value){
		this.fact=value;
		list.add("1");
	}

	public String cal() {
		for(int i=1;i<=fact;i++) {
		    int max=0;
			for(int j=0;j<list.size();j++) {
				int v1=Integer.parseInt(list.get(j));
				v1= v1 * i;
				v1+=max;
				if(v1>=10000){
					max=v1/10000;
					v1%=10000;
				}
				else max=0;
				if(j!=list.size()-1) {list.set(j, String.format("%04d", v1));}
				else list.set(j, Integer.toString(v1));
				if(max!=0&&j==list.size()-1) {
					while(max>=10000) {
						list.add(String.format("%04d", max%10000));
						max/=10000;
					}
					String v2 = Integer.toString(max);
					list.add(v2);
					break;
				}
			}
		}
		String result="";
		for(int i=list.size()-1;i>=0;i--) {
				result+=list.get(i);
		}
	    return result;
	}
}


public class BigMultiple {
	public static void main(String[] args) {
		Charset cs = Charset.defaultCharset();
		Path p = new File("C:\\homework").toPath();
		Path p1=new File(p+"\\50000!.txt").toPath();
		
		System.out.print("정수를 입력하세요 : ");
		Scanner in = new Scanner(System.in);
		Value v = new Value(in.nextInt());
		String result = v.cal();
		
		try {
			if(Files.notExists(p)) {
			Files.createDirectory(p);
			}
			if(Files.notExists(p1)) {
				Files.createFile(p1);
			}
			if(Files.isWritable(p1)) {
				byte[] data=result.getBytes();
				Files.write(p1, data);
				}
			}
		 catch (IOException e) {
				e.printStackTrace();
			}
	}
}
