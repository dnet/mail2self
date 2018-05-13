Android application for quickly sending mails to yourself
=========================================================

(poor man's GTD)

Usage
-----

Set your e-mail address in the _Settings_ dialog once, then use the main
UI or the native Android text sharing capability to send mail to yourself.

Motivation
----------

 - There are fine e-mail applications on Android such as K-9 mail, however
   _sharing_ text copies the content to the message body, not the subject.
 - Typing my own e-mail address into the _To_ field is tiresome, and K-9
   makes this even worse.
 - I wanted to try the [Preferences API][1]

Building
--------

 - Download and unpack the Android SDK to any directory (if you haven't done so already).
 - Create the `local.properties` with a single line like `sdk.dir=/path/to/android/sdk` with the correct path.
 - Run `ant debug` to build the project and sign it with a debug key.

License
-------

The whole project is licensed under MIT license.

[1]: https://developer.android.com/guide/topics/ui/settings
