package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Log {
	public static final boolean DEBUG_MODE = true;
	private static final boolean ECLIPSE = false;

	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSSSSS");
	private static final String OS = System.getProperty("os.name").toLowerCase();

	private static final String RESET = "\u001B[0m";
	private static final String RED = "\u001B[31m";
	private static final String GREEN = "\u001B[32m";
	private static final String YELLOW = "\u001B[33m";
	private static final String BLUE = "\u001B[34m";

	private static enum type {DEBUG, INFO, WARNING, ERROR};


	@SuppressWarnings("unused")
	private static final void printMessage(Object text, type mode) {
		Date date = new Date();
		String prefix = "[_LOG_LEVEL_" + mode + " " + dateFormat.format(date) + "]";

		if(OS.indexOf("win") < 0 && !ECLIPSE) {
		    String color = RESET;
		    switch (mode) {
                case DEBUG:
                    color = BLUE;
                    break;
                case INFO:
                    color = GREEN;
                    break;
                case WARNING:
                    color = YELLOW;
                    break;
                case ERROR:
                    color = RED;
                    break;
		    }

			prefix = color + prefix + RESET;
		}

		System.out.print(prefix + " " + text.toString() + "\n");
	}


	public static final void d(Object t) {
		if(DEBUG_MODE)
			printMessage(t, type.DEBUG);
	}


	public static final void d(int t) {
		if(DEBUG_MODE)
			printMessage(Integer.toString(t), type.DEBUG);
	}

	public static final void d(double t) {
		if(DEBUG_MODE)
			printMessage(Double.toString(t), type.DEBUG);
	}

	public static final void d(float t) {
		if(DEBUG_MODE)
			printMessage(Float.toString(t), type.DEBUG);
	}

	public static final void i(Object t) {
		// if(DEBUG_MODE)
		// 	printMessage(t, type.INFO);
		// else
		// 	System.out.println(t + "\n");
		;
	}

	public static final void w(Object t) {
		if(DEBUG_MODE)
			printMessage(t, type.WARNING);
		else
			System.out.println(t + "\n");
	}

	public static final void e(Object t) {
		if(DEBUG_MODE)
			printMessage(t, type.ERROR);
		else
		{
			System.out.println(t + "\n");
			System.exit(1);
		}
	}

	public static final void e(Exception e) {
		if(DEBUG_MODE)
		{
			e.printStackTrace();
			System.exit(1);
		}
		else
		{
			System.out.println(e.getMessage() + "\n");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static final void exit(){
		System.exit(1);
	}
}
