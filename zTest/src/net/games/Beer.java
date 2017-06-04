package net.games;

/**
 * 经典问题：一瓶酒 2 元，4 个瓶盖换一瓶酒，2 个酒瓶换一瓶酒。 现有 10 块钱，一共能喝多少瓶酒？
 * @author Tanken·L
 * @version 2016年6月4日
 * @Revise  2016年6月4日
 */
public class Beer {
	/**
	 * 余额
	 */
	int money;
	/**
	 * 啤酒
	 */
	int beers;
	/**
	 * 酒瓶
	 */
	int beerBottle;
	/**
	 * 瓶盖
	 */
	int beerCap;
	public Beer() {}
	/**
	 * 第一步：买酒
	 * @param money
	 */
	public Beer(int money) {
		System.out.println("经典问题：一瓶酒 2 元，4 个瓶盖换一瓶酒，2 个酒瓶换一瓶酒。 现有 10 块钱，一共能喝多少瓶酒？"
				+ "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		this.money = money % 2; // 余额
		beers = money / 2; // 第一次喝到酒的数量
		int step = 1;
		beerBottle = beers;
		beerCap = beers;
		System.out.println("┃ 第 " + step + " 步：共 " + money + " 块钱，买 " + beers 
				+ " 瓶啤酒，剩 " + this.money + " 元。\t\t┃"
				+ "\n┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
		countBeer(++step); // 从第二步开始换酒
	}
	/**
	 * 第二步：换酒
	 * @param step 累计步数
	 */
	public void countBeer(int step) {
		while (beerBottle >= 2 || beerCap >= 4) { // 当酒瓶大于等于2或瓶盖大于等于4时可以换酒
			int nowBottleBeers = 0; // 本次用酒瓶换到的啤酒数
			if (beerBottle >= 2) { // 如果有2个或更多酒瓶，可以换酒
				nowBottleBeers = beerBottle / 2;
				beerCap += nowBottleBeers; // 当前剩余瓶盖数 += 本次用酒瓶换到的啤酒数
				beers += nowBottleBeers; // 已喝到的啤酒总数
				beerBottle = (beerBottle / 2) + (beerBottle % 2); // 剩余酒瓶数 
			}
			int nowCapBeers = 0; // 本次用瓶盖换到的啤酒数
			if (beerCap >= 4) { // 如果有4个或更多瓶盖，可以换酒
				nowCapBeers = beerCap / 4;
				beerBottle += nowCapBeers; // 当前剩余酒瓶数 += 本次用瓶盖换到的啤酒数
				beers += nowCapBeers; // 已喝到的啤酒总数
				beerCap = nowCapBeers + (beerCap % 4); // 剩余瓶盖数 
			}
			System.out.println("┃ 第 " + step + " 步：换了 " + (nowBottleBeers + nowCapBeers)
					+ " 瓶啤酒， 目前喝到 " + beers + " 瓶啤酒 \t\t┃");
			System.out.println("┃\t 剩余 " + beerBottle + " 个啤酒瓶、" + beerCap + " 个啤酒瓶盖  \t\t┃"
					+ "\n┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
			step++;
		}
		System.out.println("┃\t 总共喝到 " + beers + " 瓶啤酒，余 " + this.money + " 元。\t\t┃"
				+ "\n┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	}
	public static void main(String[] args) {
		new Beer(10);
	}
}
