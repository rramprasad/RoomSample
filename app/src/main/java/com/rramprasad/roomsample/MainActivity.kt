package com.rramprasad.roomsample

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import com.rramprasad.roomsample.data.Recipe
import com.rramprasad.roomsample.data.RecipeDatabase
import com.rramprasad.roomsample.data.RecipeEntity
import com.rramprasad.roomsample.data.RecipeResponse

class MainActivity : AppCompatActivity() {

    private lateinit var recipesRecyclerView: RecyclerView

    private lateinit var mRecipesAdapter: RecipesAdapter

    private lateinit var mLinearLayoutManager: LinearLayoutManager

    private lateinit var mRecipesArrayList: ArrayList<RecipeEntity>



    private lateinit var mDBWorkerThread: DBWorkerThread

    private var mDatabase: RecipeDatabase? = null

    private lateinit var mUIHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDBWorkerThread = DBWorkerThread("DBHandlerThread")
        mDBWorkerThread.start()

        mUIHandler = Handler()

        mDatabase = RecipeDatabase.getRecipeDatabase(this)

        mRecipesArrayList = ArrayList<RecipeEntity>()
        mLinearLayoutManager = LinearLayoutManager(this)
        mRecipesAdapter = RecipesAdapter(mRecipesArrayList)

        recipesRecyclerView = findViewById<RecyclerView>(R.id.recipes_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayoutManager
            adapter = mRecipesAdapter
        }

