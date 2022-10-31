package com.maximmesh.nasamaterials.repository.notes


data class NoteData(
    val id: Int = 1,
    val name: String? = "Just Notes Name",
    val someDescription: String? = "Swipe left to delete  \nOr press plus to add a new note  \n" +
            "Oh yes, there is a note-taking feature in the NASA app. " +
            "\nAlso we'll soon add a feature to buy a plane ticket and deliver food as soon as a taxi service is available here."
)