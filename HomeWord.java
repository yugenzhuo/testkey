package zhuoye;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class HomeWord {
	public static void main(String[] args) {
		PrintStream os = null;
		PrintStream od = null;
		try {
			os=new PrintStream(new FileOutputStream("F:\\��������\\��������.txt"));
			od=new PrintStream(new FileOutputStream("F:\\��������\\���������.txt"));
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		int n=10000;
		for(int j=0;j<n;j++){
			StringBuffer sb=new StringBuffer();
			//�����������
			ArrayList<Integer> shu=ShuFu.shu();
			ArrayList<String> fu=ShuFu.fu();
			int kh1=2*(int)(Math.random()*5);
			int kh2=2*(int)(Math.random()*5);
			//�Ƿ������
			if(!((kh1+4==kh2) || (kh1+6==kh2))){
				sb.append(shu.get(0)).append(fu.get(0)).append(shu.get(1)).append(fu.get(1)).append(shu.get(2)).append(fu.get(2)).append(shu.get(3)).append("\n");
			}
			if((kh1+4==kh2) || (kh1+6==kh2)){
				//sb.insert(kh1, "(");
				//sb.insert(kh2, ")");
				if(kh1==0 && kh2==4){
					sb.append("(").append(shu.get(0)).append(fu.get(0)).append(shu.get(1)).append(")").append(fu.get(1)).append(shu.get(2)).append(fu.get(2)).append(shu.get(3)).append("\n");
				}
				if(kh1==0 && kh2==6){
					sb.append("(").append(shu.get(0)).append(fu.get(0)).append(shu.get(1)).append(fu.get(1)).append(shu.get(2)).append(")").append(fu.get(2)).append(shu.get(3)).append("\n");
				}
				if(kh1==2 && kh2==6){
					sb.append(shu.get(0)).append(fu.get(0)).append("(").append(shu.get(1)).append(fu.get(1)).append(shu.get(2)).append(")").append(fu.get(2)).append(shu.get(3)).append("\n");
				}
				if(kh1==2 && kh2==8){
					sb.append(shu.get(0)).append(fu.get(0)).append("(").append(shu.get(1)).append(fu.get(1)).append(shu.get(2)).append(fu.get(2)).append(shu.get(3)).append(")").append("\n");
				}
				if(kh1==4 && kh2==8){
					sb.append(shu.get(0)).append(fu.get(0)).append(shu.get(1)).append(fu.get(1)).append("(").append(shu.get(2)).append(fu.get(2)).append(shu.get(3)).append(")").append("\n");
				}
				int z=ShuFu.khjs(kh1, kh2, shu, fu);
				if(z==-1){
					n++;
				}else{
					os.println(sb.toString());
					od.println(z);
				}
			}else{
				int z=ShuFu.js(shu, fu);
				if(z==-1){
					n++;
				}else{
					os.println(sb.toString());
					od.println(z);
				}
			}
		}
		os.close();
		od.close();
	}
}
//�������ַ��� ����
class ShuFu {
	public static ArrayList<Integer> shu(){
		ArrayList<Integer> s=new ArrayList<Integer>();
		for(int i=0;i<4;i++){
			int a=(int)(Math.random()*100);
			s.add(a);
		}
		return s;
	}
	public static ArrayList<String> fu(){
		ArrayList<String> sz=new ArrayList<String>();
		for(int i=0;i<3;i++){
			int a=(int)(Math.random()*4);
			switch (a) {
			case 0:
				sz.add("+");
				break;
			case 1:
				sz.add("-");
				break;
			case 2:
				sz.add("*");
				break;
			case 3:
				sz.add("/");
				break;
			}
		}
		return sz;
	}
	//���㷽��
	public static int js(ArrayList<Integer> shu,ArrayList<String> fu){
		while(fu.size()>1){
		Integer f=0;
		int a=shu.get(0);
		int b=shu.get(1);
		int c=shu.get(2);
		String f1=fu.get(0);
		String f2=fu.get(1);
			if(f1=="*" || f1=="/"){
				if(f1=="*"){			//�˷�
					f=a*b;
				}else{					//����
					if(b==0 || a%b!=0){
						return -1;
					}else{
						f= a/b;
					}
				}
				shu.remove(0);
				shu.set(0, f);
				fu.remove(0);
			}else if((f1=="+" || f1=="-") && (f2=="+" || f2=="-")){
				if(f1=="+"){		//�ӷ�
					f= a+b;
				}else{				//����
					if(a-b>0){
						f= a-b;
					}else{
						return -1;
					}
				}
				shu.remove(0);
				shu.set(0, f);
				fu.remove(0);
			}else{			//�˷�
				if(f2=="*"){
					f= b*c;
				}else{		//����
					if(c==0 || b%c!=0){
						return -1;
					}else{
						f= b/c;
					}
				}
				fu.remove(1);
				shu.remove(1);
				shu.set(1, f);
			}
		}
		if(fu.size()==1){
			String s=fu.get(0);
			if(s=="+"){
				return shu.get(0)+shu.get(1);
			}
			if(s=="-"){
				if(shu.get(0)-shu.get(1)>0){
					return shu.get(0)-shu.get(1);
				}else{
					return -1;
				}
			}
			if(s=="*"){
				return shu.get(0)*shu.get(1);
			}
			if(s=="/"){
				if(shu.get(1)==0 || shu.get(0)%shu.get(1)!=0){
					return -1;
				}else{
					return shu.get(0)/shu.get(1);
				}
			}
		}
		return 0;
	}
	//�����ŵļ���
	public static int khjs(int kh1,int kh2,ArrayList<Integer> shu,ArrayList<String> fu){
		ArrayList<Integer> khshu=new ArrayList<>();
		ArrayList<String> khfu=new ArrayList<>();
		//ȡ���������
		for(int i=kh1/2;i<=kh2/2-1;i++){
			khshu.add(shu.get(i));
		}
		for(int i=kh1/2;i<=kh2/2-1;i++){
			shu.remove(kh1/2);
		}
		//ȡ������ķ���
		for(int i=kh1/2;i<=kh2/2-2;i++){
			khfu.add(fu.get(i));
		}
		for(int i=kh1/2;i<=kh2/2-2;i++){
			fu.remove(kh1/2);
		}
		if(js(khshu, khfu)==-1){
			return -1;
		}else{
			shu.add(kh1/2, js(khshu, khfu));
			if(js(shu, fu)==-1){
				return -1;
			}else{
				return js(shu, fu);
			}
		}
	}
}










