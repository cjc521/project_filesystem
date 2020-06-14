package com.testHDFS;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class Test {
	private static Configuration conf = new Configuration();
	private static final String HADOOP_URL="hdfs://etc:9000";
	private static FileSystem fs;
	public static void main(String[] args)throws Exception {
		// TODO 自动生成的方法存根
		FileSystem.setDefaultUri(conf, HADOOP_URL);//将文件系统与hdfs的url地址绑定
		fs = FileSystem.get(conf);
		//boolean flag=fs.mkdirs(new Path("/test"));//在hdfs的根目录下创建空目录test
		/*if(flag){
			System.out.println("success");
		}else{
			System.out.println("fail");
		}*/
		//fs.copyFromLocalFile(new Path("d:\\test.txt"),new Path("/test"));
	/*	boolean flag=fs.createNewFile(new Path("/test/test11.txt"));
		if(flag){
			System.out.println("success");
		}else{
			System.out.println("fail");
		}*/
/*		FileStatus[] lists=fs.listStatus(new Path("/test"));//获取指定路径下所有文件的状态信息，并且保存到lists数组
		for(FileStatus k:lists){
			System.out.println(k.getPath()+";"+k.getPermission().toString()+";"+k.getOwner()+";"+k.getLen());
		}*/
/*		boolean flag=fs.rename(new Path("/test"),new Path("/data"));//将hdfs根目录下的test子目录更名为data子目录
		if(flag){
			System.out.println("success");
		}else{
			System.out.println("fail");
		}*/
		/*FSDataInputStream in = fs.open(new Path("/data/test.txt"));//打开文件test.txt,将它的内容放入HDFS数据输入流
		FSDataOutputStream out = fs.create(new Path("/test.txt"), true);//以覆盖的方式创建文件test.txt，将它的内容放入HDFS输出流
		IOUtils.copyBytes(in, out, 4096, true);//将输入流中的内容以字节码的方式复制到输出流，缓存设置为4096Byte，并且复制完毕后，自动关闭输入流、输出流
*/	/*	boolean flag=fs.delete(new Path("/data"),true);//使用递归的方式删除指定路径的目录
		if(flag){
				System.out.println("success");
	    }else{
				System.out.println("fail");
		}*/
		fs.copyToLocalFile(false, new Path("/test.txt"), new Path("d:\\result\\"), true);
		//使用原生本地文件系统将HDFS上指定的文件下载到本地指定目录
	}

}
