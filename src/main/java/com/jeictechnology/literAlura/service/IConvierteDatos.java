package com.jeictechnology.literAlura.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);

}
