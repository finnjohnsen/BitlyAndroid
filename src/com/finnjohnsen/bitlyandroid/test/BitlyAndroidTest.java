package com.finnjohnsen.bitlyandroid.test;

/*
Copyright 2010 Finn J Johnsen. All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are
permitted provided that the following conditions are met:

   1. Redistributions of source code must retain the above copyright notice, this list of
      conditions and the following disclaimer.

   2. Redistributions in binary form must reproduce the above copyright notice, this list
      of conditions and the following disclaimer in the documentation and/or other materials
      provided with the distribution.

THIS SOFTWARE IS PROVIDED BY JOHNSEN ``AS IS'' AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JOHNSEN OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those of the
authors and should not be interpreted as representing official policies, either expressed
or implied, of Johnsen.
*/
import junit.framework.TestCase;

import com.finnjohnsen.bitlyandroid.test.BitlyAndroid.BitlyReply;
import com.finnjohnsen.bitlyandroid.test.BitlyAndroid.BitlyService;

public class BitlyAndroidTest extends TestCase {

	// Register a login a http://bit.ly
	private static String LOGIN = "";
	private static String APIKEY = "";

	private BitlyAndroid bitly;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		BitlyAndroid.service = BitlyService.BITLY;
		bitly = new BitlyAndroid(LOGIN, APIKEY);
	}

	/**
	 * Tests to use the class as it should be used by users.
	 */
	public void testGetShortUrlAsShouldBeUsed() throws Exception {
		String urlToShorten = "http://johnsenf.blogspot.com/2010/01/android-app-published-1-week-ago.html";
		String shortUrl = bitly.getShortUrl(urlToShorten);
		assertTrue(shortUrl.startsWith("http://bit.ly/"));
	}

	/**
	 * Tests bit.ly result codes and messages.
	 */
	public void testGetResultMessages() throws Exception {
		String urlToShorten = "http://johnsenf.blogspot.com/2010/01/android-app-published-1-week-ago.html";
		BitlyReply reply = bitly.getBitlyReply(urlToShorten);
		assertEquals(new Integer(0), reply.errorCode);
		assertEquals("", reply.errorMessage);
		assertEquals("OK", reply.statusCode);
	}

	/**
	 * Tests bit.ly shortUrl returned.
	 */
	public void testGetShortUrl() throws Exception {
		String urlToShorten = "http://johnsenf.blogspot.com/2010/01/android-app-published-1-week-ago.html";
		BitlyReply reply = bitly.getBitlyReply(urlToShorten);
		assertNotNull(reply.result.hash);
		String shortUrl = reply.getShortUrl();
		assertTrue(shortUrl.startsWith("http://bit.ly/"));
	}

	/**
	 * Tests j.mp shortUrl returned.
	 */
	public void testGetShortUrlFromJMP() throws Exception {
		BitlyAndroid.service = BitlyService.JMP;
		String urlToShorten = "http://johnsenf.blogspot.com/2010/01/android-app-published-1-week-ago.html";
		BitlyReply reply = bitly.getBitlyReply(urlToShorten);
		assertNotNull(reply.result.hash);
		String shortUrl = reply.getShortUrl();
		assertTrue(shortUrl.startsWith("http://j.mp/"));
	}

	/**
	 * Test to confirm different chars work.
	 */
	public void testGetShortUrlFromDifferentChars() throws Exception {
		String urlToShorten = "https://mail.google.com/mail/?shva=1#search/bit.ly";
		BitlyReply reply = bitly.getBitlyReply(urlToShorten);
		assertNotNull(reply.result.hash);
		String shortUrl = reply.getShortUrl();
		assertTrue(shortUrl.startsWith("http://bit.ly/"));
	}
}
