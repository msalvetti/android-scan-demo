Android credit card scanning example for card.io
================================================

To give it a try:

1. Clone this repo.
2. Go to https://card.io/accounts/register, sign up for a developer account and create a new app.
3. Download the latest Android library from https://card.io/integrate/android and unzip it into this project. 
4. In MyScanActivity.java, set the value of MY_CARDIO_APP_TOKEN to one created at https://card.io/apps/new/

That's it!!


Automated Testing
-----------------

Example Robotium tests are provided to help you get started with integration testing. To use them:

1. cd app-test
2. run setup.sh
3. run 'ant debug && ant installt test'

That's it!!

We recommend that you use a device test service like Testdroid Cloud (http://testdroid.com) to test your app on a wide range of hardware. 


Detailed documentation can be found at https://card.io/resources/javadoc/index.html

Questions? Comments? Contact us via https://card.io/support