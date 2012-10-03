package org.my.scanExample;

import io.card.payment.CardIOActivity;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class org.my.scanExample.ScanExampleTest \
 * org.my.scanExample.tests/android.test.InstrumentationTestRunner
 */
public class ScanExampleTest extends ActivityInstrumentationTestCase2<MyScanActivity> {
	private Solo solo;

    public ScanExampleTest() {
        super("org.my.scanExample", MyScanActivity.class);
    }
    
    @Override
    public void setUp() throws Exception {
    	solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testBack() throws Exception {
    	solo.assertCurrentActivity("expecting example app", MyScanActivity.class);
    	
    	boolean ableToScan = CardIOActivity.canReadCardWithCamera(solo.getCurrentActivity());
    	
    	solo.clickOnButton(0);
    	
    	if (ableToScan) {
    		// some devices don't support use of the camera.
    		
			solo.assertCurrentActivity("Expected CardIOActivity (scan)", "CardIOActivity");
    	}
    	else {
	    	solo.assertCurrentActivity("Expected DataEntryActivity", "DataEntryActivity");
    	}

    	solo.goBack();
    	
    	solo.assertCurrentActivity("expecting to be back at start", MyScanActivity.class);
    }
    
    public void testManualEntry() throws Exception {
    	solo.assertCurrentActivity("expecting example app", MyScanActivity.class);
    	
    	boolean ableToScan = CardIOActivity.canReadCardWithCamera(solo.getCurrentActivity());
    	
    	solo.clickOnButton(0);
    	
    	if (ableToScan) {
    		// some devices don't support use of the camera.
    		
			solo.assertCurrentActivity("Expected CardIOActivity (scan)", "CardIOActivity");
			solo.clickOnButton("Keyboard...");
    	}
    	
    	solo.assertCurrentActivity("Expected DataEntryActivity", "DataEntryActivity");
    	
		solo.enterText(0, "4111111111111111");
		solo.enterText(1, "12/22");
		
		solo.clickOnButton("Done");
		
		solo.assertCurrentActivity("Expected completion", MyScanActivity.class);

		assertEquals("Card Number not found", true, solo.searchText("Card Number:"));
		assertEquals("Expiry not found", true, solo.searchText("Expiration Date: 12/2022"));

    }
    
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
