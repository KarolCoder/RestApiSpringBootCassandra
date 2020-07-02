package com.kgodlewski.myapplication.domain;

public class MagicNumberBody {
    private Integer magic_number;

    public Integer getMagic_number() {
        return magic_number;
    }

    public void setMagic_number(Integer magic_number) {
        this.magic_number = magic_number;
    }

    @Override
    public String toString() {
        return "MagicNumberBody{" +
                "magic_number=" + magic_number +
                '}';
    }
}
