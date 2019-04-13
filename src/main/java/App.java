import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/heroForm.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//        post("/heroes", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//
//            String name = request.queryParams("name");
//            String age = request.queryParams("age");
//            String power = request.queryParams("power");
//            String weakness = request.queryParams("weakness");
//
//            Hero newHero = new Hero(name,Integer.parseInt(age),power,weakness);
//            request.session().attribute("hero", newHero);
//
//            model.put("template", "templates/heroes.vtl");
//            model.put("hero", request.session().attributes("hero"));
//            return new ModelAndView(model, layout);
//        }, new VelocityTemplateEngine());


    }
}
