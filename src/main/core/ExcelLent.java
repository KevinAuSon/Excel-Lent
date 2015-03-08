package main.core;

import java.io.File;
import java.net.URL;
import java.io.FileInputStream;
import java.lang.Exception;

import main.util.Log;
import main.connection.GenomeManager;
import org.json.JSONObject;


public class ExcelLent {
    private static GenomeManager virusesManager;
    private static GenomeManager eukaryotesManager;
    private static GenomeManager prokaryotesManager;
    

	public static void main(String[] args) {
	    File project_root = new File(System.getProperty("user.dir"));
	    File urls_path = new File(project_root.getParent(), "urls_lists.json");
	    File tree_root = new File(project_root.getParent(), "tree");
        
        JSONObject urls = getUrls(urls_path);

        Log.i("Initializing the three genome manager");
        
        try{
            virusesManager = new GenomeManager(new File(tree_root, "Viruses"),
                                                new URL(urls.get("Viruses").toString()));
            eukaryotesManager = new GenomeManager(new File(tree_root, "Eukaryotes"),
                                                new URL(urls.get("Eukaryotes").toString()));
            prokaryotesManager = new GenomeManager(new File(tree_root, "Prokaryote"),
                                                new URL(urls.get("Prokaryote").toString()));
        }catch(Exception e) {
            Log.e(e);
        }
        
        Log.i("Getting the lists and checking what to update");
        virusesManager.AddSpeciesThreads();
        eukaryotesManager.AddSpeciesThreads();
        prokaryotesManager.AddSpeciesThreads();
	}
	
	
	private static JSONObject getUrls(File urls_path) {
	    String urls = null;
	    try{
            FileInputStream fis = new FileInputStream(urls_path);
            byte[] data = new byte[(int) urls_path.length()];
            fis.read(data);
            fis.close();

            urls = new String(data, "UTF-8");
        } catch(Exception e) {
            Log.e(e);
        }
        
        return (JSONObject) new JSONObject(urls).get("urls");
	}
}