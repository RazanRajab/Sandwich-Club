package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();
        try {
            JSONObject MainJsonObject = new JSONObject(json);
            JSONObject NameJsonObject = MainJsonObject.getJSONObject("name");
            String mainName = NameJsonObject.getString("mainName");
            JSONArray otherNames = NameJsonObject.getJSONArray("alsoKnownAs");
            String placeOfOrigin = MainJsonObject.getString("placeOfOrigin");
            String description = MainJsonObject.getString("description");
            String image = MainJsonObject.getString("image");
            JSONArray ingredientsJSONArray = MainJsonObject.getJSONArray("ingredients");

            List<String> alsoKnownAs = new ArrayList<String>();
            List<String> ingredients = new ArrayList<String>();

            for (int i = 0; i < otherNames.length(); i++) {

                String name = otherNames.getString(i);
                alsoKnownAs.add(name);
            }

            for (int i = 0; i < ingredientsJSONArray.length(); i++) {

                String ingredient = ingredientsJSONArray.getString(i);
                ingredients.add(ingredient);
            }

            sandwich.setMainName(mainName);
            sandwich.setAlsoKnownAs(alsoKnownAs);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setDescription(description);
            sandwich.setImage(image);
            sandwich.setIngredients(ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            sandwich = null;
        }

        return sandwich;
    }
}
