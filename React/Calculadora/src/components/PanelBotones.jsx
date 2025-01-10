import React from "react";
import Boton from "./Boton";
import "./PanelBotones.css";

const PanelBotones = ({ onButtonClick }) => {
  const buttons = [
    "AC",
    "+/-",
    "%",
    "/",
    "7",
    "8",
    "9",
    "*",
    "4",
    "5",
    "6",
    "-",
    "1",
    "2",
    "3",
    "+",
    "0",
    ".",
    "<-",
    "=",
  ];

  const botonesEspeciales = ["/", "*", "-", "+", "="]; // Define los botones especiales

  return (
    <div className="panel-botones">
      {buttons.map((btn, index) => (
        <Boton
          key={index}
          value={btn}
          onClick={onButtonClick}
          className={`boton ${
            botonesEspeciales.includes(btn) ? "boton--especial" : ""
          }`}
        />
      ))}
    </div>
  );
};

export default PanelBotones;
