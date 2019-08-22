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
            //use the safe optString() method instead of getString() which will return an empty
            //string instead of null if the value for the that key is not found in the JSON Object.
            String mainName = NameJsonObject.optString("mainName");
            JSONArray otherNames = NameJsonObject.getJSONArray("alsoKnownAs");
            String placeOfOrigin = MainJsonObject.optString("placeOfOrigin");
            String description = MainJsonObject.optString("description");
            String image = MainJsonObject.optString("image");
            JSONArray ingredientsJSONArray = MainJsonObject.getJSONArray("ingredients");

            List<String> alsoKnownAs = new ArrayList<>();
            List<String> ingredients = new ArrayList<>();

            for (int i = 0; i < otherNames.length(); i++) {

                String name = otherNames.optString(i);
                alsoKnownAs.add(name);
            }

            for (int i = 0; i < ingredientsJSONArray.length(); i++) {

                String ingredient = ingredientsJSONArray.optString(i);
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
