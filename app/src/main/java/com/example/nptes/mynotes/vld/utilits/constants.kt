package com.example.nptes.mynotes.vld.utilits

import com.example.nptes.mynotes.vld.MainActivity
import com.example.nptes.mynotes.vld.database.DatabaseRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var EMAIL: String
lateinit var PASSWORD: String