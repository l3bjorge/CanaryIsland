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
import es.ulpgc.eite.clean.mvp.sample.description.Description;
import es.ulpgc.eite.clean.mvp.sample.description.DescriptionView;
import es.ulpgc.eite.clean.mvp.sample.home.Home;
import es.ulpgc.eite.clean.mvp.sample.islandsmenu.IslandsMenu;
import es.ulpgc.eite.clean.mvp.sample.islandsmenu.IslandsMenuView;
import es.ulpgc.eite.clean.mvp.sample.locations.Locations;
import es.ulpgc.eite.clean.mvp.sample.locations.LocationsView;

public class MediatorApp extends Application implements Mediator.Lifecycle, Mediator.Navigation {

    protected final String TAG = this.getClass().getSimpleName();

    private CanaryIslandState toCanaryIslandState, canaryislandToState;
    private HomeState toHomeState, homeToState;
    private IslandsMenuState toIslandsMenuState, islandsMenuToState;
    private CategoryState toCategoryState, categoryToState;
    private LocationsState toLocationsState, locationsToState;
    private DescriptionState toDescriptionState, descriptionToState;


    private /*static*/ String language;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "calling onCreate()");

        Log.d(TAG, "calling creatingInitialState()");
        toCanaryIslandState = new CanaryIslandState();
        toCanaryIslandState.toolbarVisibility = false;
        toCanaryIslandState.textVisibility = true;


        Log.d(TAG, "calling creatingInitialHelloState()");
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

        if(homeToState != null) {
            Log.d(TAG, "calling settingUpdatedState()");
            presenter.setToolbarVisibility(homeToState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdateState()");
            homeToState = null;
        }

        presenter.onScreenStarted();
    }

    @Override
    public void resumingScreen(Home.HomeTo presenter){
        if(homeToState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(homeToState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdatedState()");
            homeToState = null;
        }

        presenter.onScreenResumed();
    }


    // IslandsMenu Screen

    @Override
    public void startingScreen(IslandsMenu.ToIslandsMenu presenter){
        Log.d(TAG, "calling startingScreen()");
        Log.d(TAG, "language:" + language);
        if(toIslandsMenuState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setToolbarVisibility(toIslandsMenuState.toolbarVisibility);
            presenter.setLanguage(language);
            Log.d(TAG, "calling settingInitialState()"+ language);
            Log.d(TAG, "calling removingInitialState()");
            toIslandsMenuState = null;
        }

        if(islandsMenuToState != null) {
            Log.d(TAG, "calling settingUpdatedState()");
            presenter.setToolbarVisibility(islandsMenuToState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdateState()");
            islandsMenuToState = null;
        }

        presenter.onScreenStarted();
    }



    @Override
    public void resumingScreen(IslandsMenu.IslandsMenuTo presenter){
        if(islandsMenuToState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(islandsMenuToState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdatedState()");
            islandsMenuToState = null;
        }

        presenter.onScreenResumed();
    }

    // Locations Screen

    //TODO REVISAR

    @Override
    public void startingScreen(Locations.ToLocations presenter){
        if(categoryToState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setSelectedItem(categoryToState.selectedItem);
            Log.d(TAG, "calling removingInitialState()");
            categoryToState = null;
        }

        if(locationsToState != null) {
            Log.d(TAG, "calling settingUpdatedState()");
            Log.d(TAG, "calling removingUpdateState()");
            locationsToState = null;
        }

        presenter.onScreenStarted();
    }


    @Override
    public void resumingScreen(Locations.LocationsTo presenter){
        if(locationsToState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(locationsToState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdatedState()");
            locationsToState = null;
        }

        presenter.onScreenResumed();
    }


    // Category Screen

    @Override
    public void startingScreen(Category.ToCategory presenter){
            if(toCategoryState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setToolbarVisibility(toCategoryState.toolbarVisibility);
            Log.d(TAG, "calling removingInitialState()");
                toCategoryState = null;
        }

        if(categoryToState != null) {
            Log.d(TAG, "calling settingUpdatedState()");
            presenter.setToolbarVisibility(categoryToState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdateState()");
            categoryToState = null;
        }

        presenter.onScreenStarted();
    }


    @Override
    public void resumingScreen(Category.CategoryTo presenter){
        if(categoryToState != null) {
            Log.d(TAG, "calling resumingScreen()");
            Log.d(TAG, "calling restoringUpdatedState()");
            presenter.setToolbarVisibility(categoryToState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdatedState()");
            categoryToState = null;
        }

        presenter.onScreenResumed();
    }

    //Description Screen
    @Override
    public void startingScreen(Description.ToDescription presenter) {
        if(toDescriptionState != null) {
            Log.d(TAG, "calling settingInitialState()");
            presenter.setToolbarVisibility(toDescriptionState.toolbarVisibility);
            presenter.setTittleVisibility(toDescriptionState.tittleVisibility);
            presenter.setDescriptionVisibility(toDescriptionState.descriptionVisibility);
            presenter.setLocationBttnVisibility(toDescriptionState.locationBttnVisibility);
            presenter.setWebBttnVisiblity(toDescriptionState.webBttnVisibility);
            presenter.setFaceBttnVisiblity(toDescriptionState.faceBttnVisibility);
            presenter.setInstaBttnVisiblity(toDescriptionState.instaBttnVisibility);
            Log.d(TAG, "calling removingInitialState()");
            toDescriptionState = null;
        }

        if(descriptionToState != null) {
            Log.d(TAG, "calling settingUpdatedState()");
            presenter.setToolbarVisibility(descriptionToState.toolbarVisibility);
            Log.d(TAG, "calling removingUpdateState()");
            descriptionToState = null;
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
        homeToState = new HomeState();
        homeToState.textVisibility = true;
        homeToState.toolbarVisibility = false;
    }

    @Override
    public void goToIslandsMenuScreen(Home.HomeTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        homeToState = new HomeState();
        homeToState.toolbarVisibility = presenter.isToolbarVisible();
        setLanguage(presenter.checkLanguage());
        Log.d(TAG, "language:" + language);
        homeToState.textVisibility = false;


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
        islandsMenuToState = new IslandsMenuState();
        islandsMenuToState.toolbarVisibility = false;
    }

    @Override
    public void goToCategoryScreen(IslandsMenu.IslandsMenuTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        islandsMenuToState = new IslandsMenuState();
        islandsMenuToState.toolbarVisibility = presenter.isToolbarVisible();

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
        categoryToState = new CategoryState();
        categoryToState.toolbarVisibility = false;
    }

    @Override
    public void goToLocationsScreen(Category.CategoryTo presenter) {
        Log.d(TAG, "calling savingUpdatedState()");
        categoryToState = new CategoryState();
        categoryToState.toolbarVisibility = presenter.isToolbarVisible();
        categoryToState.selectedItem = presenter.getSelectedItem();

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
            locationsToState = new LocationsState();
            locationsToState.toolbarVisibility = false;
    }



    @Override
        public void goToDescriptionScreen(Locations.LocationsTo presenter, ModelItem item) {
            Log.d(TAG, "calling savingUpdatedState()");
            locationsToState = new LocationsState();
            locationsToState.toolbarVisibility = presenter.isToolbarVisible();

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
        Log.d(TAG, "calling GoogleMaps");
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
        Uri uri = Uri.parse(url);
        Intent intent = new Intent();
        intent.setData(uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void goToFacebook(String url, String faceId) {
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
        //ModelItem selectedItem;
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

    @Override
    public String getLanguage(){
        return language;
    }

}
