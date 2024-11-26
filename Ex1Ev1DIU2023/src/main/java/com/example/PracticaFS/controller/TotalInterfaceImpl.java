package com.example.PracticaFS.controller;

public class TotalInterfaceImpl implements TotalInterface{
    @Override
    public float total(Integer unidades, Float precio) {
        return unidades*precio;
    }
}
