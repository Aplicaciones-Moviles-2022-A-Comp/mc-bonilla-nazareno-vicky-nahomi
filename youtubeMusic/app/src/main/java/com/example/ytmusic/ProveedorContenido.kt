package com.example.ytmusic

class ProveedorContenido {
    companion object{
        //Labels
        var etiquetas = arrayListOf<String>(
            "Relax",
            "Energize",
            "Workout",
            "Commute",
            "Focus"
        )

        //Listen Again List
        var lagainmusic = arrayListOf<LAgainData>(
            LAgainData(
                "Daddy Issues",
                "https://m.media-amazon.com/images/I/61xjbTZ+DML._SS500_.jpg",
                "https://music.youtube.com/watch?v=lqSgsq4Bn2c"
            ),
            LAgainData(
                "Afraid to Feel",
            "https://m.media-amazon.com/images/I/41gdbFDgYYL._UXNaN_FMjpg_QL85_.jpg",
                "https://music.youtube.com/watch?v=2XF2v9UA0og"
            ),
            LAgainData(
                "Ohne Dich",
                "https://i.scdn.co/image/ab67616d0000b273cbe7573e1175f842e24b34c2",
                "https://music.youtube.com/watch?v=60ZFYYqIzIQ"
            ),
            LAgainData(
                "Make It With Chu",
                "https://m.media-amazon.com/images/I/81qky7bNxHL._SS500_.jpg",
                "https://music.youtube.com/watch?v=AZdyD8yFQaU"
            ),
            LAgainData(
                "War Pigs",
                "https://m.media-amazon.com/images/I/51R1fbumq3L.jpg",
                "https://music.youtube.com/watch?v=f-e8-DUqt0I"
            ),
            LAgainData(
                "Do the Evolution",
                "https://lh3.googleusercontent.com/PhlIi_pK0vnfw5wevRCykba4z60yWUQP2aa7nzSXbyGeJTfmazRYpWGdMyYrlREcMaqevHaEGIgBoIU=w544-h544-l90-rj",
                "https://music.youtube.com/watch?v=RQd6DwTJijE"
            ),
            LAgainData(
                "Sweetest Pie",
                "https://lh3.googleusercontent.com/qbSyrjDYpzt0M7gZlt2w_422n7Bdnbv6Nrx5-hU9VjSfJfFycdC2FHN1j4a8W35HN8NF5djaymSP0pR3=w544-h544-l90-rj",
                "https://music.youtube.com/watch?v=nGmUVBKwkaA"
            ),
            LAgainData(
                "Black Hole Sun",
                "https://lh3.googleusercontent.com/BrZVIFcO_XZbT-wHp1XmcmAxJoIuqy2wQQ2K50UuW9f-T94POAPaJDN01H8Gor0O4_3ltotLLAg7h1_e=w544-h544-l90-rj",
                "https://music.youtube.com/watch?v=9kIv6vVRKpw"
            ),
            LAgainData(
                "Go Robot",
                "https://m.media-amazon.com/images/I/81LBhvCK0gL._SS500_.jpg",
                "https://music.youtube.com/watch?v=NlC65820F2I"
            ),
            LAgainData(
                "lonely Boy",
                "https://images-na.ssl-images-amazon.com/images/I/51VV3ApzE2L._SY445_SX342_QL70_ML2_.jpg",
                "https://music.youtube.com/watch?v=9DYPfItb2fk"
            ),
            LAgainData(
                "Reflektor",
                "https://m.media-amazon.com/images/I/8164lCYolQL._SX425_.jpg",
                "https://music.youtube.com/watch?v=4G7B8wAzqCk"
            ),
            LAgainData(
                "Hey Ya!",
                "https://images-na.ssl-images-amazon.com/images/I/51aFehCJxvL._SY445_SX342_QL70_ML2_.jpg",
                "https://music.youtube.com/watch?v=Jx_O6PHdWww"
            )
        )

        //Quick Picks
        var qpicksmusic = arrayListOf<QPicksData>(
            QPicksData(
                "Simple Man",
                "Lynyrd Skynyrd",
                "https://lh3.googleusercontent.com/inUZKsaQDDwOL4dzxIpxoqTwrryh6G-nV7k6Cz4_ct_P28UfSDALuBlvhNY0zOAE5m2ZHlXWxntZ95VB=w544-h544-s-l90-rj",
                "https://music.youtube.com/watch?v=Oddo4MVeeBY"
            ),
            QPicksData(
                "Hold the Line",
                "Toto",
                "https://lh3.googleusercontent.com/SRlWn7d7cfcqZx4KtnjMnHahg2wlEtb_vOzCzULqw0KUpmtN0zrb4YL8q95F9ZsNsUO-yv9VzKwBSk14og=w544-h544-l90-rj",
                "https://music.youtube.com/watch?v=ZP69PLBqFUo&list=RDAMVMOddo4MVeeBY"
            ),
            QPicksData(
                "Down Under",
                "Men At Work",
                "https://lh3.googleusercontent.com/EAT2VCtaMyv3TokdBvlDrAskjpgb1bPXBvFlQay0We8X8ZmqnQ43RNtwR9YgcDFFQUvZuD9sO9FVaKMJ=w544-h544-l90-rj",
                "https://music.youtube.com/watch?v=hfmxO-HQ5rU"
            ),
            QPicksData(
                "Take on Me",
                "A-ha",
                "https://lh3.googleusercontent.com/Hl4_H5E5uxO1esv1hHPd9Y8Nbui-J0356F41rkK5rXTEty0zCfxjPa_yHKp24xcZDQInH28FiZM1HCpI=w544-h544-l90-rj",
                "https://music.youtube.com/watch?v=jtsHy6KMtsA&list=RDAMVMhfmxO-HQ5rU"
            ),
            QPicksData(
                "Dimension",
                "Wolfmother",
                "https://lh3.googleusercontent.com/7pgVyb1X0iaoy1UBZ5JHJENQzx3FZHNDbro9LSrOChwIgt1eBj1YF3ulWjewmBPgpSaBJbkVEIl3XQQ=w544-h544-s-l90-rj",
                "https://music.youtube.com/watch?v=u9ZLJDuSnos"
            ),
            QPicksData(
                "Killing In The Name",
                "Rage Against The Machine",
                "https://lh3.googleusercontent.com/s0pyPiTCElsfstNcNAwUDMiKnMicqBvuvP0SZxHawDOsJhko6ZoaI-iawQ1BWF9pvF072UBnUVv9DtJZ=w544-h544-l90-rj",
                "https://music.youtube.com/watch?v=2o9aoL0NWpw&list=RDAMVMu9ZLJDuSnos"
            ),
            QPicksData(
                "Sexo",
                "Los Prisioneros",
                "https://lh3.googleusercontent.com/i8XrOzMsTKjMQdbBhGF6MaMaM0deIejEnPHPzeXxf3EcXUuu2Hy-Ujd54SThTPo4h16ftotClBYjrlc=w544-h544-l90-rj",
                "https://music.youtube.com/watch?v=Hn7pNgFS6Hc"
            ),
            QPicksData(
                "El Matador",
                "Los Fabulosos Cadillac",
                "https://lh3.googleusercontent.com/qmYMHekCbHENsHoS12FLPjCIFOxJ3YtRasTr3m09WKngBheJpPt92V0u7VmT_47umG24OEIp-8Z2PAlsvg=w544-h544-l90-rj",
                "https://music.youtube.com/watch?v=rv8G30H5hzI"
            )
        )
    }
}