package Dao;

import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class FileRead.
 *
 * @author nieming
 * @version  v1.0
 * @date 2020-7-4
 */
public class FileRead {
	
	/** The file path. */
	private String filePath;
	
	/** The reader. */
	private BufferedReader reader = null;
    
    /** The temp string. */
    String tempString = null;
	
	/**
	 * Instantiates a new file read.
	 *
	 * @param filename the filename
	 */
	public FileRead(String filename) {
		filePath=filename;
		File file=new File(filePath);
		 try {
			while ((tempString = reader.readLine()) != null) {
			        System.out.println("tempString");
			
			    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * The main method.
 *
 * @param args the arguments
 */
public static void main(String[] args) {
	//��ô������ô��û�й�
	//�����á���������
	//Ҫ���������ɣ����·����web���е㲻һ����ֱ�ӵ���Ŀ��Ŀ¼�ˣ�����src�ɣ���src��ʼ 
	//String rootPath=System.getProperty("user.dir");
	String filePath="src\\hello.txt";
	//���������·�������� ����������
    File file=new File(filePath);
    System.out.println(file.getName());
    BufferedReader reader = null;
    String tempString = null;
    int line =1;
    try {
        // System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
    	//����������õ��е��ҡ���������ֽ��������������ַ���   ����·������ʹ��
 
    	FileReader fr=new FileReader(file);
        //reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
    	reader = new BufferedReader(fr);
        while ((tempString = reader.readLine()) != null) {
            System.out.println("Line"+ line + ":" +tempString);
            line ++ ;
        }
        reader.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }finally{
        if(reader != null){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
}
