package com.example.enums

enum class RoutesEnum(val route: String, val title: String) {
    home("home",""),
    all("all","Llistat de pelis"),
    new("new","Nova peli"),
    detail("detail/{id?}","Detall"),
    about("about","About Us"),
}
