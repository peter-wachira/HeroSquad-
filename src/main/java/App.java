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


        // route to serve the form for adding new squads
        get("squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/squadForm.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        // gather input from squad form
        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int size = Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");
            Squad newSquad= new Squad(name,size,cause);
            model.put("template", "templates/squadSuccess.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        //route to handle squads
        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("squads", Squad.all());
            model.put("template", "templates/squads.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //route to handle displaying individual category page
        get("/squads/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
            model.put("squad", squad);
            model.put("template", "templates/squad.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        // route to handle a form for adding new heroes to squads
        get("squads/:id/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
            model.put("squad", squad);
            model.put("template", "templates/squadHeroesForm.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        // modified the post("/heroes") route to "find" the Squad object that we are adding the newHero to then add the hero to that found Squad.
        post("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            Squad squad = Squad.find(Integer.parseInt(request.queryParams("squadId")));

            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            Hero newHero = new Hero(name, age, power, weakness);
            // check if hero is already in the squad
            if (Squad.heroAlreadyExists(newHero)) {
                String heroExists = "Hero " + name + " is already in the squad";
                model.put("heroExists", heroExists);
            }
            //check that squad members dont exceed users specified number of heroes
            else if (squad.getHeroes().size() >= squad.getSize()) {
                String sizeMet = "Squad is full";
                model.put("sizeMet", sizeMet);
            }
            // add the hero
            else{
                squad.addHero(newHero);
            }

            model.put("squad", squad);
            model.put("template", "templates/squadHeroesSuccess.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}
