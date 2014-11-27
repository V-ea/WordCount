package ea.wordcount;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Windows extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1559479507554739986L;
	private JLabel statistic =new JLabel();
	private JLabel sort =new JLabel();
	private JLabel feedback =new JLabel();
	private JButton fromButton =new JButton();
	private JButton toButton =new JButton();
	private JButton startButton =new  JButton();
	
	private String fromString;
	private String toString;
	public void init()
	{
		statistic.setText("统计:");
		sort.setText("排序：");
		fromButton.setText("打开文件");
		toButton.setText("结果存放");
		startButton.setText("开始");
		feedback.setText("有什么错误或者问题请到 http://ealife.tk:5001/wordpress/ 或者邮箱反馈");
		this.setLocation(200, 200);
		this.setSize(500, 500);
		this.setVisible(true);
		this.setTitle("Eapchen 英语词频统计软件 免费版 [邮箱:390270720@qq.com]");
		this.setLayout(new GridLayout(6,1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(statistic);
		this.add(fromButton);
		this.add(sort);
		this.add(toButton);
		this.add(startButton);
		this.add(feedback);
		//behavior
		fromButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc=new JFileChooser();  
		        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );  
		        jfc.showDialog(new JLabel(), "选择");  
		        File file=jfc.getSelectedFile();
		        if(file!=null&&file.isFile()){  
		           statistic.setText("文件:"+file.getAbsolutePath());
		           fromString = file.getAbsolutePath();
		        }  
		        
			}
		});
		toButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc=new JFileChooser();  
		        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
		        jfc.showDialog(new JLabel(), "选择");  
		        File file=jfc.getSelectedFile();
		        if(file!=null&&file.isDirectory()){  
		            sort.setText("存放在:"+file.getPath());
		            toString = file.getAbsolutePath();
		        }  
		        
			}
		});
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					TxTWordCount.Start(fromString, toString+"\\cipin.txt");
					JOptionPane.showMessageDialog( null,"统计完成", "感谢您的使用.", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String [] args) {
		new Windows().init();
	}
}
