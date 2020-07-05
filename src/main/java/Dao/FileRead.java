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
	//怎么开了那么多没有关
	//还不好。我再试试
	//要不就这样吧，这个路径和web的有点不一样，直接到项目根目录了，就用src吧，从src开始 
	//String rootPath=System.getProperty("user.dir");
	String filePath="src\\hello.txt";
	//不是这里的路径的问题 代码问题吗？
    File file=new File(filePath);
    System.out.println(file.getName());
    BufferedReader reader = null;
    String tempString = null;
    int line =1;
    try {
        // System.out.println("以行为单位读取文件内容，一次读一整行：");
    	//你这里的流用得有点乱。里面的是字节流，外层包的是字符流   绝对路径可以使用
 
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
