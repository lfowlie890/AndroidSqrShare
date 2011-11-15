package com.sqrshare.android.connections;

import org.json.JSONObject;

import com.sqrshare.android.connections.RestClient.RequestMethod;

public class RemoteDBAdapter {
	String server;
	
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

}
