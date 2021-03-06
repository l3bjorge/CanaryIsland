package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.category.Category;
import es.ulpgc.eite.clean.mvp.sample.description.Description;
import es.ulpgc.eite.clean.mvp.sample.home.Home;
import es.ulpgc.eite.clean.mvp.sample.islandsmenu.IslandsMenu;
import es.ulpgc.eite.clean.mvp.sample.locations.Locations;

/**
 * Created by imac on 23/1/18.
 */

public interface Mediator {

  interface Lifecycle {

    //Home
    void startingScreen(Home.ToHome presenter);
    void resumingScreen(Home.HomeTo presenter);

    //IslandsMenu
    void startingScreen(IslandsMenu.ToIslandsMenu presenter);
    void resumingScreen(IslandsMenu.IslandsMenuTo presenter);

    //Category
    void startingScreen(Category.ToCategory presenter);
    void resumingScreen(Category.CategoryTo presenter);

    //Locations
    void startingScreen(Locations.ToLocations presenter);
    void resumingScreen(Locations.LocationsTo presenter);

    //Description
    void startingScreen(Description.ToDescription presenter);
    void resumingScreen(Description.DescriptionTo presenter);


  }

  interface Navigation {

    //Home
    void goToIslandsMenuScreen(Home.HomeTo presenter);
    void backToPreviousScreen(Home.HomeTo presenter);
    String getLanguage();

    //IslandsMenu
    void goToCategoryScreen(IslandsMenu.IslandsMenuTo presenter);
    void backToPreviousScreen(IslandsMenu.IslandsMenuTo presenter);
    String getIsland();

    //Category
    void goToLocationsScreen(Category.CategoryTo presenter);
    void backToPreviousScreen(Category.CategoryTo presenter);

    //Locations
    void goToDescriptionScreen(Locations.LocationsTo presenter);
    void backToPreviousScreen(Locations.LocationsTo presenter);

    //Description
    void backToPreviousScreen(Description.DescriptionTo presenter);
    void goToGoogleMaps(String url);
    void goToWeb(String url);
    void goToFacebook(String url, String faceId);
    void goToInstagram(String instagramId, String url);
    void share(String location, String image, String map);



  }


}
