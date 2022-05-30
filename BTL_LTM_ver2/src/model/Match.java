/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Match implements Serializable{
    private int id;
    private int idplayer1;
    private int idplayer2;
    private String status;

    public Match(int idplayer1, int idplayer2, String status) {
        this.idplayer1 = idplayer1;
        this.idplayer2 = idplayer2;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdplayer1() {
        return idplayer1;
    }

    public void setIdplayer1(int idplayer1) {
        this.idplayer1 = idplayer1;
    }

    public int getIdplayer2() {
        return idplayer2;
    }

    public void setIdplayer2(int idplayer2) {
        this.idplayer2 = idplayer2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
