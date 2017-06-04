package net.games;

import java.util.Scanner;
 
/**
 * 扩展：推箱子游戏
其他方向补充实现
 * @author Administrator
 *
 */
public class BoxGame {
    public static void main(String[] args) {
        BoxGame bg = new BoxGame();
        bg.startGame();
    }
    private final int PEOPLE = 1;
    private final int BOX = 2;
    private final int AIM = 3;
    private final int BORDER = -1;
    private int peopleRow, peopleCol, boxRow, boxCol, aimRow, aimCol;
    private int[][] map = new int[20][21];
    private Scanner s = new Scanner(System.in);
    private String direction;
    private final String UP = "w";
    private final String DOWN = "s";
    private final String LEFT = "a";
    private final String RIGHT = "d";
    private final int BORDERUP = 0;
    private final int BORDERDOWN = 19;
    private final int BORDERLEFT = 0;
    private final int BORDERRIGHT = 20;
     
    public BoxGame() {
         
    }
     
    private void initLocat(){
        peopleRow = 3;
        peopleCol = 4;
        map[peopleRow][peopleCol] = 1;
         
        boxRow = 5;
        boxCol = 6;
        map[boxRow][boxCol] = 2;
         
        aimRow = 15;
        aimCol = 18;
        map[aimRow][aimCol] = 3;
         
        for (int i = BORDERLEFT; i <= BORDERRIGHT; i ++){
            map[0][i] = -1;
            map[BORDERDOWN][i] = -1;
        }
         
        for (int j = BORDERUP + 1; j < BORDERDOWN; j ++){
            map[j][0] = -1;
            map[j][BORDERRIGHT] = -1;
        }
    }
     
    private void printMap(){
        for (int i = 0; i < map.length; i ++){
            for (int j = 0; j < map[i].length; j ++){
                switch (map[i][j]){
                case PEOPLE:
                    System.out.print("囚");
                    break;
                case BOX:
                    System.out.print("■");
                    break;
                case AIM:
                    System.out.print("★");
                    break;
                case BORDER:
                    System.out.print("▲");
                    break;
                default:
                    System.out.print("□");
                    break;
                }
            }
            System.out.println();
        }
    }
     
    private void scanfDirection(){
        System.out.println("请输入方向：（w:上；s:下；a:左；d:右）");
        direction = s.next();
    }
     
    private boolean move(int row, int col){
         
        if ((map[peopleRow+row][peopleCol+col] != BORDER) && (map[peopleRow+row][peopleCol+col] != AIM)){
            if (boxCol == aimCol && boxRow == aimRow){
                return true;
            }else {
                if (peopleRow+row == boxRow && peopleCol+col == boxCol && map[boxRow-row][boxCol] != BORDER){
                    map[boxRow][boxCol] = 0;
                    boxRow += row;
                    boxCol += col;
                    map[boxRow][boxCol] = 2;
                }
                if (map[peopleRow-1][peopleCol] != 2){
                    map[peopleRow][peopleCol] = 0;
                    peopleRow += row;
                    peopleCol += col;
                    map[peopleRow][peopleCol] = 1;
                }
            }
        }
         
        return false;
    }
     
    private boolean moveBoxAndPeople(){
        if (UP.equalsIgnoreCase(direction)){
            return move(-1, 0);
        }else if (DOWN.equalsIgnoreCase(direction)){
            return move(1, 0);
        }else if (LEFT.equalsIgnoreCase(direction)){
            return move(0, -1);
        }else if (RIGHT.equalsIgnoreCase(direction)){
            return move(0, 1);
        }else {
            System.out.println("朋友，您输入的方向有误！");
        }
         
        return false;
    }
     
    public void startGame(){
        initLocat();
         
        while (true){
            printMap();
            scanfDirection();
            if (moveBoxAndPeople() == true){
                System.out.println("恭喜你顺利到达目的地，游戏结束！");
                break;
            }
        }
    }
}