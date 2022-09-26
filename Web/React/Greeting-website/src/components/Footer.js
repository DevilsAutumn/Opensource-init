import React from "react";

const Footer = () => {
  const time = new Date().toLocaleString();

  return <div className="footer">{time}</div>;
};

export default Footer;
