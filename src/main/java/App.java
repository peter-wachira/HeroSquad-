import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import java.util.ArrayList;
import java.util.List;
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











//        get("/form", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("template", "templates/heroForm.vtl");
//            return new ModelAndView(model, layout);
//        }, new VelocityTemplateEngine());

        // getting instances of Hero class
        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("heroes", Hero.all());
            model.put("template", "templates/heroes.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //getting heroe by their id
        get("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Hero hero = Hero.find(Integer.parseInt(request.params(":id")));
            model.put("hero", hero);
            model.put("template", "templates/hero.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());






        post("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            ArrayList<Hero> heroes = request.session().attribute("heroes");
            if (heroes == null) {
                heroes = new ArrayList<Hero>();
                request.session().attribute("heroes", heroes);
            }

            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            Hero newHero = new Hero(name,Integer.parseInt(age),power,weakness);
            heroes.add(newHero);

            model.put("template", "templates/heroes.vtl");
            model.put("heroes", request.session().attribute("heroes"));
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
