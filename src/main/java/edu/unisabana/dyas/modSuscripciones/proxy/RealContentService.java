package edu.unisabana.dyas.modSuscripciones.proxy;

import edu.unisabana.dyas.modSuscripciones.models.User;

class RealContentService implements ContentService {
    @Override
    public void accessContent(User user) {
        System.out.println("Accediendo al contenido para el usuario: " + user.getName());
    }
}