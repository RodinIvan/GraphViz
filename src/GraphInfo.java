import javafx.util.Pair;
import java.io.*;
import java.util.*;


public class GraphInfo {
    List<Pair<String, Integer>> nodes;
    List<Connection> connections;
    String unicodeString = "utf-8";
    //public GraphInfo(String pathFile, boolean supgraph) throws FileNotFoundException, NullPointerException{ //ƒŒœ»À»“‹
    public GraphInfo(String pathFile) throws FileNotFoundException, NullPointerException{ //ƒŒœ»À»“‹
        nodes = new ArrayList<Pair<String, Integer>>();
        connections = new ArrayList<Connection>();
        BufferedReader reader;
        try{
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile),unicodeString));
        try{
            reader.readLine();
            String currentLine;
            while ((currentLine = reader.readLine()) != null){
                boolean foundConcept1 = false, foundConcept2 = false;
                String[] substrs = currentLine.split(";");
                for (int i = 0; i < nodes.size(); ++i){
                    if (!foundConcept1 && nodes.get(i).getKey().toLowerCase().equals(substrs[0].toLowerCase().replaceAll(" ", "_"))){
                        foundConcept1 = true;
                        if (foundConcept2) break;
                    }
                    if (!foundConcept2 && nodes.get(i).getKey().toLowerCase().equals(substrs[1].toLowerCase().replaceAll(" ", "_"))){
                        foundConcept2 = true;
                        if (foundConcept1) break;
                    }
                }
                if ((!foundConcept1)&&(Integer.parseInt(substrs[2])>28)) //¬Õ»Ã¿Õ»≈,  Œ—“€À‹! œÂÂÌÂÒÚË ÔÓ‚ÂÍÛ ‚ MainClass.java!
                    nodes.add(new Pair(substrs[0].toLowerCase().replaceAll(" ", "_"), Integer.parseInt(substrs[2])));
                if ((!foundConcept2)&&(Integer.parseInt(substrs[3])>28))
                    nodes.add(new Pair(substrs[1].toLowerCase().replaceAll(" ", "_"), Integer.parseInt(substrs[3])));
            }
            
        }
        catch(IOException exc){
            throw new NullPointerException("IOException occurred");
        }
        catch(Exception exc){
        	throw new NullPointerException("some error occurred");
        }
        finally {
            try{
                reader.close();
            }
            catch(Exception exc){
                //do nothing. Let's hope future reads will be just fine.
            }
        }
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile),unicodeString));
        try{
            reader.readLine();
            String currentLine;
            while ((currentLine = reader.readLine()) != null){
                String[] substrs = currentLine.split(";");
                Connection connection = new Connection(substrs[0].toLowerCase().replaceAll(" ", "_"), substrs[1].toLowerCase().replaceAll(" ", "_"), Double.parseDouble(substrs[4]));
                if(Double.parseDouble(substrs[4])>0.9)
                	connections.add(connection);
            }
        }
        catch(IOException exc){
            throw new NullPointerException("IOException occurred");
        }
        finally {
            try{
                reader.close();
            }
            catch(Exception exc){
                //do nothing. It's just ending of working with file
            }
        }
      //---------------------------------------------------------------------------------------------------------
        /*
        int nodeBound=28;
        double conBound=0.9;
        boolean standAlone=false;
        
        for(int i=0; i<nodes.size(); i++){
        	//Pair<String, Integer> n = nodes.get(i);
    		if(nodes.get(i).getValue()<nodeBound){
    			for(int j=0; j<connections.size(); j++)
        		{
        			if((nodes.get(i).getKey().equals(connections.get(j).getNode1()))||(nodes.get(i).getKey().equals(connections.get(j).getNode2())))
        			{
        				connections.remove(j);
        			}
        		}
    			nodes.remove(i);		
    		}
    	}
    	for(int i=0; i<connections.size(); i++){
    		if(connections.get(i).getWeight()<conBound)
    			connections.remove(i);
    	}
    	if(!standAlone)
    	{
	    	for(int i=0; i<nodes.size(); i++){
	    		int cntcon=0;
	    		Pair<String, Integer> node = nodes.get(i);
	    		for(int j=0; j<connections.size(); j++){
	    			if((nodes.get(i).getKey().equals(connections.get(j).getNode1()))||(nodes.get(i).getKey().equals(connections.get(j).getNode2()))){	
	    			cntcon++;
	    				break;
	    			}		
	    		}
	    		if(cntcon==0){
	    			nodes.remove(i);
	    		}
	    	}
    	}
    	*/
        //---------------------------------------------------------------------------------------------------------

        }
        catch(Exception exc){
        	//TODO::handle
        }
    }
    public int getNodesCount(){
        return nodes.size();
    }
    public Pair<String, Integer> getNodeAt(int index){
        if (index < 0 || index >= nodes.size())
            return null;
        return nodes.get(index);
    }
    public int getConnectionsCount(){
        return connections.size();
    }
    public Connection getConnectionAt(int index){
        if (index < 0 || index >= connections.size())
            return null;
        return connections.get(index);
    }
    public void deleteNode(String nodename){
    	for(int i=0; i<nodes.size(); i++){
    		if(nodename.equals(nodes.get(i).getKey())){
    			//System.out.println(nodes.get(i).getKey());
    			nodes.remove(i);
    			break;
    		}
    	}
    }
    public void Filter(int nodeBound, double conBound, boolean standAlone){
    	for(int i=0; i<nodes.size(); i++){
    		if(nodes.get(i).getValue()<nodeBound){
    			for(int j=0; j<connections.size(); j++)
        		{
        			if((nodes.get(i).getKey().equals(connections.get(j).node1))||(nodes.get(i).getKey().equals(connections.get(j).node2)))
        			{
        				connections.remove(j);
        			}
        		}
    			nodes.remove(i);		
    		}
    	}
    	for(int i=0; i<connections.size(); i++){
    		if(connections.get(i).getWeight()<conBound)
    			connections.remove(i);
    	}
    	if(!standAlone)
    	{
	    	for(int i=0; i<nodes.size(); i++){
	    		int cntcon=0;
	    		for(int j=0; j<connections.size(); j++){
	    			if((nodes.get(i).getKey().equals(connections.get(j).node1))||(nodes.get(i).getKey().equals(connections.get(j).node2))){
	    				cntcon++;
	    				break;
	    			}		
	    		}
	    		if(cntcon==0){
	    			nodes.remove(i);
	    		}
	    	}
    	}
    }
    
    
    
    
       
    /*
    public int getNodeWeight(int index){
    	if (index < 0 || index >= nodes.size())
            return 0;
        return nodes.get(index).getValue();
    
    }*/
}
