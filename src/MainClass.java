import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;


import java.io.UnsupportedEncodingException;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
//-----------------
import javax.swing.UIManager;

import org.graphstream.stream.ProxyPipe; 
import org.graphstream.stream.thread.ThreadProxyPipe; 
import org.graphstream.ui.spriteManager.Sprite; 
import org.graphstream.ui.spriteManager.SpriteManager;
//------------------









import javafx.util.Pair;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.jfree.ui.RefineryUtilities;

//****************//
import org.graphstream.ui.swingViewer.*;

import scala.util.Random;

import com.sun.glass.ui.View;

/*
class MyThread implements Runnable{
	boolean needToStop = false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (;;){
			//...
			if (needToStop){
				//��������� ��� �������� (join).
			}
		}
	}
	public void setNeedToStop(boolean value){
		this.needToStop = value;
	}
	public boolean getNeedToStop(){return needToStop;}
}
*/

public class MainClass{
	
	static GraphInfo graphInfo;
	static GraphInfo superGraphInfo;
	
	public static void main(String[] args) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		try{	
			/*MyThread myThread = new MyThread();
			myThread.run();
			
			myThread.setNeedToStop(!myThread.needToStop); //������� � handler-� ��� ������� �������*/
			
			//1 : 
			GraphConstructor constructor =  new GraphConstructor();
			
			//2 :
			Viewer v = constructor.showGraph();  
			
			String path="C:\\graphs\\";
			for(int i=2; i<=26; i++){				//� thread RUN
				String endpath=path+i+".txt";	
				constructor.readGraphInfo(endpath);
				constructor.fillGraph(v);
				//constructor.showGraph();
				Thread.sleep(500);	

				/*
				Viewer viewer = new Viewer(null, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD); //��� ��� �� �����
				ViewPanel view = viewer.addDefaultView(false);
				
				JPanel mPanel = new JPanel();
				mPanel.add(view);
				JFrame myFrame = new JFrame();
				myFrame.add(mPanel);
				myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				myFrame.show();
				myFrame.addKeyListener(new KeyListener(){

					@Override
					public void keyPressed(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyTyped(KeyEvent arg0) {
						// TODO Auto-generated method stub
						if (arg0.getKeyChar() == 'a'){
							myThread.setNeedToStop(true);
						}
					}
					
				});*/
			}
			} 
		catch(Exception exc){
			exc.printStackTrace();
		}
	}

}



class GraphConstructor {
	public static final int sleepMsc = 10;
	Graph graph;
	GraphInfo graphInfo;
	GraphInfo prew_graphInfo = null;
	ArrayList <String> nodesAdded = new ArrayList<String>();
	public GraphConstructor(){
		this.graph = new SingleGraph("GraphName");
		String styleSheet =
		        "node {" +
		        "	fill-mode: dyn-plain;"+
		        "	fill-color: rgb(255,0,0), rgb(0,0,255);"+
		        "	stroke-mode: plain;"+
		        "	text-style: italic;"+
		        "	text-alignment: above;"+
		        "	padding: 160px;"+
		        "}" +
		        "node.marked {" +
		        "   fill-color: red;" +
		        "}"+
		        "node.big {" +
		        "   size:20px;" +
		        "}"+
		        "edge {" +
		        "	fill-mode: dyn-plain;"+
		        "	fill-color: grey, blue;" +
		        "	stroke-width: 5;"+
		        "}";
		graph.addAttribute("ui.stylesheet", styleSheet);

	}
	
	public Viewer showGraph(){ //����� ��� ����
		
		Viewer viewer = graph.display();  //������������� ������ ����� ��� ��������
		  
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		
		return(viewer);
		
		
		//Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		//ViewPanel view = viewer.addDefaultView(false);
		//return(view);
	}

