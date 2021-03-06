package org.levainservice.rest.oms;

import org.levainservice.rest.RESTClient;
import org.levainservice.rest.Response;
import org.levainservice.service.oms.model.User;
import org.levainservice.util.IdRandom;

import com.google.gson.Gson;

public class UserClient extends BaseClient {
	private static final String RESOURCE_PATH = "/userService";

	public Response insert(User user) {
		long id = Long.parseLong(IdRandom.getId());
		user.setUserId(id);
		String json = gson.toJson(user);
		String servicePort = BaseClient.SERVICE_BASE_URL + RESOURCE_PATH + "/";

		Response response = client.request(servicePort,
				RESTClient.SUBMIT_METHOD_POST, json);
		return response;
	}

	public Response signin(User user) {
		String json = gson.toJson(user);
		String servicePort = BaseClient.SERVICE_BASE_URL + RESOURCE_PATH
				+ "/signin";

		Response response = client.request(servicePort,
				RESTClient.SUBMIT_METHOD_POST, json);
		return response;
	}
	
	public Response getUserId(String userName) {
		String servicePort = BaseClient.SERVICE_BASE_URL + RESOURCE_PATH
				+ "/" + userName;

		Response response = client.request(servicePort,
				RESTClient.SUBMIT_METHOD_GET, "");
		return response;
	}


	public static void main(String[] args) {
		UserClient client = new UserClient();
		User user = new User();
		user.setUserName("rest-001");
		user.setUserPasswd("rest-p-001");

		Response r = client.signin(user);
		System.out.println(new Gson().toJson(r));
		
		Response r1 = client.getUserId("test01");
		System.out.println(new Gson().toJson(r1));
	}
}
