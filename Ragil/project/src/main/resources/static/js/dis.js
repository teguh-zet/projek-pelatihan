const music = new Audio("/audio/1.mp3");
let lagus = [],
  n = 1;
song.forEach((lagu) => {
  lagus.push({
    id: n,
    songname: ` ${lagu.songName} <br>
    <div class=
    "subtittle">${lagu.artist}</div>`,
    poster: lagu.poster,
  });
  n++;
});
Array.from(document.getElementsByClassName("itemlagu")).forEach((e, i) => {
  e.getElementsByTagName("img")[0].src = lagus[i].poster;
  e.getElementsByTagName("h5")[0].innerHTML = lagus[i].songname;
});

// /// mulai pencarian
// let hasil_pencarian = document.getElementsByClassName("hasil_pencarian")[0];
// lagus.forEach((Element) => {
//   const { id, songname, poster } = Element;
//   // console.log(poster);
//   let card = document.createElement("a");
//   card.classList.add("card");
//   card.href = "#" + id;
//   card.innerHTML = `
//   <img src="${poster}" alt="">
//   <div class="content">
//       ${songname}
//   </div>
//   `;
//   hasil_pencarian.appendChild(card);
// });

// let input = document.getElementsByTagName("input")[0];
// input.addEventListener("keyup", () => {
//   let input_value = input.value.toUpperCase();

//   let item = hasil_pencarian.getElementsByTagName("a");

//   for (let index = 0; index < item.length; index++) {
//     const as = item[index].getElementsByClassName("content")[0];
//     let text_value = as.textContent || as.innerHTML;

//     if (text_value.toUpperCase().indexOf(input_value) > -1) {
//       item[index].style.display = "flex";
//     } else {
//       item[index].style.display = "none";
//     }

//     if (input.value == 0) {
//       hasil_pencarian.style.display = "none";
//     } else {
//       hasil_pencarian.style.display = "";
//     }
//   }
// });
// /// akhir pencarian

// let masterplay = document.getElementById("masterplay");
// let putar = document.getElementById("putar");
// masterplay.addEventListener("click", () => {
//   if (music.paused || music.currentTime <= 0) {
//     music.play();
//     putar.classList.add("active1");
//     masterplay.classList.remove("bi-play");
//     masterplay.classList.add("bi-pause");
//   } else {
//     music.pause();
//     putar.classList.remove("active1");
//     masterplay.classList.add("bi-play");
//     masterplay.classList.remove("bi-pause");
//   }
// });

// const backgrounds = () => {
//   Array.from(document.getElementsByClassName("itemlagu")).forEach((e1) => {
//     e1.style.background = ` rgb(105, 105, 105 .0)`;
//   });
// };

// const plays = () => {
//   Array.from(document.getElementsByClassName("playlistplay")).forEach((e1) => {
//     e1.classList.add("bi-play-circle");
//     e1.classList.remove("bi-pause-circle");
//   });
// };

// let index = 0;
// let poster_putar_musik = document.getElementById("poster_putar_musik");
// let title = document.getElementById("title");
// Array.from(document.getElementsByClassName("playlistplay")).forEach((e) => {
//   e.addEventListener("click", (e1) => {
//     index = e1.target.id;
//     music.src = `audio/${index}.mp3`;
//     music.play();
//     masterplay.classList.remove("bi-play");
//     masterplay.classList.add("bi-pause");
//     let songtitle = lagus.filter((els) => {
//       return els.id == index;
//     });
//     songtitle.forEach((elss) => {
//       let { songname, poster } = elss;
//       title.innerHTML = songname;
//       poster_putar_musik.src = poster;
//     });
//     backgrounds();
//     Array.from(document.getElementsByClassName("itemlagu"))[
//       index - 1
//     ].style.background = "rgb(105, 105, 105 .1)";
//     plays();
//     e1.target.classList.remove("bi-play-circle");
//     e1.target.classList.add("bi-pause-circle");
//     putar.classList.add("active1");
//   });
// });

// let currentstart = document.getElementById("currentstart");
// let currentend = document.getElementById("currentend");
// let seek = document.getElementById("seek");
// let bar2 = document.getElementById("bar2");
// let dot = document.getElementsByClassName("dot")[0];

