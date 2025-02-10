package org.example;

import io.restassured.response.Response;

public class ScooterServiceApi extends BaseHttpClient{
    private String pathCourier = "/api/v1/courier/";
    private String pathGetOrders = "/api/v1/orders/accept/%scourierId=%s";
    private String pathGetTenOrder = "/api/v1/orders?limit=5";
    private String pathOrder = "/api/v1/orders";

    public Response doPostLogin(Object object) {
        return doPostRequest(pathCourier + "login", object);
    }

    public Response doPostCreate(Object object) {
        return doPostRequest(pathCourier, object);
    }

    public Response doPostOrder(Object object) {
        return doPostRequest(pathOrder, object);
    }

    public Response doGet() {
        return doGetRequest(pathGetTenOrder);
    }

    public Response doDelete(String id) {
        return doDeleteRequest(pathCourier + id);
    }

    public Response doPut(String idOrder, String idCourier) {
        return doPutRequest(String.format(pathGetOrders, idOrder, idCourier));
    }
}
