package com.github.mc_nagatuki.realminecraft;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

// 爆弾の管理
public class MineManager{
    private Set<String> mines;

    // コンストラクタ
    public MineManager(){
        this.mines = new HashSet<>();
    }

    // 自動設置
    public void layAutomatically(int x1, int z1, int x2, int z2, double probability){
        if(x1 > x2){
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }

        if(z1 > z2){
            int temp = z1;
            z1 = z2;
            z2 = temp;
        }

        Random rd = new Random();
        for(int x = x1; x <= x2; ++x){
            for(int z = z1; z <= z2; ++z){
                double rdVal = rd.nextDouble();
                if(rdVal < probability){
                    lay(x, z);
                }
            }
        }
    }

    // 手動設置
    public void lay(int x, int z){
        String key = coordToString(x, z);
        this.mines.add(key);
    }

    // 手動解除
    public void demine(int x, int z){
        String key = coordToString(x, z);
        this.mines.remove(key);
    }

    // 全解除
    public void demineAll(){
        this.mines.clear();
    }

    // 爆弾の有無
    public boolean hasMine(int x, int z){
        String key = this.coordToString(x, z);
        return this.mines.contains(key);
    }

    // ブロック座標を文字列に
    private String coordToString(int x, int z){
        return "x" + x + "y" + z;
    }
}