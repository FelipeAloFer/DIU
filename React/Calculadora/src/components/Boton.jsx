import React from "react";

const Boton = ({ value, onClick, className }) => {
  return (
    <button className={className} onClick={() => onClick(value)}>
      {value}
    </button>
  );
};

export default Boton;
