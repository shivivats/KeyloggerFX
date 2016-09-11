# KeyloggerFX
Really simple keylogger made using the [JNativeHook](https://github.com/kwhat/jnativehook) library

Make sure to include the JNativeHook jar file as a dependency.

Run the program and click on the button `Start Keyboard Listener` to start the keyboard logger.

All logs will be stored in a file named "keyboard_log.txt", along with the date and time stamp of the start of the log.

You can uncomment the commented portion to use mouse and mousewheel loggers as well. But **make sure to read the code thoroughly** before doing so, as there is a lot of redundant code that may cause problems during run-time. Make the required edits before running the code.