	public void clearGraph(){
		graph.clear();
	}
	public void readGraphInfo(String path){
		prew_graphInfo = graphInfo;
		try {
			graphInfo = new GraphInfo(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("restriction")
	public void fillGraph(Viewer viewer){
		for (Node node : this.graph) {
		    node.addAttribute("ui.label", node.getId());
		    node.setAttribute("layout.weight",0.000003);
		}
		
		for (Node node : graph) {
			double k = node.getAttribute("col");
		    node.setAttribute("col", k);
		}
		
		try{Thread.sleep(sleepMsc);}
		catch(Exception exc){exc.printStackTrace();}
		
		//-----------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------
				
				
				//�������� ������ �����
				if (prew_graphInfo != null){
					for (int i = 0; i < prew_graphInfo.getConnectionsCount(); ++i){
						Connection prew_con=prew_graphInfo.getConnectionAt(i);
						String prew_edgeName=prew_con.node1+"_"+prew_con.node2;
						Boolean hadUniqueEdge = true;
						if (!(graphInfo == null)){
							for (int j = 0; j < graphInfo.getConnectionsCount(); ++j){
								Connection con=graphInfo.getConnectionAt(j);
								String edgeName=con.node1+"_"+con.node2;
								if (edgeName.equals(prew_edgeName)){
									hadUniqueEdge = false;
									break;
								}
							}
						}
						if (hadUniqueEdge){
							try{
							graph.removeEdge(prew_edgeName);
							}
							catch(Exception exc){}
						}
					}
					
				}
				//��������� ������� �����				
				
				
				//�������� ������ �����
						if (prew_graphInfo != null){
							for (int i = 0; i < prew_graphInfo.getNodesCount(); ++i){
								String nodeName = prew_graphInfo.getNodeAt(i).getKey();
								Boolean hadUniqueNode = true;
								Integer nodeSize=0;
								if (!(graphInfo == null)){
									for (int j = 0; j < graphInfo.getNodesCount(); ++j){ //prew ?
										if (graphInfo.getNodeAt(j).getKey().equals(nodeName)){
											nodeSize=graphInfo.getNodeAt(j).getValue();
											hadUniqueNode = false;
											break;
										}
									}
								}
								try{
									if (hadUniqueNode){
										try{Thread.sleep(sleepMsc);}
										catch(Exception exc){exc.printStackTrace();}
										graph.getNode(nodeName).setAttribute("col", (double) 0.0);
										graph.removeNode(nodeName);
									}
									else{
										Integer sz=nodeSize-19;
										String st = "size: "+sz.toString()+"px;";
										//graph.getNode(nodeName).setAttribute("ui.style", st);
										try{Thread.sleep(sleepMsc);}
										catch(Exception exc){exc.printStackTrace();}
									}
								}
								catch(Exception ex){
									ex.printStackTrace();
								}
							}
						}
						//��������� ������� ������ ����
						
						//���������� ������ ������
						int k=0;
						for (int i = 0; i < graphInfo.getNodesCount(); ++i){
							String nodeName = graphInfo.getNodeAt(i).getKey();
							Integer nodeSize = graphInfo.getNodeAt(i).getValue();
							//System.out.println(nodeSize);
							Boolean hasThisNode = false;
							if (!(prew_graphInfo == null)){
								for (int j = 0; j < prew_graphInfo.getNodesCount(); ++j){
									if (prew_graphInfo.getNodeAt(j).getKey().equals(nodeName)){
										double numc = graph.getNode(nodeName).getAttribute("col");
										double d = numc + 0.1;
										graph.getNode(nodeName).setAttribute("col", (double) d);
										graph.getNode(nodeName).setAttribute("ui.color", (double) d);
										hasThisNode = true;
										k=i;
										break;
									}
								}
							}
							if (!hasThisNode){
								try {
									graph.addNode(nodeName);//!!!!!!!!!!!!!!!!!!!!!
									if((nodesAdded!=null)&&(nodesAdded.contains(nodeName)))
										graph.getNode(nodeName).setAttribute("ui.style", "stroke-color: black; stroke-width: 2px;");
									try{Thread.sleep(sleepMsc);}
									catch(Exception exc){exc.printStackTrace();}
									graph.getNode(nodeName).setAttribute("col", (double) 0.0);
									//graph.getNode(nodeName).setAttribute("ui.color", 0);
									Integer sz=nodeSize-19;
									String st = "size: "+sz.toString()+"px;";
									graph.getNode(nodeName).setAttribute("ui.style", st);
									nodesAdded.add(nodeName);
								}
								catch (Exception e) {
									e.printStackTrace();
								}
								for (Node node : graph) {
								    node.addAttribute("ui.label", node.getId());
								}
									
							}
						}
						
						//���������� ������ �������		
						for (int i = 0; i< graphInfo.getConnectionsCount(); ++i){
							Connection con=graphInfo.getConnectionAt(i);
							String edgeName=con.node1+"_"+con.node2;
							Boolean hasThisEdge = false;
							if (!(prew_graphInfo == null)){
								for (int j = 0; j < prew_graphInfo.getConnectionsCount(); ++j){
									Connection prew_con=prew_graphInfo.getConnectionAt(j);
									String prew_edgeName=prew_con.node1+"_"+prew_con.node2;
									if (prew_edgeName.equals(edgeName)){
									//if (prew_graphInfo.getNodeAt(j).getKey().equals(nodeName)){
										hasThisEdge = true;
										break;
									}
								}
							}
							if (!hasThisEdge){
								try{
									if(con.getWeight()>0.9){
										graph.addEdge(con.node1+"_"+con.node2, con.getNode1(),con.getNode2(), true);
										//graph.getEdge(con.node1+"_"+con.node2).addAttribute("ui.label", con.weight);
									}
								}				
								catch(Exception exc){
									
								}
								
							}
						}
						//��������� ��������� �������
				
		//--------------------------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------------
						System.out.println("////////////////////////////////////////////");
		for (Node node : graph) {
			//System.out.println(node.getId());
			//System.out.println(node.getEdgeSet());
		    if(node.getEdgeSet().isEmpty())
		    {
		    	graph.removeNode(node); //���������, ����� ������ �� ������ �� ����� �� � �� ��������
		    	graphInfo.deleteNode(node.getId().toString().toLowerCase());
		    }
		}
		for (Node node : graph) {
			//System.out.println(node.getId());
			//System.out.println(node.getEdgeSet());
		    if(node.getId().equals("�������_��������"))
		    {
		    	graph.removeNode(node); //���������, ����� ������ �� ������ �� ����� �� � �� ��������
		    	graphInfo.deleteNode(node.getId().toString().toLowerCase());
		    }
		}
		
		System.out.println(this.graph.getNodeSet().isEmpty());
		
		ViewerPipe fromSwing = viewer.newViewerPipe(); 
		fromSwing.addSink(graph); 
		
		int pressedNode = 0;
		boolean loop = true;
		while (loop&&(pressedNode<3)) { 
			   try { 
			    Thread.sleep(30); 
			   } catch (InterruptedException e) { 
			    e.printStackTrace(); 
			   } 
			   
			   fromSwing.pump(); 
			   
			   if (graph.hasAttribute("ui.viewClosed")) {
				   System.out.println("PYSHPYSH");   
			    loop = false; 
			   } else {
				   for (Node node : graph) {
					   if (node.hasAttribute("ui.clicked")) { 
						 pressedNode+=1;
						 try {
							 
							 for(Node nd : graph){
								 if(nd.hasEdgeToward(node)||nd.hasEdgeFrom(node)){
								 //nd.addAttribute("ui.style", "shadow-mode: plain; shadow-color: gray; shadow-width:3px; shadow-offset:0;");
								 }
							 }
							 
							 CreatePlot chart = new CreatePlot(node.getId(), "C:\\result.txt", "Support of Concept" ,"Size of " + node.getId() + " VS weeks");
							 chart.pack( );
							 RefineryUtilities.positionFrameRandomly(chart);
							 //RefineryUtilities.centerFrameOnScreen( chart );
							 chart.setVisible( true );
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 node.removeAttribute("ui.clicked");
					     loop = false; 
					   }
				   }
			    } 
		}		
}
}