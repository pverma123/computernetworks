package mansi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class Mansiworld {

	private JFrame frmLinkStateRouting;
	JTable table;
	static int noOfNodes;
	int dist[][];
	int flag[][];
	int cost[][];
	int last[][],costMatrix[][];
	int min,v;
//	Mansiworld m = new Mansiworld();
	private JTextField textField;
	private JTextField textField_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mansiworld window = new Mansiworld();
					window.frmLinkStateRouting.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mansiworld() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLinkStateRouting = new JFrame();
		frmLinkStateRouting.setTitle("Link State Routing Protocol");
		frmLinkStateRouting.setBounds(100, 100, 746, 468);
		frmLinkStateRouting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton1 = new JButton("Show Network Topology");
		btnNewButton1.setBounds(10, 96, 224, 23);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JTextArea ta = new JTextArea(20, 60);
					ta.read(new FileReader("C:/file/abc.txt"), null);
					ta.setEditable(false);
					JOptionPane.showMessageDialog(btnNewButton1, new JScrollPane(ta));
					}
					catch (IOException ioe) {
						JOptionPane.showMessageDialog(null,"File not found in the Path. Check !");
					}
			}
		});
		frmLinkStateRouting.getContentPane().setLayout(null);
		frmLinkStateRouting.getContentPane().add(btnNewButton1);
		
		
		JButton btnNewButton2 = new JButton("Show Connection Table");
		btnNewButton2.setBounds(10, 151, 224, 23);
		btnNewButton2.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				noOfNodes=0;
				JTextArea textArea = new JTextArea(20,60);
				textArea.setBounds(82, 150, 191, 67);

				FileReader fr=null;
				ArrayList<String> str=null;
				String readFile = null;
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				try {
		

					//JOptionPane.showMessageDialog(null,"File not found in the Path. Check !");
					String fileName="C:/file/abc.txt";
					fr=new FileReader(fileName);
					br = new BufferedReader(fr); 
					
					
					str=new ArrayList<String>();
					while((readFile = br.readLine()) != null ) {
						str.add(readFile);

						noOfNodes++;	
					}
					cost=new int[noOfNodes][noOfNodes];
					dist =new int[noOfNodes][noOfNodes];
					costMatrix=new int[noOfNodes][noOfNodes];
					flag =new int[noOfNodes][noOfNodes];
					last =new int[noOfNodes][noOfNodes];

					// insert elements from file to an array
					for(int i=0;i<str.size();i++){
						String temp[]=str.get(i).split(" ");
						for(int col=0;col<temp.length;col++){
							cost[i][col]=new Integer(temp[col].trim());
								
						}
						}
			//		System.out.println("Total number of nodes:"+noOfNodes);
					
					for(int i=0;i<noOfNodes;i++){
		                for(int j=0;j<noOfNodes;j++){
		                    dist[i][j] = costMatrix[i][j]=cost[i][j];
		                    if(dist[i][j]<0)
		                    	dist[i][j]=costMatrix[i][j]=1000;
		                }
		            }
		                                
		            for(int a=0;a<noOfNodes;a++){
		                    for(int v=0;v<noOfNodes;v++){
		                        flag[a][v]=0;
		                        last[a][v]=a;
		                    }
		            }
		            dijkstra();
				} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null,"File not found in the Path. Check !");
					
				} catch (IOException e2) {
					JOptionPane.showMessageDialog(null,"Problem with the file. Check !");
				}finally{
					try {
						fr.close();
					} catch (IOException e3) {
					e3.printStackTrace();
					}
				}
			
				for(int a=0;a<noOfNodes;a++)
				textArea.append("\t"+"R"+a);
				textArea.append("\t"+"Next Node");
					for(int l=0;l<noOfNodes;l++)
					{
					textArea.append("\n-----+-------------------------------------------------------------" +
                         		"----------------------------------------------------------------------------------" +
                         		"---------------" +
                         		"-----------------------------------------");
					if(l>=10)
						textArea.append("\nR"+l+"  | \t");
                   else
                	   textArea.append("\n\nR"+l+"   | \t");
						int nextHop1=9999;
		                int nextHopValue1=9999;
		                for(int n=0;n<noOfNodes;n++){
		                    if(dist[l][n]==1000)
		                    {  // Systeout.print("\nR-\t");
		               		textArea.append("\n\t");		                        
		                    } 
		                    else
		                    {  //     Systeout.print(dist[l][n]+"\t");
		               		textArea.append(Integer.toString(dist[l][n])+"\t");		                        
		                    }
		                    if(dist[l][n]<nextHopValue1 && dist[l][n]>0){
		                   		nextHopValue1=dist[l][n];
		                   		nextHop1=n;
		                  		//textArea.append(Integer.toString(dist[l][n]));		                        
		                    }
		          		}

		                textArea.append("R"+nextHop1);
		        			
					}
					
			   	JOptionPane.showMessageDialog(btnNewButton1, new JScrollPane(textArea));
				
			} 
			
		});

		frmLinkStateRouting.getContentPane().setLayout(null);

		frmLinkStateRouting.getContentPane().add(btnNewButton2);
		
		frmLinkStateRouting.setVisible(true);
		
		JButton btnNewButton3 = new JButton("Get Path");
		btnNewButton3.setBounds(10, 283, 163, 23);
		btnNewButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//m=new Mansiworld();
			//try
				
					JTextArea textArea1 = new JTextArea(20,60);
				textArea1.setBounds(82, 150, 191, 67);

				
				int w;
				try{
				while(true){
                  // Systeout.println("\nPlease input the source and destination router number : ");
                 try{  int src=Integer.parseInt(textField.getText());
                   int dest=Integer.parseInt(textField_1.getText());
				        
                 //  JOptionPane.showMessageDialog(null,"File not found in the Path. Check !");
      //             JOptionPane.showMessageDialog(null,dist);
                   
                   if(src<noOfNodes && dest<noOfNodes){
                       //shortest distance is picked from the matrix and displayed
                       //route taken from source to destination with cost matrix is displayed
                       if(dist[src][dest]!=1000){
                    	   textArea1.append("\nThe shortest path between R"+src+" and R"+dest+"  is :  ");
                    	   textArea1.append(" R"+dest);
                                w = dest;
                                while(w!=src){
                                	textArea1.append(" <-- R"+last[src][w]);
                                        w=last[src][w];
                                }
                       
                                textArea1.append("\n Total cost is  : ");
                                textArea1.append(Integer.toString(dist[src][dest]));
                                JOptionPane.showMessageDialog(btnNewButton1, new JScrollPane(textArea1));
                       }else
                    	   textArea1.append("There is no path between the source and destination..!!");
                       break;
                   }else{
                       if(src>noOfNodes && dest>noOfNodes){
                    	   textArea1.append("\n Enter valid source and destination router nos. ");
                       }else{
                    	                     	   
                           if(src>noOfNodes)
                        	   {JOptionPane.showMessageDialog(null,"Enter a valid Source");
                        	   break;}
                        	   else
                         { 	JOptionPane.showMessageDialog(null,"Enter a valid destination");
                       break;
                            }
                       }     
                   }//else for error input ends
                }//end of while in case 3
                 catch (OutOfMemoryError oe) {
 					JOptionPane.showMessageDialog(null,"Please load the toplogy file first.");
 					break;
                 }
				}}catch (NumberFormatException ex){ // handle your exception
					JOptionPane.showMessageDialog(null,"Enter a valid Number");
				}
				
				}
				
				/*catch (OutOfMemoryError oe) {
					JOptionPane.showMessageDialog(null,"Please load the toplogy file first.");
					}*/
		});
		frmLinkStateRouting.getContentPane().add(btnNewButton3);
		
		JButton btnNewButton4 = new JButton("Modify Topology");
		btnNewButton4.setBounds(10, 317, 163, 23);
		frmLinkStateRouting.getContentPane().add(btnNewButton4);
		
		JButton btnNewButton5 = new JButton("Exit");
		btnNewButton5.setBounds(308, 361, 89, 23);
		btnNewButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frmLinkStateRouting.getContentPane().add(btnNewButton5);
		
		textField = new JTextField();
		textField.setBounds(126, 243, 108, 20);
		frmLinkStateRouting.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(465, 243, 124, 20);
		frmLinkStateRouting.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSourceRouter = new JLabel("Source Router");
		lblSourceRouter.setBounds(10, 246, 89, 14);
		frmLinkStateRouting.getContentPane().add(lblSourceRouter);
		
		JLabel lblDestinationRouter = new JLabel("Destination Router");
		lblDestinationRouter.setBounds(308, 246, 124, 14);
		frmLinkStateRouting.getContentPane().add(lblDestinationRouter);
		
		JLabel lblNewLabel = new JLabel("Shortest Path to Destination Router :");
		lblNewLabel.setBounds(10, 208, 266, 14);
		frmLinkStateRouting.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 34, 299, 38);
		frmLinkStateRouting.getContentPane().add(lblNewLabel_1);
		
		//JTextArea textArea = new JTextArea();
		//textArea.setBounds(82, 150, 191, 67);
		//frame.getContentPane().add(textArea);
		
		
		
		
	}
	public void dijkstra(){
		//flooding the router and finding the smallest path using Djikstra's Algorithm 
        for(int a=0;a<noOfNodes;a++){
        flag[a][a]=1;
	        for(int i=0;i<noOfNodes;i++){
	                min=1000;
	                for(int w=0;w<noOfNodes;w++){
	                        if(flag[a][w] == 0)
	                        if(dist[a][w]<min){
	                                v=w;
	                                min=dist[a][w];
	                        }
	                }
	                flag[a][v]=1;
	                for(int w=0;w<noOfNodes;w++){
	                        if(flag[a][w]==0)
	                        	if(min+costMatrix[v][w]<dist[a][w]){
	                                dist[a][w]=min+costMatrix[v][w];
	                                last[a][w]=v;
	                        	}
	                }
	        }
        }
	}
}
