
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {


        // Given : We remove the element at position 2
         onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT)); //le "allOf" permet d'éviter l'erreur d'id multiples
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void myNeighbourList_clickOnContact_shouldShowProfile(){
        //Pick the first neighbour from the list
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.activity_profile_avatar_image)).check(matches(isDisplayed()));

    }

    @Test
    public void myNeighbourList_onProfile_shouldShowRightName(){
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.activity_profile_nomAvatar_text))
                .check(matches(withText("Caroline")));
    }


    @Test
    public void myNeighbourList_onFavoritesTab_shouldOnlyShowFavs(){

        //Vérifie que la liste des favoris est vide
        onView(withText("Favorites")).perform(click());
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(matches(hasChildCount(0)));
        onView(withText("My neighbours")).perform(click());

        //Ajout d'un neighbour en favoris
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.activity_profile_favoris_floatingButton)).perform(click());

        //vérifie que l'onglet des favoris contient bien un neighbour
        onView(withId(R.id.activity_profile_back)).perform(click());
        onView(withContentDescription("Favorites")).perform(click());
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(matches(hasChildCount(1)));

    }
}