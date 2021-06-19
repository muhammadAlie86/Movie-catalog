package com.example.moviecatalogue.utils

import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.CatalogueEntity

object DataDummy {

    fun generateDataDummyCatalogueMovie() : List<CatalogueEntity>{

        val catalogueMovie = ArrayList<CatalogueEntity>()
        catalogueMovie.add(
            CatalogueEntity(
                "1",
                "A Star Is Born",
                "2h 16m",
                " Drama, Romance, Music",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons",
                "10/05/2018 (US)",
                R.drawable.poster_a_start_is_born,
                "Bradley Cooper"

            )
        )

        catalogueMovie.add(
            CatalogueEntity(
                "2",
                "Alita : Battle Angel",
                "2h 2m",
                "Action, Science Fiction, Adventure ",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "02/14/2019 ",
                R.drawable.poster_alita,
                "Robert Rodriguez"
            )
        )
        catalogueMovie.add(
            CatalogueEntity(
                "3",
                "Aquaman",
                "2h 24m",
                "Action, Adventure, Fantasy",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "12/21/2018",
                R.drawable.poster_aquaman,
                "James Wan"

            )
        )
        catalogueMovie.add(
            CatalogueEntity(
                "4",
                "Bohemian Rapshody",
                "2h 15m",
                " Music, Drama ",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "11/02/2018",
                R.drawable.poster_bohemian,
                "Anthony McCarten"

            )
        )
        catalogueMovie.add(
            CatalogueEntity(
                "5",
                "Cold Persuit",
                "1h 48m",
                "Family, Action ",
                "J.R. Collier, once the world's greatest race driver, is losing his family because he can't get racing out of his blood. His scheme to \"go legit\" by acquiring and reselling exotic cars -- cars he acquires by suckering the owners into racing for the pink slip -- runs afoul of the bad guys when he wins one car that has something in it that they will kill for",
                "10/26/2006",
                R.drawable.poster_cold_persuit,
                "John Schneider"

            )
        )
        catalogueMovie.add(
            CatalogueEntity(
                "6",
                "Creed II",
                "2h 13m",
                "Drama",
                "The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.",
                "11/25/2015 (US)",
                R.drawable.poster_creed,
                "Ryan Coogler"


            )
        )
        catalogueMovie.add(
            CatalogueEntity(
                "7",
                "The Crimes of Grindelwald",
                "2h 14m",
                "Adventure, Fantasy, Drama",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world",
                " 11/16/2018",
                R.drawable.poster_crimes,
                "David Yates"

            )
        )
        catalogueMovie.add(
            CatalogueEntity(
                "8",
                "Glass",
                "2h 9m",
                "Thriller, Drama, Science Fiction",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "01/18/2019",
                R.drawable.poster_glass,
                "M.Night Shyamalan"
            )
        )
        catalogueMovie.add(
            CatalogueEntity(
                "9",
                "How to Train Your Dragon",
                "03/26/2010 (US) 1h 40m",
                "Fantasy, Adventure, Animation, Family ",
                "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father",
                "03/26/2010",
                R.drawable.poster_how_to_train,
                "Dean DeBlois"

            )
        )
        catalogueMovie.add(
            CatalogueEntity(
                "10",
                "Infinity War",
                "2h 29m",
                "Adventure, Action, Science Fiction",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "04/27/2018 ",
                R.drawable.poster_infinity_war,
                "Joe Russo"

            )
        )

        return catalogueMovie
    }
    fun generateDataDummyCatalogueTv() : List<CatalogueEntity>{

        val catalogueTv = ArrayList<CatalogueEntity>()
        catalogueTv.add(
            CatalogueEntity(
                "1",
                "Arrow",
                "49m",
                "Crime, Drama, Mystery, Action & Adventure",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow",
                "10/10/2012",
                R.drawable.poster_arrow,
                "Greg Berlanti"

            )
        )

        catalogueTv.add(
            CatalogueEntity(
                "2",
                "Doom Patrol",
                "49m",
                " Sci-Fi & Fantasy, Action & Adventure, Comedy",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "15/02/2019",
                R.drawable.poster_doom_patrol,
                "Jeremy Calver"
            )
        )
        catalogueTv.add(
            CatalogueEntity(
                "3",
                "Dragon Ball",
                "26m",
                " Animation, Sci-Fi & Fantasy, Action & Adventure ",
                "Five years have passed since the fight with Piccolo Jr., and Goku now has a son, Gohan. The peace is interrupted when an alien named Raditz arrives on Earth in a spacecraft and tracks down Goku, revealing to him that that they are members of a near-extinct warrior race called the Saiyans.",
                "26/04/1989",
                R.drawable.poster_dragon_ball,
                "Akira Toriyama"

            )
        )
        catalogueTv.add(
            CatalogueEntity(
                "4",
                "Fairy Tail",
                "1h 25m",
                "Action, Adventure, Comedy, Fantasy, Animation",
                "Natsu Dragneel and his friends travel to the island Kingdom of Stella, where they will reveal dark secrets, fight the new enemies and once again save the world from destruction.","05/06/2017 (JP)",
                R.drawable.poster_fairytail,
                "Tatsuma Minamikama"

            )
        )
        catalogueTv.add(
            CatalogueEntity(
                "5",
                "Family Guy",
                "57m",
                "Animation, Fantasy, Comedy, Science Fiction ",
                "With the Griffins stuck again at home during a blackout, Peter tells the story of Star Wars Episode VI: Return of the Jedi.",
                "05/22/2011",
                R.drawable.poster_family_guy,
                "Peter Shin"

            )
        )
        catalogueTv.add(
            CatalogueEntity(
                "6",
                "The Flash",
                "44m",
                "Drama, Sci-Fi & Fantasy ",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "07/11/2014",
                R.drawable.poster_flash,
                "Greg Berlanti"


            )
        )
        catalogueTv.add(
            CatalogueEntity(
                "7",
                "Naruto Shipudden",
                "1h 34m",
                "Family, Action, Animation, Adventure, Fantasy ",
                "Demons that once almost destroyed the world, are revived by someone. To prevent the world from being destroyed, the demon has to be sealed and the only one who can do it is the shrine maiden Shion from the country of demons, who has two powers; one is sealing demons and the other is predicting the deaths of humans. This time Naruto's mission is to guard Shion, but she predicts Naruto's death. The only way to escape it, is to get away from Shion, which would leave her unguarded, then the demon, whose only goal is to kill Shion will do so, thus meaning the end of the world. Naruto decides to challenge this prediction of death.",
                "08/04/2007 ",
                R.drawable.poster_naruto_shipudden,
                "Hajime Kamigaki"

            )
        )
        catalogueTv.add(
            CatalogueEntity(
                "8",
                "Gotham",
                "43m",
                "Drama, Fantasy,Crime",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "22/09/2014",
                R.drawable.poster_gotham,
                "Bruno Heller"
            )
        )
        catalogueTv.add(
            CatalogueEntity(
                "9",
                "Grey's Anatomy",
                "43m",
                "Drama",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "27/03/2005",
                R.drawable.poster_grey_anatomy,
                "Shonda Rhimes"

            )
        )
        catalogueTv.add(
            CatalogueEntity(
                "10",
                "Hanna",
                "50m",
                " Action & Adventure, Drama ",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "28/03/2019 ",
                R.drawable.poster_hanna,
                "David Farr"

            )
        )

        return catalogueTv
    }
}