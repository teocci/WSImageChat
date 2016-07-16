package com.teocci.data;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * Created by teocci on 7/16/16.
 */

public class MessageData {
    private JsonObject json;

    public MessageData(JsonObject json) {
        this.json = json;
    }

    public JsonObject getJson() {
        return json;
    }

    public void setJson(JsonObject json) {
        this.json = json;
    }

    @Override
    public String toString(){
        StringWriter writer = new StringWriter();

        Json.createWriter(writer).write(json);

        return writer.toString();
    }
}