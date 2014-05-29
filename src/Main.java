import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class Main {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new myJFrame();
		frame.setTitle("图片助手-兄弟小组");
		frame.setBounds(450, 150, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("图片来源:");
		label.setBounds(10, 13, 66, 18);
		frame.getContentPane().add(label);
		
		
		
		
		
		textField = new JTextField();
		textField.setBounds(76, 7, 282, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("浏览");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//只能选择目录
				String path=null;
				File f=null;
				int flag = 0;
				try{     
				            flag=chooser.showOpenDialog(null);     
				        }    
				        catch(HeadlessException head){     
				             System.out.println("Open File Dialog ERROR!");    
				        }        
				        if(flag==JFileChooser.APPROVE_OPTION){
				             //获得该文件    
				            f=chooser.getSelectedFile();    
				            path=f.getPath();
				            textField.setText(path);
				         }    
			}
		});
		btnNewButton.setBounds(369, 9, 63, 40);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("PIC");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String path = textField.getText();
				ico_BatPicHandler.FormatPic(path, "480x800", 480, 800);
				ico_BatPicHandler.FormatPic(path, "800x1280", 800, 1280);
			}
		});
		btnNewButton_1.setBounds(73, 124, 150, 100);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("ICO");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String path = textField.getText();
//				ico_BatPicHandler.FormatIcoPic(path, "ico", 512, 512);
//				ico_BatPicHandler.FormatIcoPic(path, "ico", 144, 144);
//				ico_BatPicHandler.FormatIcoPic(path, "ico", 72, 72);
				try {
					ico_BatPicHandler.ImgConvert(512, 512, path);
					ico_BatPicHandler.ImgConvert(144, 144, path);
					ico_BatPicHandler.ImgConvert(72, 72, path);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(251, 124, 150, 100);
		frame.getContentPane().add(btnNewButton_2);
	}
	
	public class myJFrame extends JFrame implements DropTargetListener{
		 private static final long serialVersionUID = 1L;
		  public myJFrame() {
		   new java.awt.dnd.DropTarget(this, DnDConstants.ACTION_NONE, this);
		}
		@Override
		public void dragEnter(DropTargetDragEvent dtde) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void dragExit(DropTargetEvent dte) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void dragOver(DropTargetDragEvent dtde) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void drop(DropTargetDropEvent dtde) {
			try {

		        if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
		            dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
		            System.out.println("file cp");
		            List<?> list = (List<?>) (dtde.getTransferable()
		                    .getTransferData(DataFlavor.javaFileListFlavor));
		            Iterator<?> iterator = list.iterator();
		            while (iterator.hasNext()) {
		                File f = (File) iterator.next();
		                textField.setText(f.getAbsolutePath());
		            }
		            dtde.dropComplete(true);
		            //this.updateUI();
		        }else {
		            dtde.rejectDrop();
		        }
		    } catch (IOException ioe) {
		        ioe.printStackTrace();
		    } catch (UnsupportedFlavorException ufe) {
		        ufe.printStackTrace();
		    }
			
		}
		@Override
		public void dropActionChanged(DropTargetDragEvent dtde) {
			// TODO Auto-generated method stub
			
		}
		}
}
