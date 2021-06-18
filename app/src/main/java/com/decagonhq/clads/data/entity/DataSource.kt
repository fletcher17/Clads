package com.decagonhq.clads.data.model

class DataSource {
    companion object {
        fun createDataSet(): ArrayList<ArticleModel> {
            val list = ArrayList<ArticleModel>()
            list.add(
                ArticleModel(
                    "", "How to sew Adire", "https://i.pinimg.com/originals/82/be/dc/82bedcbcff5fa4cd3a3845a57e43ff86.jpg"
                )
            )
            list.add(
                ArticleModel("", "Trending Styles 2021", "https://guardian.ng/wp-content/uploads/2017/12/Emmy-Kasbit.jpg")
            )
            list.add(
                ArticleModel("", "REETAH- Award winning Fashion Designer", "https://www.webbspy.com/wp-content/uploads/2019/11/Top-100-Fashion-Designers-in-Nigeria-Complete-List.jpg")
            )
            list.add(
                ArticleModel("", "Beautiful Ankara Designs", "https://i0.wp.com/patriarchadvisory.com/wp-content/uploads/2020/10/fg-entrepreneurial-Nigeria.jpg?resize=358%2C184")
            )
            list.add(
                ArticleModel("", "Top 10 Wedding Dress Designers", "https://i.pinimg.com/originals/d3/92/23/d39223250eddb5cda311057dff0ad3fc.jpg")
            )
            list.add(
                ArticleModel("", "Lagos Expo 2022", "https://i.redd.it/yn2jylntia451.jpg")
            )
            return list
        }
    }
}
