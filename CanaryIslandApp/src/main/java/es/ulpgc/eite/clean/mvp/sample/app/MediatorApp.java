package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;


import es.ulpgc.eite.clean.mvp.sample.canaryisland.CanaryIsland;
import es.ulpgc.eite.clean.mvp.sample.canaryisland.CanaryIslandView;
import es.ulpgc.eite.clean.mvp.sample.category.Category;

import es.ulpgc.eite.clean.mvp.sample.category.CategoryView;
import es.ulpgc.eite.clean.mvp.sample.data.LocationItem;
import es.ulpgc.eite.clean.mvp.sample.description.Description;
import es.ulpgc.eite.clean.mvp.sample.description.DescriptionView;
import es.ulpgc.eite.clean.mvp.sample.home.Home;
import es.ulpgc.eite.clean.mvp.sample.islandsmenu.IslandsMenu;
import es.ulpgc.eite.clean.mvp.sample.islandsmenu.IslandsMenuView;
import es.ulpgc.eite.clean.mvp.sample.locations.Locations;
import es.ulpgc.eite.clean.mvp.sample.locations.LocationsView;
import io.realm.Realm;

public class MediatorApp extends Application implements Mediator.Lifecycle, Mediator.Navigation {

    protected final String TAG = this.getClass().getSimpleName();

    private CanaryIslandState toCanaryIslandState, canaryislandToState;
    private HomeState toHomeState, homeToIslandsMenuState;
    private IslandsMenuState islandsMenuToCategoryState;
    private CategoryState categoryToLocationsState;
    private LocationsState locationsToDescriptionState;
    private DescriptionState descriptionToState;


    private /*static*/ String language, island;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Log.d(TAG, "calling onCreate()");

        Log.d(TAG, "calling creatingInitialState()");
        toCanaryIslandState = new CanaryIslandState();
        toCanaryIslandState.toolbarVisibility = false;
        toCanaryIslandState.textVisibility = true;


        Log.d(TAG, "calling creatingInitialHelloState()");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Realm.getDefaultInstance().close();
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // Lifecycle /////////////////////////////////////////////////////////////////////

    // Favourite Screen

    @Override
    public void startingScreen(CanaryIsland.ToCanaryIsland presenter){
        if(toCanaryIslandState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setToolbarVisibility(toCanaryIslandState.toolbarVisibility);
            presenter.setTextVisibility(toCanaryIslandState.textVisibility);

            Log.d(TAG, "calling removingInitialState()");
            toCanaryIslandState = null;
        }

        if(canaryislandToState != null) {
            Log.d(TAG, "calling settingUpdatedState()");
            presenter.setToolbarVisibility(canaryislandToState.toolbarVisibility);
            presenter.setTextVisibility(canaryislandToState.textVisibility);

            Log.d(TAG, "calling removingUpdateState()");
            canaryislandToState = null;
        }

        presenter.onScreenStarted();
    }


    @Override
    public void resumingScreen(CanaryIsland.CanaryIslandTo presenter){
        if(canaryislandToState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(canaryislandToState.toolbarVisibility);
            presenter.setTextVisibility(canaryislandToState.textVisibility);

            Log.d(TAG, "calling removingUpdatedState()");
            canaryislandToState = null;
        }

        presenter.onScreenResumed();
    }

    // Home Screen

    @Override
    public void startingScreen(Home.ToHome presenter){
        if(toHomeState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setToolbarVisibility(toHomeState.toolbarVisibility);
            Log.d(TAG, "calling removingInitialState()");
            toHomeState = null;
        }

        presenter.onScreenStarted();
    }