// music.addEventListener("timeupdate", () => {
//   let music_curr = music.currentTime;
//   let music_durr = music.duration;
//   let min1 = Math.floor(music_durr / 60);
//   let sec1 = Math.floor(music_durr % 60);
//   // console.log(min1);
//   if (sec1 < 10) {
//     sec1 = `0${sec1}`;
//   }
//   currentend.innerText = `${min1}:${sec1}`;

//   let min2 = Math.floor(music_curr / 60);
//   let sec2 = Math.floor(music_curr % 60);
//   if (sec2 < 10) {
//     sec2 = `0${sec2}`;
//   }

//   currentstart.innerText = `${min2}:${sec2}`;

//   let progressbar = parseInt((music_curr / music_durr) * 100);
//   seek.value = progressbar;
//   // console.log(seek.value);
//   let seekbar = seek.value;
//   bar2.style.width = `${seekbar}%`;
//   dot.style.left = `${seekbar}%`;
// });

// seek.addEventListener("change", () => {
//   music.currentTime = (seek.value * music.duration) / 100;
// });

// let vol_icon = document.getElementById("vol_icon");
// let vol = document.getElementById("vol");
// let vol_bar = document.getElementsByClassName("vol_bar")[0];
// let vol_dot = document.getElementById("vol_dot");

// vol.addEventListener("change", () => {
//   if (vol.value == 0) {
//     vol_icon.classList.remove("bi-volume-up");
//     vol_icon.classList.remove("bi-volume-down");
//     vol_icon.classList.add("bi-volume-off");
//   }
//   if (vol.value > 0) {
//     vol_icon.classList.remove("bi-volume-up");
//     vol_icon.classList.add("bi-volume-down");
//     vol_icon.classList.remove("bi-volume-off");
//   }
//   if (vol.value > 50) {
//     vol_icon.classList.add("bi-volume-up");
//     vol_icon.classList.remove("bi-volume-down");
//     vol_icon.classList.remove("bi-volume-off");
//   }
//   let vol_a = vol.value;
//   vol_bar.style.width = `${vol_a}%`;
//   vol_dot.style.left = `${vol_a}%`;
//   music.volume = vol_a / 100;
// });

// let back = document.getElementById("back");
// let next = document.getElementById("next");

// back.addEventListener("click", () => {
//   index -= 1;
//   if (index < 1) {
//     index = Array.from(document.getElementsByClassName("itemlagu")).length;
//   }
//   music.src = `audio/${index}.mp3`;
//   music.play();
//   masterplay.classList.remove("bi-play");
//   masterplay.classList.add("bi-pause");
//   let songtitle = lagus.filter((els) => {
//     return els.id == index;
//   });
//   songtitle.forEach((elss) => {
//     let { songname, poster } = elss;
//     title.innerHTML = songname;
//     poster_putar_musik.src = poster;
//   });
//   backgrounds();
//   Array.from(document.getElementsByClassName("itemlagu"))[
//     index - 1
//   ].style.background = "rgb(105, 105, 105 .1)";
//   plays();
//   e1.target.classList.remove("bi-play-circle");
//   e1.target.classList.add("bi-pause-circle");
//   putar.classList.add("active1");
// });

// next.addEventListener("click", () => {
//   index++;
//   if (index > Array.from(document.getElementsByClassName("itemlagu")).length) {
//     index = 1;
//   }

//   music.src = `audio/${index}.mp3`;
//   music.play();
//   masterplay.classList.remove("bi-play");
//   masterplay.classList.add("bi-pause");
//   let songtitle = lagus.filter((els) => {
//     return els.id == index;
//   });
//   songtitle.forEach((elss) => {
//     let { songname, poster } = elss;
//     title.innerHTML = songname;
//     poster_putar_musik.src = poster;
//   });
//   backgrounds();
//   Array.from(document.getElementsByClassName("itemlagu"))[
//     index - 1
//   ].style.background = "rgb(105, 105, 105 .1)";
//   plays();
//   e1.target.classList.remove("bi-play-circle");
//   e1.target.classList.add("bi-pause-circle");
//   putar.classList.add("active1");
// });

// let popsong_left = document.getElementById("popsong_left");
// let popsong_right = document.getElementById("popsong_right");
// let popsong = document.getElementsByClassName("popsong")[0];

// popsong_right.addEventListener("click", () => {
//   popsong.scrollLeft += 330;
// });
// popsong_left.addEventListener("click", () => {
//   popsong.scrollLeft -= 330;
// });

// let popartis_left = document.getElementById("popartis_left");
// let popartis_right = document.getElementById("popartis_right");
// let item = document.getElementsByClassName("item")[0];

