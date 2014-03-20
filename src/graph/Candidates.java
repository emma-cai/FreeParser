package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class Candidates {
	public static HashMap<String, String> rel_mt_MAP = new HashMap<String, String>();
	public static HashMap<String, String> rel_et_MAP = new HashMap<String, String>();
	
	public static void main(String[] args){
		init();
		test();
	}
	
	public static void genCandidates(HashMap<String, ArrayList<String>> wrd_tList_MAP, HashMap<String, ArrayList<String>> wrd_rList_MAP){
		HashMap<String, ArrayList<String>> RrW_mtWList_MAP = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> RrW_etWList_MAP = new HashMap<String, ArrayList<String>>();
		
		for(String wrd : wrd_rList_MAP.keySet()){
			ArrayList<String> rList = wrd_rList_MAP.get(wrd);
			
			
			for(String rel : rList){
				String RrW = wrd+"::"+rel;
				String mt = rel_mt_MAP.get(rel);
				String et = rel_et_MAP.get(rel);
				for(String wrd_2 : wrd_tList_MAP.keySet()){
					ArrayList<String> tList = wrd_tList_MAP.get(wrd_2);
					if(tList.contains(mt)){
					//	System.out.println("Find mt: "+rel+"\t"+mt+"\t"+wrd_2);
						ArrayList<String> mtWList = new ArrayList<String>();
						 
					}
					
					if(tList.contains(et)){
					//	System.out.println("Find et: "+rel+"\t"+et+"\t"+wrd_2);
						ArrayList<String> etWList = new ArrayList<String>();
						etWList = RrW_etWList_MAP.get(RrW);
						if(etWList==null || etWList.size()==0 || !etWList.contains(wrd_2))
							etWList.add(wrd_2);
						RrW_etWList_MAP.put(RrW, etWList);
					}
				//	System.out.println("==============================================");
				}
			}
		}
		
		common.Print.printMap(RrW_mtWList_MAP);
		System.out.println("==============================================");
		common.Print.printMap(RrW_etWList_MAP);
	}
	
	public static void test(){
		String sen = "Charley Beal is the art director of the film Sleepless in Seattle";
	//	HashMap<String, HashMap<String, ArrayList<String>>> wrd_genre_vList_MAP = new HashMap<String, HashMap<String, ArrayList<String>>>();
		HashMap<String, ArrayList<String>> wrd_tList_MAP = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> wrd_rList_MAP = new HashMap<String, ArrayList<String>>();
		
		
		ArrayList<String> vList = new ArrayList<String>();
		vList.add("/film/art_director");
		vList.add("/art/visual_artist");
		vList.add("/art/artist");
		wrd_tList_MAP.put("art", vList);
		
		vList = new ArrayList<String>();
		vList.add("/art/artist/album");
		vList.add("/film/film/art_directed_by");
		vList.add("/art/visual_artist/artworks");
		wrd_rList_MAP.put("art", vList);
		
		vList = new ArrayList<String>();
		vList.add("/film/art_director");
		vList.add("/film/director");
		vList.add("/museum/director");
		wrd_tList_MAP.put("director", vList);
		
		vList = new ArrayList<String>();
		vList.add("/film/film/art_directed_by");
		vList.add("/film/film/directed_by");
		vList.add("/tv/tv_director/episodes_directed");
		vList.add("/museum/museum_director/museums");
		wrd_rList_MAP.put("director", vList);
		
		vList = new ArrayList<String>();
		vList.add("/film/film");
		vList.add("/film/film_cut");
		wrd_tList_MAP.put("film", vList);
		
		vList = new ArrayList<String>();
		vList.add("/film/film/directed_by");
		vList.add("/film/film/written_by");
		vList.add("/film/film/art_directed_by");
		wrd_rList_MAP.put("film", vList);
		
		genCandidates(wrd_tList_MAP, wrd_rList_MAP);
	}
	
	public static void init(){
		//initialize rel_mt_map
		rel_mt_MAP.put("/film/film/directed_by", "/film/film");
		rel_mt_MAP.put("/film/film/written_by", "/film/film");
		rel_mt_MAP.put("/film/film/art_directed_by", "/film/film");
		rel_mt_MAP.put("/tv/tv_director/episodes_directed", "/tv/tv_director");
		rel_mt_MAP.put("/museum/museum_director/museums", "/museum/museum_director");
		rel_mt_MAP.put("/art/artist/album", "/art/artist");
		rel_mt_MAP.put("/art/visual_artist/artworks", "/art/visual_artist");
		
		//initialize rel_et_map
		rel_et_MAP.put("/film/film/directed_by", "/film/director");
		rel_et_MAP.put("/film/film/written_by", "/film/writter");
		rel_et_MAP.put("/film/film/art_directed_by", "/film/art_directr");
		rel_et_MAP.put("/tv/tv_director/episodes_directed", "/tv/tv_episodes");
		rel_et_MAP.put("/museum/museum_director/museums", "/museum/museums");
		rel_et_MAP.put("/art/artist/album", "/art/album");
		rel_et_MAP.put("/art/visual_artist/artworks", "/art/artworks");
		
	}
}
