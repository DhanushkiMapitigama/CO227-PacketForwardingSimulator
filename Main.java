package co227PacketSimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Timer;

public class Main implements ActionListener {
	
	
	public static int noRouters = 0,noLinks;
	public static ArrayList<Router>Routers;
	private int cycleNo=0;
	
	Main(){
		Timer watch = new Timer(1000, this);
		watch.start();
	}
	
	public static void main(String[] args) {
		HashMap<String,Link>Links;
		int[][] adjecencyMat = null,forwardingTable;
		boolean settingTopology=false;
		Links = new HashMap<String,Link>();
		Routers = new ArrayList<Router>();
		
		
		boolean firstLine=true;
		String network="B.txt";
		//String matFile = "C:/Hiruna/CO227/Project/src/"+network;
		String matFile = "./src/"+network;
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(matFile))) {
        	while ((line = br.readLine()) != null ) {
        		String [] cmd = line.split(" ");
        		if(firstLine){
        			noRouters=Integer.valueOf(cmd[0]);
        			noLinks=Integer.valueOf(cmd[1]);
        			adjecencyMat = new int[noRouters][noRouters];
        			firstLine=false;
        			//System.out.println(firstLine);
        		}
        		else{
        			
        			int router1=Integer.valueOf(cmd[0]);
        			int router2=Integer.valueOf(cmd[1]);
        			//Pair tempPair = new Pair(router1,router2);
        			Link tempLink1 = new Link((router1)+" to "+(router2));
        			Links.put((router1)+" to "+(router2), tempLink1);
        			Link tempLink2 = new Link((router2)+" to "+(router1));
        			Links.put((router2)+" to "+(router1), tempLink2);
        			//System.out.println(Links.get((router2)+" to "+(router1)).getLinkId());

        			adjecencyMat[router1][router2]=1;
        			adjecencyMat[router2][router1]=1;
        		}     		
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		for(int i=0;i<adjecencyMat.length;i++){
			for(int j=0;j<adjecencyMat.length;j++){
				System.out.print(adjecencyMat[i][j]+" ");
			}
			System.out.println();
		}

		int src=0;
		forwardingTable= new int[noRouters][noRouters];
		ForwadingTable table1= new ForwadingTable(adjecencyMat,src,forwardingTable);
//		for(int i=0;i<forwardingTable.length;i++){
//			for(int j=0;j<forwardingTable.length;j++){
//				System.out.print(forwardingTable[i][j]+" ");
//			}
//			System.out.println();
//		}
//		Pair tempPair = new Pair(0,1);
//		System.out.println(Links.get(tempPair.getPair()).getLinkId());
		
		
		//initialize routers
		for(int i=0;i<noRouters;i++){
			Router tempRouter = new Router(i,adjecencyMat,forwardingTable,Links);
			Routers.add(tempRouter);
		}
		
		gui227 inputWindow= new gui227();
		
		settingTopology = true;
		
		
		
		
//		Packet firstPacket = new Packet("firstPacket","2","0");
//		firstPacket.setRoute("routerID 2");
//		Links.get("2 to 1").addPacketIn(firstPacket);
		
//		Packet firstPacket = new Packet("firstPacket","2","0");
//		Routers.get(2).addToPCconnectedQ(firstPacket);
//		
//		Packet p3 = new Packet("p3","2","0");
//		Routers.get(2).addToPCconnectedQ(p3);
//		
//		Packet p4 = new Packet("p4","2","0");
//		Routers.get(2).addToPCconnectedQ(p4);
//		
//		Packet p5 = new Packet("p5","2","0");
//		Routers.get(2).addToPCconnectedQ(p5);
//		
//		Packet p6 = new Packet("p6","2","0");
//		Routers.get(2).addToPCconnectedQ(p6);
//		
//		Packet p7 = new Packet("p7","2","0");
//		Routers.get(2).addToPCconnectedQ(p7);
//		Packet secondPacket = new Packet("secondPacket","0","3");
//		secondPacket.setRoute("routerID 0");
//		Links.get("0 to 2").addPacketIn(secondPacket);
		
		
		
		
		
		
		Main testObject = new Main();
		
		
		
//		while(cycleNo<1000000){
//			for(int i=0;i<noRouters;i++){
//				Routers.get(i).process(cycleNo);
//			}
//			cycleNo++;
//		}
		System.out.println("time out");
//		for(int i=0;i<firstPacket.getRoute().size();i++){
//			System.out.println(firstPacket.getRoute().get(i));
//		}
//		System.out.println("secondPacket");
//		for(int i=0;i<secondPacket.getRoute().size();i++){
//			System.out.println(secondPacket.getRoute().get(i));
//		}
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("inside");
		for(int i=0;i<noRouters;i++){
			Routers.get(i).process(cycleNo);
			cycleNo++;
		}
		
	}

}