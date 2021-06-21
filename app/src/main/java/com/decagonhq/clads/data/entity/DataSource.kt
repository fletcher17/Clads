package com.decagonhq.clads.data.model

import com.decagonhq.clads.data.entity.mappedmodel.ResourceHomeVideoModel

class DataSource {
    companion object {
        fun createDataSet(): ArrayList<ArticleModel> {
            val list = ArrayList<ArticleModel>()
            list.add(
                ArticleModel(
                    "",
                    "How to sew Adire",
                    "https://i.pinimg.com/originals/82/be/dc/82bedcbcff5fa4cd3a3845a57e43ff86.jpg"
                )
            )
            list.add(
                ArticleModel(
                    "",
                    "Trending Styles 2021",
                    "https://guardian.ng/wp-content/uploads/2017/12/Emmy-Kasbit.jpg"
                )
            )
            list.add(
                ArticleModel(
                    "",
                    "REETAH- Award winning Fashion Designer",
                    "https://africanfashionandlifestyles.com/wp-content/uploads/2019/05/2019-04-22-07.17.47.png"
                )
            )
            list.add(
                ArticleModel(
                    "",
                    "Beautiful Ankara Designs",
                    "https://www.webbspy.com/wp-content/uploads/2019/11/Top-100-Fashion-Designers-in-Nigeria-Complete-List.jpg"
                )
            )
            list.add(
                ArticleModel(
                    "",
                    "Top 10 Wedding Dress Designers",
                    "https://i.pinimg.com/originals/d3/92/23/d39223250eddb5cda311057dff0ad3fc.jpg"
                )
            )
            list.add(
                ArticleModel("", "Lagos Expo 2022", "https://i.redd.it/yn2jylntia451.jpg")
            )
            return list
        }

        fun createVideoDataSet(): ArrayList<ResourceHomeVideoModel> {
            val list = ArrayList<ResourceHomeVideoModel>()
            list.add(
                ResourceHomeVideoModel(
                    "https://html5demos.com/assets/dizzy.mp4",
                    "https://i.redd.it/yn2jylntia451.jpg",
                    "Who missed the DecaDev Fashion Show?"
                )
            )
            list.add(
                ResourceHomeVideoModel(
                    "https://html5demos.com/assets/dizzy.mp4",
                    "https://dynaimage.cdn.cnn.com/cnn/q_auto,w_1100,c_fill,g_auto/http%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F170313194820-card-african-retailers-oxosi-restricted-use.jpg",
                    "The biggest game changer in African fashion is...the internet"
                )
            )
            list.add(
                ResourceHomeVideoModel(
                    "https://html5demos.com/assets/dizzy.mp4",
                    "https://i.pinimg.com/originals/f3/15/d1/f315d130cf3f8e07871a95e8cb0e9805.jpg",
                    "Who missed the DecaDev Fashion Show?"
                )
            )
            list.add(
                ResourceHomeVideoModel(
                    "https://html5demos.com/assets/dizzy.mp4",
                    "https://media.voguebusiness.com/photos/5f1b10ce548d83dcfc8f6e19/2:3/w_2560%2Cc_limit/AFRICAN-FASHION-POST-PANDEMIC-voguebus-Aart-Verrips-Rich-Mnisi-july-20-story.jpg",
                    "The future of African fashion, post-Covid-19"
                )
            )
            list.add(
                ResourceHomeVideoModel(
                    "https://html5demos.com/assets/dizzy.mp4",
                    "https://sheleadsafrica.org/wp-content/uploads/2016/12/wonderfull_abah_folasade_dan-1024x684.jpg",
                    "Sew easy workshop- SheLead Africa"
                )
            )
            return list
        }
    }
}
