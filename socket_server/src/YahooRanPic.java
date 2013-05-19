import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class YahooRanPic {
	
	private JSONArray jsonArray;
	private int size;
	private int current;
	private Vector<String> pics;
	public int getCurrent(){
		return current;	
	}
	public void setCurrent(int c){
		current = c;	
	}
	public int getSize(){
		return size;
	}
	public YahooRanPic() throws HttpException, IOException, JSONException{
		pics = new Vector<String>();
		current = -1;
		HttpClient httpclient=new HttpClient();
		GetMethod getMethod=new GetMethod("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20rss%20where%20url%3D%22http%3A%2F%2Frss.news.yahoo.com%2Frss%2Ftopstories%22&format=json");
		//回车，获得响应状态码
		int statusCode=httpclient.executeMethod(getMethod);
		JSONObject jsonObject = new JSONObject(getMethod.getResponseBodyAsString());
		jsonObject = new JSONObject(jsonObject.getString("query"));
		jsonObject = new JSONObject(jsonObject.getString("results"));
		jsonArray = new JSONArray(jsonObject.getString("item"));
		size = jsonArray.length();
		//clear();
	}
	
	public void clear() throws JSONException{
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonobject2=jsonArray.getJSONObject(i);
			JSONObject jsonObject = new JSONObject(jsonobject2.getString("content"));
			if(jsonObject.getString("type") == null);
			
			System.out.println(jsonobject2.getString("title"));
			if(jsonobject2.opt("content") != null)
				System.out.println(jsonobject2.getString("content"));
		}
	}
	public void update() throws HttpException, IOException, JSONException{
		HttpClient httpclient=new HttpClient();
		GetMethod getMethod=new GetMethod("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20rss%20where%20url%3D%22http%3A%2F%2Frss.news.yahoo.com%2Frss%2Ftopstories%22&format=json");
		
		int statusCode=httpclient.executeMethod(getMethod);
		JSONObject jsonObject = new JSONObject(getMethod.getResponseBodyAsString());
		jsonObject = new JSONObject(jsonObject.getString("query"));
		jsonObject = new JSONObject(jsonObject.getString("results"));
		jsonArray = new JSONArray(jsonObject.getString("item"));
		size = jsonArray.length();
	}
	
	public void getPic() throws JSONException, HttpException, IOException{
		for(int i=0;i<size;i++){
			JSONObject jsonobject2=jsonArray.getJSONObject(i);
			String title = jsonobject2.getString("title");
			//System.out.println(jsonobject2.getString("content"));
			String url = null;
			if(jsonobject2.opt("content") != null){
				System.out.println(jsonobject2.getString("content"));
				JSONObject jsonObject = new JSONObject(jsonobject2.getString("content"));
				if(jsonObject.opt("type") != null){
					if(jsonObject.getString("type").equals("image/jpeg")){
						
						url=jsonObject.getString("url");
						pics.add(url);
						HttpClient httpclient=new HttpClient();
						GetMethod getMethod=new GetMethod("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20contentanalysis.analyze%20where%20text='"+title.replace(" ", "%20")+"'&format=json");
						
						int statusCode=httpclient.executeMethod(getMethod);
						System.out.println(getMethod.getResponseBodyAsString());
					}
				}
			}
		}
	}
	
	public String getNewsByIndex(int i) throws JSONException, HttpException, IOException, ParserException{
		if(i<0||i>=size)return "-2";
		current = i;
		JSONObject jsonobject2=jsonArray.getJSONObject(i);
		String title = jsonobject2.getString("title");
		//System.out.println(jsonobject2.getString("content"));
		String url = null;
		if(jsonobject2.opt("content") != null){
			JSONObject jsonObject = new JSONObject(jsonobject2.getString("content"));
			if(jsonObject.opt("type") != null){
				if(jsonObject.getString("type").equals("image/jpeg")){
					url=jsonObject.getString("url");
					return "s\t"+title+"\t"+url;
				}
			}
		}
		return "-1";
	}
	public String getUrl(String url) throws HttpException, IOException, ParserException{
		HttpClient httpclient=new HttpClient();
		GetMethod getMethod=new GetMethod(url);
		Pattern pattern = Pattern.compile("src=\"(.+?)\"");
		
		int statusCode=httpclient.executeMethod(getMethod);
		Parser parser = new Parser( getMethod.getResponseBodyAsString() );
		NodeFilter filter = new HasAttributeFilter("class","photo-div");
		NodeList nodes = parser.extractAllNodesThatMatch(filter);
		System.out.println("getText:"+nodes.size());
		for (NodeIterator j = nodes.elements (); j.hasMoreNodes(); ){
			
			Node subnode = j.nextNode();
			parser = new Parser( subnode.toHtml());
			NodeFilter tagfilter = new TagNameFilter ("img");
            NodeList sub_nodes = parser.extractAllNodesThatMatch(tagfilter); 
            for (NodeIterator jj = sub_nodes.elements (); jj.hasMoreNodes(); ){
            	Node sub_node = jj.nextNode();
            	Matcher matcher = pattern.matcher(sub_node.getText());
    			if(matcher.find()){
    				System.out.println(matcher.group(1));
    				return matcher.group(1);
    			}
    				
            	
            }
			//System.out.println("getText:"+subnode.toHtml());
		}
		
		
		return "-1";
	}
	// repeat......
	public String getNext() throws JSONException, HttpException, IOException, ParserException{
		String res = getNewsByIndex(current+1);
		while(res.equals("-1")){
			res = getNewsByIndex(current+1);
		}
		return res;
	}
	public String getPrevise() throws JSONException, HttpException, IOException, ParserException{
		//current-=2;
		String res = getNewsByIndex(current-1);
		while(res.equals("-1")){
			res = getNewsByIndex(current-1);
		}
		return res;
	}
	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws HttpException 
	 * @throws ParserException 
	 */
	public static void main(String[] args) throws HttpException, IOException, JSONException, ParserException {
		// TODO Auto-generated method stub
		YahooRanPic yn = new YahooRanPic();
		//yn.getNewsByIndex(1);
		yn.getPic();
		//for(int i=0;i<yn.getSize();i++){
		
		//}
		/*HttpClient httpclient=new HttpClient();
		GetMethod getMethod=new GetMethod("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20flickr.photos.info%20where%20photo_id%20in%20%28select%20id%20from%20flickr.photos.interestingness%2820%29%20where%20api_key%3D%2292bd0de55a63046155c09f1a06876875%22%20%29%20and%20api_key%3D%2792bd0de55a63046155c09f1a06876875%27%20&diagnostics=true&format=json");
		
		int statusCode=httpclient.executeMethod(getMethod);
		//System.out.println(getMethod.getResponseBodyAsString());
		
		JSONObject jsonObject = new JSONObject(getMethod.getResponseBodyAsString());
		jsonObject = new JSONObject(jsonObject.getString("query"));
		jsonObject = new JSONObject(jsonObject.getString("results"));
		JSONArray jsonArray = new JSONArray(jsonObject.getString("photo"));
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonobject2=jsonArray.getJSONObject(i);
		   // jsonObject = new JSONObject(jsonobject2.getString("urls"));
		    System.out.println(jsonobject2.getString("urls"));
		    jsonobject2 = new JSONObject(jsonobject2.getString("urls"));
		    jsonobject2 = new JSONObject(jsonobject2.getString("url"));
		    System.out.println(jsonobject2.getString("content"));
		    //jsonobject2 = new JSONObject(jsonobject2.getString("content"));
			
		}*/
		//System.out.println(jsonObject.getString("results"));
		
	}

}
