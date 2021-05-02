package mgw.interceptor;

import org.json.JSONObject;
import org.wso2.micro.gateway.interceptor.*;

public class SampleInterceptor implements Interceptor {
    public boolean interceptRequest(Caller caller, Request req) {
        boolean hasApiKey = req.hasHeader("X-API-KEY");
        if (!hasApiKey) {
            Response response = new Response();
            JSONObject responsePayload = new JSONObject().put("error", "Missing required header");
            response.setResponseCode(400);
            response.setJsonPayload(responsePayload);
            caller.respond(response);
            // If we respond from the interceptor it is necessary to set the return value as false.
            // Otherwise the usual request flow will continue.
            return false;
        }
        return true;
    }

    public boolean interceptResponse(Caller caller, Response res) {
        return true;
    }
}
