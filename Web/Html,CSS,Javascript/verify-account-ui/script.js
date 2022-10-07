const codes = document.querySelectorAll('.code')

codes[0].focus()

codes.forEach((code, idx) => {
  code.addEventListener("keydown", (e) => {
    if (e.keyCode >= 48 && e.keyCode <= 57 && codes[idx].value == "") {
      setTimeout(() => codes[idx + 1].focus(), 10);
    } else if (e.key === "Backspace") {
      codes[idx - 1].value = "";
      setTimeout(() => codes[idx - 1].focus(), 10);
    } else if (e.key === "Enter") {
      for (var i = 0; i < codes.length; i++) {
        codes[i].value = "";
      }
      codes[0].focus();
    } else {
      codes[idx].value = "";
      codes[idx].value = e.key;
      e.preventDefault();
    }
  });
});