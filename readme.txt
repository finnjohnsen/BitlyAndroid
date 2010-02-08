The easiest way to get this, is just to grab BitlyAndroid.java in the source above and include it in your source.
/src/com /finnjohnsen/bitlyandroid/test/BitlyAndroid.java

To open the project in eclipse
1. File -> New -> Other -> Android Project (File -> Import may work)
2. Create project from existing source. 
3. Project Name can optionally be called BitlyAndroidTest
4. Check Android 1.5 or higher (only tested on 1.5).
5. Click Finish


To run the tests, you need a bit.ly user. Fill out these two variables in BitlyAndroidTest.java:

private static String LOGIN = "";
private static String APIKEY = "";
