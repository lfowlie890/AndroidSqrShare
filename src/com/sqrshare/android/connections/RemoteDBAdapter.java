package com.sqrshare.android.connections;

import org.json.JSONObject;

import com.sqrshare.android.connections.RestClient.RequestMethod;

public class RemoteDBAdapter {
	private String server;
	private String current_session = null;
	
	public RemoteDBAdapter(String server){
		this.server = server;
	}

    public JSONObject nodeGet(int nodeID) throws Exception
    {
        RestClient c = new RestClient(server + "/node/" + nodeID);
        c.AddHeader("Accept", "application/json");
        c.AddHeader("Content-type", "application/json");

        c.Execute(RequestMethod.GET);

        JSONObject json = new JSONObject(c.getResponse());

        return json;


    }
    
    public String login(String userName, String password) throws Exception
    {
        RestClient c = new RestClient(server + "/system/connect.json");
        c.AddHeader("Accept", "application/json");
        c.AddHeader("Content-type", "application/json");
        c.Execute(RequestMethod.POST);
        JSONObject json = new JSONObject(c.getResponse());
        current_session = json.getString("sessid");
        
        c = new RestClient(server + "/user/login.json");
        c.AddHeader("Accept", "application/json");
        c.AddHeader("Content-type", "application/json");
        c.AddParam("sessid", current_session);
        c.AddParam("username", userName);
        c.AddParam("password", password);
        c.Execute(RequestMethod.POST);
        return c.getResponse();
    }
    
    public String logout() throws Exception
    {
        RestClient c = new RestClient(server + "/user/logout.json");
        c.AddHeader("Accept", "application/json");
        c.AddHeader("Content-type", "application/json");
        c.AddParam("sessid", current_session);
        c.Execute(RequestMethod.POST);
        return c.getResponse();
    }

}
