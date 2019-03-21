package com.chemutai.android;

public class Route {
    private int route_id;
    private String route_name;

    public Route(int route_id, String route_name) {
        this.route_id = route_id;
        this.route_name = route_name;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    @Override
    public String toString() {
        return "Route{" +
                "route_name='" + route_name + '\'' +
                '}';
    }
}