// popartis_right.addEventListener("click", () => {
//   item.scrollLeft += 330;
// });
// popartis_left.addEventListener("click", () => {
//   item.scrollLeft -= 330;
// });

// let shuffle = document.getElementsByClassName("shuffle")[0];

// shuffle.classList.add("bi-repeat");
// shuffle.addEventListener("click", () => {
//   let a = shuffle.innerHTML;
//   switch (a) {
//     case "next":
//       shuffle.classList.add("bi-repeat");
//       shuffle.classList.remove("bi-music-note-beamed");
//       shuffle.classList.remove("bi-shuffle");
//       shuffle.innerHTML = "repeat";
//       break;
//     case "repeat":
//       shuffle.classList.remove("bi-repeat");
//       shuffle.classList.remove("bi-music-note-beamed");
//       shuffle.classList.add("bi-shuffle");
//       shuffle.innerHTML = "random";
//       break;
//     case "random":
//       shuffle.classList.remove("bi-repeat");
//       shuffle.classList.add("bi-music-note-beamed");
//       shuffle.classList.remove("bi-shuffle");
//       shuffle.innerHTML = "next";
//       break;
//   }
// });

// const next_music = () => {
//   if (index == lagus.length) {
//     index = 1;
//   } else {
//     index++;
//   }
//   music.src = `audio/${index}.mp3`;
//   music.play();
//   masterplay.classList.remove("bi-play");
//   masterplay.classList.add("bi-pause");

//   unduh_music.href = `audio/${index}.mp3`;

//   let songtitle = lagus.filter((els) => {
//     return els.id == index;
//   });
//   songtitle.forEach((elss) => {
//     let { songname, poster } = elss;
//     title.innerHTML = songname;
//     unduh_music.setAttribute("download", songname);
//     poster_putar_musik.src = poster;
//   });
//   backgrounds();
//   Array.from(document.getElementsByClassName("itemlagu"))[
//     index - 1
//   ].style.background = "rgb(105, 105, 105 .1)";
//   plays();
//   e1.target.classList.remove("bi-play-circle");
//   e1.target.classList.add("bi-pause-circle");
//   putar.classList.add("active1");
// };

// const repeat_music = () => {
//   index;
//   music.src = `audio/${index}.mp3`;
//   music.play();
//   masterplay.classList.remove("bi-play");
//   masterplay.classList.add("bi-pause");

//   unduh_music.href = `audio/${index}.mp3`;

//   let songtitle = lagus.filter((els) => {
//     return els.id == index;
//   });
//   songtitle.forEach((elss) => {
//     let { songname, poster } = elss;
//     title.innerHTML = songname;
//     unduh_music.setAttribute("download", songname);
//     poster_putar_musik.src = poster;
//   });
//   backgrounds();
//   Array.from(document.getElementsByClassName("itemlagu"))[
//     index - 1
//   ].style.background = "rgb(105, 105, 105 .1)";
//   plays();
//   e1.target.classList.remove("bi-play-circle");
//   e1.target.classList.add("bi-pause-circle");
//   putar.classList.add("active1");
// };

// const random_music = () => {
//   if (index == lagus.length) {
//     index = 1;
//   } else {
//     index = Math.floor(Math.random() * lagus.length + 1);
//   }
//   music.src = `audio/${index}.mp3`;
//   music.play();
//   masterplay.classList.remove("bi-play");
//   masterplay.classList.add("bi-pause");

//   unduh_music.href = `audio/${index}.mp3`;

//   let songtitle = lagus.filter((els) => {
//     return els.id == index;
//   });
//   songtitle.forEach((elss) => {
//     let { songname, poster } = elss;
//     title.innerHTML = songname;
//     unduh_music.setAttribute("download", songname);
//     poster_putar_musik.src = poster;
//   });
//   backgrounds();
//   Array.from(document.getElementsByClassName("itemlagu"))[
//     index - 1
//   ].style.background = "rgb(105, 105, 105 .1)";
//   plays();
//   e1.target.classList.remove("bi-play-circle");
//   e1.target.classList.add("bi-pause-circle");
//   putar.classList.add("active1");
// };

// music.addEventListener("ended", () => {
//   let b = shuffle.innerHTML;

//   switch (b) {
//     case "repeat":
//       repeat_music();
//       break;
//     case "next":
//       next_music();
//       break;
//     case "random":
//       random_music();
//       break;
//   }
// });