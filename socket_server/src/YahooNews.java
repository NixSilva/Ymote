import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class YahooNews {
	
	private JSONArray jsonArray;
	private int size;
	private int current;
	public int getCurrent(){
		return current;	
	}
	public void setCurrent(int c){
		current = c;	
	}
	public int getSize(){
		return size;
	}
	public YahooNews() throws HttpException, IOException, JSONException{
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
		//回车，获得响应状态码
		int statusCode=httpclient.executeMethod(getMethod);
		JSONObject jsonObject = new JSONObject(getMethod.getResponseBodyAsString());
		jsonObject = new JSONObject(jsonObject.getString("query"));
		jsonObject = new JSONObject(jsonObject.getString("results"));
		jsonArray = new JSONArray(jsonObject.getString("item"));
	}
	public String getNewsByIndex(int i) throws JSONException{
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
					return title+"\t"+url;
				}
			}
		}
		return "-1";
	}
	
	// repeat......
	public String getNext() throws JSONException{
		String res = getNewsByIndex(current+1);
		while(res.equals("-1")){
			res = getNewsByIndex(current+1);
		}
		return res;
	}
	public String getPrevise() throws JSONException{
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
	 */
	public static void main(String[] args) throws HttpException, IOException, JSONException {
		// TODO Auto-generated method stub
		YahooNews yn = new YahooNews();
		
		//for(int i=0;i<yn.getSize();i++){
			System.out.println(yn.getNext());
			System.out.println(yn.getCurrent());
			System.out.println(yn.getNext());
			System.out.println(yn.getCurrent());
			System.out.println(yn.getNext());
			System.out.println(yn.getCurrent());
			System.out.println(yn.getPrevise());
			System.out.println(yn.getCurrent());
			System.out.println(yn.getPrevise());
			System.out.println(yn.getCurrent());
			System.out.println(yn.getPrevise());
		//}
		
	}

}
