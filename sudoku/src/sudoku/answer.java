package sudoku;

import java.sql.SQLException;

public class answer {

	public static void main(String[] args) {
		
		// get arguments
		char[] il = new char[0];
		int boxes = Integer.parseInt(args[0]);
		int total = Integer.parseInt(args[1]);
		if (args.length == 3) {
			String illimination = args[2];
			il = new char[illimination.length()];
			for (int i = 0; i < illimination.length(); i++) {
				if (i == illimination.length()) break;
				il[i] = illimination.charAt(i);
			}
		} else {
			
		}
		
		if (boxes == 1) System.exit(0);
		if (boxes > 8) System.exit(0);
		
		if (boxes == 2) {
			for(int a = 1; a <= 9; a++) {
				if (checkExclude(a, il)) {
					for(int b = 1 + a; b <= 9; b++) {
						if (checkExclude(b, il)) {
							if (a + b == total) {
								System.out.println(a + " " + b);
								break;
							}
						}
					}
				}
			}
		} else if(boxes == 3) {
			for(int a = 1; a <= 9; a++) {
				if (checkExclude(a, il)) {
					for(int b = a + 1; b <= 9; b++) {
						if (checkExclude(b, il)) {
							for(int c = b + 1; c <= 9; c++) {
								if (checkExclude(c, il)) {
									if ((a != b) && (b != c) && (a != c)) {
										if (a + b + c == total) {
											System.out.println(a + " " + b + " " + c);
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		} else if(boxes == 4) {
			for(int a = 1; a <= 9; a++) {
				if (checkExclude(a, il)) {
					for(int b = a + 1; b <= 9; b++) {
						if (checkExclude(b, il)) {
							for(int c = b + 1; c <= 9; c++) {
								if (checkExclude(c, il)) {
									for(int d = c + 1; d <= 9; d++) {
										if (checkExclude(d, il)) {
											int sum = a + b + c + d;
											if (sum == total) {
												if (d > 9) break;
												if ((a != b) && (b != c) && (c != d)) {
													System.out.println(a + " " + b + " " + c + " " + d);
													break;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} else if(boxes == 5) {
			for(int a = 1; a <= boxes; a++) {
				if (checkExclude(a, il)) {
					for(int b = a + 1; b <= 9; b++) {
						if (checkExclude(b, il)) {
							for(int c = b + 1; c <= 9; c++) {
								if (checkExclude(c, il)) {
									for(int d = c + 1; d <= 9; d++) {
										if (checkExclude(d, il)) {
											for(int e = d + 1; e <= 9; e++) {
												if (checkExclude(e, il)) {
													int sum = a + b + c + d + e;
													if (sum == total) {
														if (e > 9) break;
														if ((a != b) && (b != c) && (c != d) && (d != c)) {
															System.out.println(a + " " + b + " " + c + " " + d + " " + e);
															break;
														}
													} else if (sum > total) break;
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if (a == boxes) break;
			}
		}
	}
	
	public static boolean checkExclude(int number, char[] exclude) {
		
		for (int i = 0; i < exclude.length; i++) {
			int cval = Integer.parseInt(String.valueOf(exclude[i]));
			if (cval == number) {
				return false;
			}
		}
		
		return true;
	}
}