    @Override
    public void resumingScreen(Home.HomeTo presenter){
        if(homeToIslandsMenuState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(homeToIslandsMenuState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdatedState()");
            homeToIslandsMenuState = null;
        }

        presenter.onScreenResumed();
    }


    // IslandsMenu Screen

    @Override
    public void startingScreen(IslandsMenu.ToIslandsMenu presenter){
        Log.d(TAG, "calling startingScreen()");
        Log.d(TAG, "language:" + language);
        if(homeToIslandsMenuState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setToolbarVisibility(homeToIslandsMenuState.toolbarVisibility);
            presenter.setLanguage(language);
            Log.d(TAG, "calling settingInitialState()"+ language);
            Log.d(TAG, "calling removingInitialState()");
            homeToIslandsMenuState = null;
        }

        presenter.onScreenStarted();
    }



    @Override
    public void resumingScreen(IslandsMenu.IslandsMenuTo presenter){
        if(islandsMenuToCategoryState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(islandsMenuToCategoryState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdatedState()");
            islandsMenuToCategoryState = null;
        }

        presenter.onScreenResumed();
    }

    // Locations Screen

    //TODO REVISAR

    @Override
    public void startingScreen(Locations.ToLocations presenter){
        if(categoryToLocationsState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setSelectedItem(categoryToLocationsState.selectedItem);
            presenter.setLanguage(language);
            Log.d(TAG, "calling removingInitialState()");
            categoryToLocationsState = null;
        }

        if(locationsToDescriptionState != null) {
            Log.d(TAG, "calling settingUpdatedState()");
            Log.d(TAG, "calling removingUpdateState()");
            locationsToDescriptionState = null;
        }

        presenter.onScreenStarted();
    }


    @Override
    public void resumingScreen(Locations.LocationsTo presenter){
        if(locationsToDescriptionState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(locationsToDescriptionState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdatedState()");
            locationsToDescriptionState = null;
        }

        presenter.onScreenResumed();
    }


    // Category Screen

    @Override
    public void startingScreen(Category.ToCategory presenter){
        Log.d(TAG, "island: " + island);
        if(islandsMenuToCategoryState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setToolbarVisibility(islandsMenuToCategoryState.toolbarVisibility);
            presenter.setLanguage(language);
            Log.d(TAG, "calling removingInitialState()");
            islandsMenuToCategoryState = null;
        }

        presenter.onScreenStarted();
    }


    @Override
    public void resumingScreen(Category.CategoryTo presenter){
        if(categoryToLocationsState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(categoryToLocationsState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdatedState()");
            categoryToLocationsState = null;
        }

        presenter.onScreenResumed();
    }

    //Description Screen
    @Override
    public void startingScreen(Description.ToDescription presenter) {
        if(locationsToDescriptionState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setToolbarVisibility(locationsToDescriptionState.toolbarVisibility);
            presenter.setSelectedItem(locationsToDescriptionState.selectedItem);
            Log.d(TAG, "calling removingInitialState()");
            locationsToDescriptionState = null;
        }
        presenter.onScreenStarted();
    }

    @Override
    public void resumingScreen(Description.DescriptionTo presenter) {
        if(descriptionToState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(descriptionToState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdatedState()");
            descriptionToState = null;
        }

        presenter.onScreenResumed();
    }


    ///////////////////////////////////////////////////////////////////////////////////
    // Navigation ////////////////////////////////////////////////////////////////////

    // Favourite Screen

    @Override
    public void backToPreviousScreen(CanaryIsland.CanaryIslandTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        canaryislandToState = new CanaryIslandState();
        canaryislandToState.textVisibility = true;
        canaryislandToState.toolbarVisibility = false;
    }

    @Override
    public void goToNextScreen(CanaryIsland.CanaryIslandTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        canaryislandToState = new CanaryIslandState();
        canaryislandToState.toolbarVisibility = presenter.isToolbarVisible();
        //canaryislandToState.textVisibility = presenter.isTextVisible();
        canaryislandToState.textVisibility = false;

        Context view = presenter.getManagedContext();
        if (view != null) {
            Log.d(TAG, "calling startingNextScreen()");
            view.startActivity(new Intent(view, CanaryIslandView.class));
            //Log.d(TAG, "calling finishingCurrentScreen()");
            //presenter.destroyView();
        }
    }

    // Home Screen

    @Override
    public void backToPreviousScreen(Home.HomeTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        homeToIslandsMenuState = new HomeState();
        homeToIslandsMenuState.textVisibility = true;
        homeToIslandsMenuState.toolbarVisibility = false;
    }

    @Override
    public void goToIslandsMenuScreen(Home.HomeTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        homeToIslandsMenuState = new HomeState();
        homeToIslandsMenuState.toolbarVisibility = presenter.isToolbarVisible();
        setLanguage(presenter.checkLanguage());
        Log.d(TAG, "language:" + language);
        homeToIslandsMenuState.textVisibility = false;


        Context view = presenter.getManagedContext();
        if (view != null) {
            Log.d(TAG, "calling startingNextScreen()");
            view.startActivity(new Intent(view, IslandsMenuView.class));
            //Log.d(TAG, "calling finishingCurrentScreen()");
            //presenter.destroyView();
        }

    }

    // IslandsMenu Screen

    @Override
    public void backToPreviousScreen(IslandsMenu.IslandsMenuTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        islandsMenuToCategoryState = new IslandsMenuState();
        islandsMenuToCategoryState.toolbarVisibility = false;
    }

    @Override
    public void goToCategoryScreen(IslandsMenu.IslandsMenuTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        islandsMenuToCategoryState = new IslandsMenuState();
        islandsMenuToCategoryState.toolbarVisibility = presenter.isToolbarVisible();
        setIsland(presenter.checkIsland());

        Context view = presenter.getManagedContext();
        if (view != null) {
            Log.d(TAG, "calling startingNextScreen()");
            view.startActivity(new Intent(view, CategoryView.class));
//            Log.d(TAG, "calling finishingCurrentScreen()");
//            presenter.destroyView();
        }
    }


    // IslandsMenu Screen

    @Override
    public void backToPreviousScreen(Category.CategoryTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        categoryToLocationsState = new CategoryState();
        categoryToLocationsState.toolbarVisibility = false;
    }

    @Override
    public void goToLocationsScreen(Category.CategoryTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        categoryToLocationsState = new CategoryState();
        categoryToLocationsState.toolbarVisibility = presenter.isToolbarVisible();
        categoryToLocationsState.selectedItem = presenter.getSelectedItem();

        Context view = presenter.getManagedContext();
        if (view != null) {
            Log.d(TAG, "calling startingNextScreen()");
            view.startActivity(new Intent(view, LocationsView.class));
            //Log.d(TAG, "calling finishingCurrentScreen()");
            //presenter.destroyView();
        }
    }

    // Locations Screen

    @Override
        public void backToPreviousScreen(Locations.LocationsTo presenter) {
            Log.d(TAG, "calling savingUpdatedState()");
            locationsToDescriptionState = new LocationsState();
            locationsToDescriptionState.toolbarVisibility = false;
    }



    @Override
        public void goToDescriptionScreen(Locations.LocationsTo presenter) {
            Log.d(TAG, "calling savingUpdatedState()");
            locationsToDescriptionState = new LocationsState();
            locationsToDescriptionState.toolbarVisibility = presenter.isToolbarVisible();
            locationsToDescriptionState.selectedItem = presenter.getLocation();

            Context view = presenter.getManagedContext();
            if (view != null) {
                Log.d(TAG, "calling startingNextScreen()");
                view.startActivity(new Intent(view, DescriptionView.class));
                //Log.d(TAG, "calling finishingCurrentScreen()");
                //presenter.destroyView();
            }
        }

    //Description Screen
    @Override
    public void backToPreviousScreen(Description.DescriptionTo presenter) {

    }

    @Override
    public void goToGoogleMaps(String url) {
        Log.d(TAG, "calling goToGoogleMaps()");
        Uri uri = Uri.parse(url);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mapIntent);
        }


    }

    @Override
    public void goToWeb(String url) {
        Log.d(TAG, "calling goToWeb()");
        Uri uri = Uri.parse(url);
        Intent intent = new Intent();
        intent.setData(uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void goToFacebook(String url, String faceId) {
        Log.d(TAG, "calling goToFacebook()");
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            Uri uri = Uri.parse(faceId);
            intent.setData(uri);
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Aplicación no instalada.");
            //Abre url de pagina.
            Uri uri = Uri.parse(url);
            intent.setData(uri);
            startActivity(intent);
        }
    }

    @Override
    public void goToInstagram(String instagramId, String url) {
        Log.d(TAG, "calling goToInstagram()");
        Uri uri = Uri.parse(instagramId);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
        likeIng.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        likeIng.setPackage("com.instagram.android");
        try {

            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Aplicación no instalada.");
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url)));
        }
    }



    ///////////////////////////////////////////////////////////////////////////////////
    // State /////////////////////////////////////////////////////////////////////////

    private class CanaryIslandState {
        boolean toolbarVisibility;
        boolean textVisibility;
    }

    private class HomeState {
        boolean toolbarVisibility;
        boolean textVisibility;
    }

    private class IslandsMenuState {
        boolean toolbarVisibility;

    }

    private class CategoryState {
        boolean toolbarVisibility;
        ModelItem selectedItem;
    }

    private class LocationsState {
        boolean toolbarVisibility;
        LocationItem selectedItem;
    }

    private class DescriptionState {
        boolean toolbarVisibility;
        boolean tittleVisibility;
        boolean descriptionVisibility;
        boolean locationBttnVisibility;
        boolean webBttnVisibility;
        boolean faceBttnVisibility;
        boolean instaBttnVisibility;

    }


    private void setLanguage(String language) {
        this.language = language;
    }

    private void setIsland(String island) {
        this.island = island;
    }

    @Override
    public String getLanguage(){
        return language;
    }



}
