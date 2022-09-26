import React from "react";

const Header = () => {
  let period = " ";
  let style = {};

  let hours = new Date().getHours();
  if (hours >= 0 && hours < 12) {
    period = "Morning";
    style.color = "#39FF14";
  } else if (hours >= 12 && hours < 16) {
    period = "Afternoon";
    style.color = "#FFAE42";
  } else if (hours >= 16 && hours < 21) {
    period = "Evening";
    style.color = "#007FFF";
  } else {
    period = "Night";
    style.color = "#FF2400";
  }

  return (
    <div className="header">
      <span>{`Good ${period}, `}</span>
      <span style={style}>Codey Sandeep</span>
    </div>
  );
};

export default Header;
