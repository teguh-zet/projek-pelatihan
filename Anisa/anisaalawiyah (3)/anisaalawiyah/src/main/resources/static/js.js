$(function () {
    $('[data-toggle="tooltip"]').tooltip()
  })

  // untuk mengatur saat si halaman di scroll maka warna si headernya berubah tapi jika kembali ke awal maka akan berubah seperti semula
window.addEventListener("scroll", function () {
  var header = document.querySelector("header");
  var scrollPosition = window.scrollY;
  // Tentukan posisi scroll (misalnya 100 piksel) untuk mengubah warna header
  if (scrollPosition > 100) {
    header.classList.add("fixed"); // untuk menambahkan warna yang di inginkan
  } else {
    header.classList.remove("fixed"); // Kembalikan ke warna awal ketika scroll kembali ke atas
}
});