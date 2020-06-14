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
		// TODO �Զ����ɵķ������
		FileSystem.setDefaultUri(conf, HADOOP_URL);//���ļ�ϵͳ��hdfs��url��ַ��
		fs = FileSystem.get(conf);
		//boolean flag=fs.mkdirs(new Path("/test"));//��hdfs�ĸ�Ŀ¼�´�����Ŀ¼test
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
/*		FileStatus[] lists=fs.listStatus(new Path("/test"));//��ȡָ��·���������ļ���״̬��Ϣ�����ұ��浽lists����
		for(FileStatus k:lists){
			System.out.println(k.getPath()+";"+k.getPermission().toString()+";"+k.getOwner()+";"+k.getLen());
		}*/
/*		boolean flag=fs.rename(new Path("/test"),new Path("/data"));//��hdfs��Ŀ¼�µ�test��Ŀ¼����Ϊdata��Ŀ¼
		if(flag){
			System.out.println("success");
		}else{
			System.out.println("fail");
		}*/
		/*FSDataInputStream in = fs.open(new Path("/data/test.txt"));//���ļ�test.txt,���������ݷ���HDFS����������
		FSDataOutputStream out = fs.create(new Path("/test.txt"), true);//�Ը��ǵķ�ʽ�����ļ�test.txt�����������ݷ���HDFS�����
		IOUtils.copyBytes(in, out, 4096, true);//���������е��������ֽ���ķ�ʽ���Ƶ����������������Ϊ4096Byte�����Ҹ�����Ϻ��Զ��ر��������������
*/	/*	boolean flag=fs.delete(new Path("/data"),true);//ʹ�õݹ�ķ�ʽɾ��ָ��·����Ŀ¼
		if(flag){
				System.out.println("success");
	    }else{
				System.out.println("fail");
		}*/
		fs.copyToLocalFile(false, new Path("/test.txt"), new Path("d:\\result\\"), true);
		//ʹ��ԭ�������ļ�ϵͳ��HDFS��ָ�����ļ����ص�����ָ��Ŀ¼
	}

}