        mDBWorkerThread.postTask(Runnable {
            populateData()
            fetchData()
        })
    }

    private fun fetchData() {
        val allRecipes = mDatabase?.recipeDao()?.getAllRecipes()
        mRecipesArrayList.clear()

        mRecipesArrayList.addAll(allRecipes!!)

        mUIHandler.post(Runnable {
            mRecipesAdapter.notifyDataSetChanged()
        })
    }

    private fun populateData() {
        val jsonString: String = """[
  {
    "id": 1,
    "name": "Nutella Pie",
    "ingredients": [
      {
        "quantity": 2,
        "measure": "CUP",
        "ingredient": "Graham Cracker crumbs"
      },
      {
        "quantity": 6,
        "measure": "TBLSP",
        "ingredient": "unsalted butter, melted"
      },
      {
        "quantity": 0.5,
        "measure": "CUP",
        "ingredient": "granulated sugar"
      },
      {
        "quantity": 1.5,
        "measure": "TSP",
        "ingredient": "salt"
      },
      {
        "quantity": 5,
        "measure": "TBLSP",
        "ingredient": "vanilla"
      },
      {
        "quantity": 1,
        "measure": "K",
        "ingredient": "Nutella or other chocolate-hazelnut spread"
      },
      {
        "quantity": 500,
        "measure": "G",
        "ingredient": "Mascapone Cheese(room temperature)"
      },
      {
        "quantity": 1,
        "measure": "CUP",
        "ingredient": "heavy cream(cold)"
      },
      {
        "quantity": 4,
        "measure": "OZ",
        "ingredient": "cream cheese(softened)"
      }
    ],
    "steps": [
      {
        "id": 0,
        "shortDescription": "Recipe Introduction",
        "description": "Recipe Introduction",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 1,
        "shortDescription": "Starting prep",
        "description": "1. Preheat the oven to 350\u00b0F. Butter a 9\" deep dish pie pan.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 2,
        "shortDescription": "Prep the cookie crust.",
        "description": "2. Whisk the graham cracker crumbs, 50 grams (1/4 cup) of sugar, and 1/2 teaspoon of salt together in a medium bowl. Pour the melted butter and 1 teaspoon of vanilla into the dry ingredients and stir together until evenly mixed.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 3,
        "shortDescription": "Press the crust into baking form.",
        "description": "3. Press the cookie crumb mixture into the prepared pie pan and bake for 12 minutes. Let crust cool to room temperature.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9cb_4-press-crumbs-in-pie-plate-creampie/4-press-crumbs-in-pie-plate-creampie.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 4,
        "shortDescription": "Start filling prep",
        "description": "4. Beat together the nutella, mascarpone, 1 teaspoon of salt, and 1 tablespoon of vanilla on medium speed in a stand mixer or high speed with a hand mixer until fluffy.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd97a_1-mix-marscapone-nutella-creampie/1-mix-marscapone-nutella-creampie.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 5,
        "shortDescription": "Finish filling prep",
        "description": "5. Beat the cream cheese and 50 grams (1/4 cup) of sugar on medium speed in a stand mixer or high speed with a hand mixer for 3 minutes. Decrease the speed to medium-low and gradually add in the cold cream. Add in 2 teaspoons of vanilla and beat until stiff peaks form.",
        "videoURL": "",
        "thumbnailURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffda20_7-add-cream-mix-creampie/7-add-cream-mix-creampie.mp4"
      },
      {
        "id": 6,
        "shortDescription": "Finishing Steps",
        "description": "6. Pour the filling into the prepared crust and smooth the top. Spread the whipped cream over the filling. Refrigerate the pie for at least 2 hours. Then it's ready to serve!",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffda45_9-add-mixed-nutella-to-crust-creampie/9-add-mixed-nutella-to-crust-creampie.mp4",
        "thumbnailURL": ""
      }
    ],
    "servings": 8,
    "image": ""
  },
  {
    "id": 2,
    "name": "Brownies",
    "ingredients": [
      {
        "quantity": 350,
        "measure": "G",
        "ingredient": "Bittersweet chocolate (60-70% cacao)"
      },
      {
        "quantity": 226,
        "measure": "G",
        "ingredient": "unsalted butter"
      },
      {
        "quantity": 300,
        "measure": "G",
        "ingredient": "granulated sugar"
      },
      {
        "quantity": 100,
        "measure": "G",
        "ingredient": "light brown sugar"
      },
      {
        "quantity": 5,
        "measure": "UNIT",
        "ingredient": "large eggs"
      },
      {
        "quantity": 1,
        "measure": "TBLSP",
        "ingredient": "vanilla extract"
      },
      {
        "quantity": 140,
        "measure": "G",
        "ingredient": "all purpose flour"
      },
      {
        "quantity": 40,
        "measure": "G",
        "ingredient": "cocoa powder"
      },
      {
        "quantity": 1.5,
        "measure": "TSP",
        "ingredient": "salt"
      },
      {
        "quantity": 350,
        "measure": "G",
        "ingredient": "semisweet chocolate chips"
      }
    ],
    "steps": [
      {
        "id": 0,
        "shortDescription": "Recipe Introduction",
        "description": "Recipe Introduction",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc33_-intro-brownies/-intro-brownies.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 1,
        "shortDescription": "Starting prep",
        "description": "1. Preheat the oven to 350�F. Butter the bottom and sides of a 9\"x13\" pan.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 2,
        "shortDescription": "Melt butter and bittersweet chocolate.",
        "description": "2. Melt the butter and bittersweet chocolate together in a microwave or a double boiler. If microwaving, heat for 30 seconds at a time, removing bowl and stirring ingredients in between.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc43_1-melt-choclate-chips-and-butter-brownies/1-melt-choclate-chips-and-butter-brownies.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 3,
        "shortDescription": "Add sugars to wet mixture.",
        "description": "3. Mix both sugars into the melted chocolate in a large mixing bowl until mixture is smooth and uniform.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 4,
        "shortDescription": "Mix together dry ingredients.",
        "description": "4. Sift together the flour, cocoa, and salt in a small bowl and whisk until mixture is uniform and no clumps remain. ",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc9e_4-sift-flower-add-coco-powder-salt-brownies/4-sift-flower-add-coco-powder-salt-brownies.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 5,
        "shortDescription": "Add eggs.",
        "description": "5. Crack 3 eggs into the chocolate mixture and carefully fold them in. Crack the other 2 eggs in and carefully fold them in. Fold in the vanilla.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc62_2-mix-egss-with-choclate-butter-brownies/2-mix-egss-with-choclate-butter-brownies.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 6,
        "shortDescription": "Add dry mixture to wet mixture.",
        "description": "6. Dump half of flour mixture into chocolate mixture and carefully fold in, just until no streaks remain. Repeat with the rest of the flour mixture. Fold in the chocolate chips.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdcc8_5-mix-wet-and-cry-batter-together-brownies/5-mix-wet-and-cry-batter-together-brownies.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 7,
        "shortDescription": "Add batter to pan.",
        "description": "7. Pour the batter into the prepared pan and bake for 30 minutes.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdcf4_8-put-brownies-in-oven-to-bake-brownies/8-put-brownies-in-oven-to-bake-brownies.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 8,
        "shortDescription": "Remove pan from oven.",
        "description": "8. Remove the pan from the oven and let cool until room temperature. If you want to speed this up, you can feel free to put the pan in a freezer for a bit.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 9,
        "shortDescription": "Cut and serve.",
        "description": "9. Cut and serve.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdcf9_9-final-product-brownies/9-final-product-brownies.mp4",
        "thumbnailURL": ""
      }
    ],
    "servings": 8,
    "image": ""
  },
  {
    "id": 3,
    "name": "Yellow Cake",
    "ingredients": [
      {
        "quantity": 400,
        "measure": "G",
        "ingredient": "sifted cake flour"
      },
      {
        "quantity": 700,
        "measure": "G",
        "ingredient": "granulated sugar"
      },
      {
        "quantity": 4,
        "measure": "TSP",
        "ingredient": "baking powder"
      },
      {
        "quantity": 1.5,
        "measure": "TSP",
        "ingredient": "salt"
      },
      {
        "quantity": 2,
        "measure": "TBLSP",
        "ingredient": "vanilla extract, divided"
      },
      {
        "quantity": 8,
        "measure": "UNIT",
        "ingredient": "egg yolks"
      },
      {
        "quantity": 323,
        "measure": "G",
        "ingredient": "whole milk"
      },
      {
        "quantity": 961,
        "measure": "G",
        "ingredient": "unsalted butter, softened and cut into 1 in. cubes"
      },
      {
        "quantity": 6,
        "measure": "UNIT",
        "ingredient": "egg whites"
      },
      {
        "quantity": 283,
        "measure": "G",
        "ingredient": "melted and cooled bittersweet or semisweet chocolate"
      }
    ],
    "steps": [
      {
        "id": 0,
        "shortDescription": "Recipe Introduction",
        "description": "Recipe Introduction",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffddf0_-intro-yellow-cake/-intro-yellow-cake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 1,
        "shortDescription": "Starting prep",
        "description": "1. Preheat the oven to 350\u00b0F. Butter the bottoms and sides of two 9\" round pans with 2\"-high sides. Cover the bottoms of the pans with rounds of parchment paper, and butter the paper as well.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 2,
        "shortDescription": "Combine dry ingredients.",
        "description": "2. Combine the cake flour, 400 grams (2 cups) of sugar, baking powder, and 1 teaspoon of salt in the bowl of a stand mixer. Using the paddle attachment, beat at low speed until the dry ingredients are mixed together, about one minute",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffde28_1-mix-all-dry-ingredients-yellow-cake/1-mix-all-dry-ingredients-yellow-cake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 3,
        "shortDescription": "Prepare wet ingredients.",
        "description": "3. Lightly beat together the egg yolks, 1 tablespoon of vanilla, and 80 grams (1/3 cup) of the milk in a small bowl.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffde36_2-mix-all-wet-ingredients-yellow-cake/2-mix-all-wet-ingredients-yellow-cake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 4,
        "shortDescription": "Add butter and milk to dry ingredients.",
        "description": "4. Add 283 grams (20 tablespoons) of butter and 243 grams (1 cup) of milk to the dry ingredients. Beat at low speed until the dry ingredients are fully moistened, using a spatula to help with the incorporation if necessary. Then beat at medium speed for 90 seconds.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 5,
        "shortDescription": "Add egg mixture to batter.",
        "description": "5. Scrape down the sides of the bowl. Add the egg mixture to the batter in three batches, beating for 20 seconds each time and then scraping down the sides.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffde36_2-mix-all-wet-ingredients-yellow-cake/2-mix-all-wet-ingredients-yellow-cake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 6,
        "shortDescription": "Pour batter into pans.",
        "description": "6. Pour the mixture in two even batches into the prepared pans. Bake for 25 minutes or until a tester comes out of the cake clean. The cake should only start to shrink away from the sides of the pan after it comes out of the oven.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffde43_5-add-mixed-batter-to-baking-pans-yellow-cake/5-add-mixed-batter-to-baking-pans-yellow-cake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 8,
        "shortDescription": "Begin making buttercream.",
        "description": "8. Once the cake is cool, it's time to make the buttercream. You'll start by bringing an inch of water to a boil in a small saucepan. You'll want to use a saucepan that is small enough that when you set the bowl of your stand mixer in it, the bowl does not touch the bottom of the pot.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 9,
        "shortDescription": "Prepare egg whites.",
        "description": "9. Whisk together the egg whites and remaining 300 grams (1.5 cups) of sugar in the bowl of a stand mixer until combined. Set the bowl over the top of the boiling water and continue whisking the egg white mixture until it feels hot to the touch and the sugar is totally dissolved (if you have a reliable thermometer, it should read 150\u00b0F). ",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/5901299d_6-srir-egg-whites-for-frosting-yellow-cake/6-srir-egg-whites-for-frosting-yellow-cake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 10,
        "shortDescription": "Beat egg whites to stiff peaks.",
        "description": "10. Remove the bowl from the pot, and using the whisk attachment of your stand mixer, beat the egg white mixture on medium-high speed until stiff peaks form and the outside of the bowl reaches room temperature.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 11,
        "shortDescription": "Add butter to egg white mixture.",
        "description": "11. Keeping the mixer at medium speed, add the butter one piece at a time to the egg white mixture, waiting 5 to 10 seconds between additions. If the mixture starts to look curdled, just keep beating it! It will come together once it has been mixed enough and has enough butter added. ",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/590129a3_9-mix-in-butter-for-frosting-yellow-cake/9-mix-in-butter-for-frosting-yellow-cake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 12,
        "shortDescription": "Finish buttercream icing.",
        "description": "12. With the mixer still running, pour the melted chocolate into the buttercream. Then add the remaining tablespoon of vanilla and 1/2 teaspoon of salt. Beat at high speed for 30 seconds to ensure the buttercream is well-mixed.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/590129a5_10-mix-in-melted-chocolate-for-frosting-yellow-cake/10-mix-in-melted-chocolate-for-frosting-yellow-cake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 13,
        "shortDescription": "Frost cakes.",
        "description": "13. Frost your cake! Use a serrated knife to cut each cooled cake layer in half (so that you have 4 cake layers). Frost in between the layers, the sides of the cake, and the top of the cake. Then eat it!",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/590129ad_17-frost-all-around-cake-yellow-cake/17-frost-all-around-cake-yellow-cake.mp4",
        "thumbnailURL": ""
      }
    ],
    "servings": 8,
    "image": ""
  },
  {
    "id": 4,
    "name": "Cheesecake",
    "ingredients": [
      {
        "quantity": 2,
        "measure": "CUP",
        "ingredient": "Graham Cracker crumbs"
      },
      {
        "quantity": 6,
        "measure": "TBLSP",
        "ingredient": "unsalted butter, melted"
      },
      {
        "quantity": 250,
        "measure": "G",
        "ingredient": "granulated sugar"
      },
      {
        "quantity": 1,
        "measure": "TSP",
        "ingredient": "salt"
      },
      {
        "quantity": 4,
        "measure": "TSP",
        "ingredient": "vanilla,divided"
      },
      {
        "quantity": 680,
        "measure": "G",
        "ingredient": "cream cheese, softened"
      },
      {
        "quantity": 3,
        "measure": "UNIT",
        "ingredient": "large whole eggs"
      },
      {
        "quantity": 2,
        "measure": "UNIT",
        "ingredient": "large egg yolks"
      },
      {
        "quantity": 250,
        "measure": "G",
        "ingredient": "heavy cream"
      }
    ],
    "steps": [
      {
        "id": 0,
        "shortDescription": "Recipe Introduction",
        "description": "Recipe Introduction",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdae8_-intro-cheesecake/-intro-cheesecake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 1,
        "shortDescription": "Starting prep.",
        "description": "1. Preheat the oven to 350\u00b0F. Grease the bottom of a 9-inch round springform pan with butter. ",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 2,
        "shortDescription": "Prep the cookie crust.",
        "description": "2. To assemble the crust, whisk together the cookie crumbs, 50 grams (1/4 cup) of sugar, and 1/2 teaspoon of salt for the crust in a medium bowl. Stir in the melted butter and 1 teaspoon of vanilla extract until uniform. ",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdb1d_2-form-crust-to-bottom-of-pan-cheesecake/2-form-crust-to-bottom-of-pan-cheesecake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 3,
        "shortDescription": "Start water bath.",
        "description": "3. Fill a large roasting pan with a few inches of hot water and place it on the bottom rack of the oven.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 4,
        "shortDescription": "Prebake cookie crust. ",
        "description": "4. Press the cookie mixture into the bottom and slightly up the sides of the prepared pan. Bake for 11 minutes and then let cool.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 5,
        "shortDescription": "Mix cream cheese and dry ingredients.",
        "description": "5. Beat the cream cheese, remaining 200 grams (1 cup) of sugar, and remaining 1/2 teaspoon salt on medium speed in a stand mixer with the paddle attachment for 3 minutes (or high speed if using a hand mixer). ",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdb3a_3-mix-sugar-salt-together-cheesecake/3-mix-sugar-salt-together-cheesecake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 6,
        "shortDescription": "Add eggs.",
        "description": "6. Scrape down the sides of the pan. Add in the eggs one at a time, beating each one on medium-low speed just until incorporated. Scrape down the sides and bottom of the bowl. Add in both egg yolks and beat until just incorporated. ",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdb55_4-add-eggs-mix-cheesecake/4-add-eggs-mix-cheesecake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 7,
        "shortDescription": "Add heavy cream and vanilla.",
        "description": "7. Add the cream and remaining tablespoon of vanilla to the batter and beat on medium-low speed until just incorporated. ",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdb72_5-mix-vanilla-cream-together-cheesecake/5-mix-vanilla-cream-together-cheesecake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 8,
        "shortDescription": "Pour batter in pan.",
        "description": "8. Pour the batter into the cooled cookie crust. Bang the pan on a counter or sturdy table a few times to release air bubbles from the batter.",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdb88_6-add-the-batter-to-the-pan-w-the-crumbs-cheesecake/6-add-the-batter-to-the-pan-w-the-crumbs-cheesecake.mp4",
        "thumbnailURL": ""
      },
      {
        "id": 9,
        "shortDescription": "Bake the cheesecake.",
        "description": "9. Bake the cheesecake on a middle rack of the oven above the roasting pan full of water for 50 minutes. ",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 10,
        "shortDescription": "Turn off oven and leave cake in.",
        "description": "10. Turn off the oven but keep the cheesecake in the oven with the door closed for 50 more minutes.",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 11,
        "shortDescription": "Remove from oven and cool at room temperature.",
        "description": "11. Take the cheesecake out of the oven. It should look pale yellow or golden on top and be set but still slightly jiggly. Let it cool to room temperature. ",
        "videoURL": "",
        "thumbnailURL": ""
      },
      {
        "id": 12,
        "shortDescription": "Final cooling and set.",
        "description": "12. Cover the cheesecake with plastic wrap, not allowing the plastic to touch the top of the cake, and refrigerate it for at least 8 hours. Then it's ready to serve!",
        "videoURL": "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdbac_9-finished-product-cheesecake/9-finished-product-cheesecake.mp4",
        "thumbnailURL": ""
      }
    ],
    "servings": 8,
    "image": ""
  }
]
"""

        val recipesList: List<Recipe> = Gson().fromJson(jsonString, Array<Recipe>::class.java).toList()

        for (recipe: Recipe in recipesList) {
            val recipeEntity = RecipeEntity(recipe.id, recipe.name, recipe.servings, recipe.imageURL)
            mDatabase?.recipeDao()?.insertRecipe(recipeEntity)
        }
    }
}
