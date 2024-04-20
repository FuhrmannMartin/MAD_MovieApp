package com.example.movieappmad24.navigation

const val DB_ID_KEY = "id"

sealed class Screen(val route: String) {
    data object Home: Screen(route = "home_screen")
    data object Detail: Screen(route = "detail_screen/{$DB_ID_KEY}") {
        fun passId(id: String): String {
            return this.route.replace(oldValue = "{$DB_ID_KEY}", newValue = id)
        }
    }
    data object WatchList: Screen(route = "watch_list")
}