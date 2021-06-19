package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow

object DataDummy {

    fun generateDataDummyCatalogueMovie() : List<Movie>{

        val catalogueMovie = ArrayList<Movie>()
        catalogueMovie.add(
            Movie(
                464052,
                "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                "Wonder Woman 1984",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "en",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "2020-12-16",
                "7"

            )
        )

        catalogueMovie.add(
            Movie(
                651571,
                "/nz8xWrTKZzA5A7FgxaM4kfAoO1W.jpg",
                "Breach",
                "/13B6onhL6FzSN2KaNeQeMML05pS.jpg",
                "en",
                "A hardened mechanic must stay awake and maintain an interstellar ark fleeing the dying planet Earth with a few thousand lucky souls on board... the last of humanity. Unfortunately, humans are not the only passengers. A shapeshifting alien creature has taken residence, its only goal is to kill as many people as possible. The crew must think quickly to stop this menace before it destroys mankind.",
                "2020-12-17",
                "5"
            )
        )
        catalogueMovie.add(
            Movie(
                508442,
                "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                "Soul",
                "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                "en",
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "2020-12-25",
                "8.3"
            )
        )
        catalogueMovie.add(
            Movie(
                604822,
                "/fX8e94MEWSuTJExndVYxKsmA4Hw.jpg",
                "急先锋",
                "/vYvppZMvXYheYTWVd8Rnn9nsmNp.jpg",
                "zh",
                "Covert security company Vanguard is the last hope of survival for an accountant after he is targeted by the world's deadliest mercenary organization.",
                "2020-09-30",
                "6.7"
            )
        )
        catalogueMovie.add(
            Movie(520946,
                "/7TxeZVg2evMG42p0uSbMJpWNQ8A.jpg",
                "100% Wolf",
                "/2VrvxK4yxNCU6KVgo5TADJeBEQu.jpg",
                "en",
                "Freddy Lupin, heir to a proud family line of werewolves, is in for a shock when on his 14th birthday his first 'warfing' goes awry, turning him into a ferocious poodle. The pack elders give Freddy until the next moonrise to prove he has the heart of a wolf, or risk being cast out forever. With the help of an unlikely ally in a streetwise stray named Batty, Freddy must prove he's 100% Wolf.",
                "2020-06-26",
                " 6.4"
            )
        )

        return catalogueMovie
    }
    fun generateDataDummyCatalogueTv() : List<TvShow>{

        val catalogueTv = ArrayList<TvShow>()
        catalogueTv.add(
            TvShow(
                85271,
                "/57vVjteucIF3bGnZj6PmaoJRScw.jpg",
                "WandaVision","/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "en",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "2021-01-15",
                "8.4"
            )
        )

        catalogueTv.add(
            TvShow(
                69050,
                "/9hvhGtcsGhQY58pukw8w55UEUbL.jpg",
                "Riverdale",
                "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "en",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017-01-26",
                "8.6"
            )
        )
        catalogueTv.add(
            TvShow(
                114695,
                "/wAEWZm2pSopAbqE5dQWE0ET8aR5.jpg",
                "Marvel Studios: Legends",
                "/EpDuYIK81YtCUT3gH2JDpyj8Qk.jpg",
                "en",
                "Revisit the epic heroes, villains and moments from across the MCU in preparation for the stories still to come. Each dynamic segment feeds directly into the upcoming series — setting the stage for future events. This series weaves together the many threads that constitute the unparalleled Marvel Cinematic Universe.",
                "2021-01-08",
                "7.7"
            )
        )
        catalogueTv.add(
            TvShow(
                79460,
                "/fRYwdeNjMqC30EhofPx5PlDpdun.jpg",
                "Legacies",
                "/qTZIgXrBKURBK1KrsT7fe3qwtl9.jpg",
                "en",
                "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.",
                "2018-10-25",
                 "8.6"

            )
        )
        catalogueTv.add(
            TvShow(
                71712,
                "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                "The Good Doctor",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "en",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "2017-09-25",
                "8.6"
            )
        )

        return catalogueTv
    }
}