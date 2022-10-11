const fill = document.querySelector(".fill");
const empties = document.querySelectorAll(".empty");

fill.addEventListener("dragstart", dragStart);
fill.addEventListener("dragend", dragEnd);

for (const empty of empties) {
  empty.addEventListener("dragover", dragOver);
  empty.addEventListener("dragenter", dragEnter);
  empty.addEventListener("dragleave", dragLeave);
  empty.addEventListener("drop", dragDrop);
}

function dragStart() {
  this.className += " hold";
  setTimeout(() => (this.className = "invisible"), 0);
}

function dragEnd() {
  this.className = "fill";
}

function dragOver(e) {
  e.preventDefault();
}

function dragEnter(e) {
  e.preventDefault();
  this.className += " hovered";
}

function dragLeave() {
  this.className = "empty";
}

function dragDrop() {
  this.className = "empty";
  this.append(fill);
}

//load image from local directory
const el = document.getElementById("uploadimage");
fill.addEventListener("click", function (e) {
  if (e.offsetX > fill.offsetWidth - 20) {
    fill.style.background = `white`;
    fill.style.setProperty("--crossVal", "None");
    fill.style.setProperty("--addVal", "content");
  } else if (
    fill.offsetWidth - 90 < e.offsetX < fill.offsetWidth - 80 &&
    fill.offsetHeight - 90 < e.offsetY < fill.offsetHeight - 80
  ) {
    if (fill.style.background === "white") {
      console.log("upload");
      $("input").click();
    }
  }
});

const loadImage = (ev) => {
  // console.log(ev);
  const img = new Image(),
    f = document.getElementById("uploadimage").files[0],
    url = window.URL || window.webkitURL,
    src = url.createObjectURL(f);
  img.src = src;

  console.log(img.src);
  //   console.log(el);
  img.onload = () => {
    fill.style.setProperty("--crossVal", "block");
    fill.style.setProperty("--addVal", "none");
    fill.style.backgroundImage = `url('${src}')`;
  };
};

document
  .getElementById("uploadimage")
  .addEventListener("change", loadImage, false);
