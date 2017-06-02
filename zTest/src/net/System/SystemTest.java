package net.System;

public class SystemTest {

	public static void main(String[] args) {
		SystemExit();
	}
	
	/**
	 * System.exit(0) 后 finally 依然会执行
	 */
	public static void SystemExit() {
		System.setSecurityManager(new SecurityManager() {
			@Override
			public void checkExit(int status) {
				System.out.println("System Exit");
				throw new ThreadDeath();
			}
		});
		try {
			System.exit(0);
		} finally {
			System.out.println("In the finally block");
		}
	}
}
