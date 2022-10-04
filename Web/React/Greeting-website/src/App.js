import React from "react";

import Header from "./components/Header";
import Footer from "./components/Footer";

const App = () => {

let style={};

let hours = new Date().getHours();
  if (hours >= 0 && hours < 12) {
    style.backgroundColor = "#87CEEB";
  } else if (hours >= 12 && hours < 16) {
    style.backgroundColor = "#FFAE42";
  } else if (hours >= 16 && hours < 21) {
    style.backgroundColor = "#007FFF";
  } else {
    style.backgroundColor = "#FF2400";

  }


  return (
    <div className="container-fluid" style={style}>
      <div className="container">
        <Header />
        <Footer />
      </div>
    </div>
  );
};

export default App;
