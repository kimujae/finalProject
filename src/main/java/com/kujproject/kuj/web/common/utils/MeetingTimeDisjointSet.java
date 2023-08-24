package com.kujproject.kuj.web.common.utils;

import org.springframework.stereotype.Component;


public class MeetingTimeDisjointSet {
    private int block;
    private int[] parent, frequencySet;

    public MeetingTimeDisjointSet(int block) {
        this.block = block;
    }


    public void setDsu(int[] parent, int[] frequencySet) {
        this.frequencySet = frequencySet;
        this.parent = parent;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int[] getSet() {
        return parent;
    }

    public int[] getFrequencySet() {
        return frequencySet;
    }

    public void setFrequencySet(int i) {
        frequencySet[i]++;
    }

    public void makeSet(){
        parent = new int[block];
        frequencySet = new int[block];
        for(int i = 0; i < block; i++) {
            parent[i] = i;
        }
    }
    public int find(int x){
        if(parent[x] == x) return x;
        else{
            int y = find(parent[x]);
            parent[x] = y;
            return y;
        }
    }
    public void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return;
        if(x > y) parent[x] = y;
        else parent[y] = x;
    }
}
