package com.silence.flickr.global.service

object Endpoints {

    private const val API = "api"
    private const val CLIENTS = "$API/clients"
    const val TOKEN = "$CLIENTS/token"
    const val PROFILE = "$CLIENTS/me"

    const val MENUS = "menus"
    const val MENU = "$MENUS/{id}"

    private const val ORDERS = "$API/orders"
    const val ORDER_CONFIRMATION_BY_CASH = "$ORDERS/{number}/confirm"
    const val ORDER_CONFIRMATION_BY_POINTS = "$ORDERS/{number}/confirmWithPoints"
    const val ORDER_HISTORY = "$ORDERS/history"
    const val ORDER_DETAILS = "$ORDERS/{number}"

    private const val POINTS = "$API/points"
    const val BALANCE = "$POINTS/balance"

    const val SALES = "sale"
    const val SALE = "$SALES/{id}"
}