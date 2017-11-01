package controllers;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Seq;
import models.Appellation;
import models.Wine;
import org.reactivecouchbase.json.JsArray;
import org.reactivecouchbase.json.JsValue;
import org.reactivecouchbase.json.Json;
import play.libs.ws.WSClient;
import play.mvc.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;
import java.util.stream.StreamSupport;

@Singleton
public class HomeController extends Controller {

    private final WSClient wsClient ;

    @Inject
    public HomeController(WSClient wsClient) {
        this.wsClient = wsClient;
    }

    public CompletionStage<Result> index() {
        return wsClient.url("https://wines-api.herokuapp.com/api/regions").get().thenApply(response -> {
            JsValue value = Json.fromJsonNode(response.asJson());
            JsArray arr = value.as(JsArray.class);
            Seq<String> regions = arr.values.map(v -> v.asString());
            return ok(views.html.regions.render(regions));
        });
    }

    public CompletionStage<Result> wines(String id) {
        return wsClient.url("https://wines-api.herokuapp.com/api/wines/")
                .addQueryParameter("region", id)
                .get().thenApply(response -> {
            JsValue value = Json.fromJsonNode(response.asJson());
            JsArray arr = value.as(JsArray.class);
                    Seq<Wine> wines = arr.values
                            .map(v -> new Wine(
                                    v.field("id").asString(),
                                    v.field("name").asString(),
                                    v.field("type").asString(),
                                    new Appellation(
                                            v.field("appellation").field("name").asString(),
                                            v.field("appellation").field("region").asString()
                                    ),
                                    v.field("grapes").asArray().values.map(g -> g.asString()).toJavaList()
                            ));
            return ok(views.html.wines.render(wines));
        });
    }

    public CompletionStage<Result> wine(String id) {
        return wsClient.url("https://wines-api.herokuapp.com/api/wines/" + id)
                .get().thenApply(response -> {
                    JsValue v = Json.fromJsonNode(response.asJson());
                    Wine wine = new Wine(
                                    v.field("id").asString(),
                                    v.field("name").asString(),
                                    v.field("type").asString(),
                                    new Appellation(
                                        v.field("appellation").field("name").asString(),
                                        v.field("appellation").field("region").asString()
                                    ),
                                    v.field("grapes").asArray().values.map(g -> g.asString()).toJavaList()
                            );
                    return ok(views.html.wine.render(wine));
                });
    }

}
