package com.decagonhq.clads.data.model

class Article(var author: String, var title: String) {

    companion object {
        fun getDefaultArticleList(): ArrayList<Article> {

            return arrayListOf(
                Article("Chinua Achebe", "Things Fall Apart",),
                Article("Wole Soyinka", "The Lion and the Jewel"),
                Article("Chimamanda Adichie", "Purple Hibiscus"),
                Article("Ben Okri", "The Famished Road"),
                Article("Buchi Emecheta", "The Joys of Motherhood"),
                Article("Helon Habila", "Oil on Water"),
                Article("Adaobi Nwaubani", "Came to You By Chance"),
                Article("Babatunde Olatunji", "The beat of my drum"),
                Article("Chris Abani", "Graceland, Song for Night"),
                Article("Buchi Emecheta", "The Joys of Motherhood"),
                Article("Helon Habila", "Oil on Water"),
                Article("Adaobi Nwaubani", "Came to You By Chance")

            )
        }
    }
}
