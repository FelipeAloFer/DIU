import React, { useState } from "react";
import * as math from "mathjs";
import PanelBotones from "./components/PanelBotones";
import Pantalla from "./components/Pantalla";
import "./App.css";

const App = () => {
  const [valorPantalla, setValorPantalla] = useState("0"); // Pantalla de la calculadora
  const [primerNumero, setPrimerNumero] = useState(null); // Primer número ingresado
  const [operador, setOperador] = useState(null); // Operador seleccionado
  const [esperandoSegundoNumero, setEsperandoSegundoNumero] = useState(false); // Si estamos esperando el segundo número

  const manejarClickBoton = (valor) => {
    if (valor === "AC") {
      // Reseteamos todos los estados
      setValorPantalla("0");
      setPrimerNumero(null);
      setOperador(null);
      setEsperandoSegundoNumero(false);
      return;
    }

    if (["+", "-", "*", "/"].includes(valor)) {
      // Guardamos el operador y el número actual como primer número
      setOperador(valor);
      setPrimerNumero(valorPantalla);
      setValorPantalla("0");
      setEsperandoSegundoNumero(true); // Esperamos el segundo número
      return;
    }

    if (valor === "=") {
      if (primerNumero !== null && operador !== null) {
        try {
          // Construimos la expresión y evaluamos con math.js
          const expresion = `${primerNumero} ${operador} ${valorPantalla}`;
          const resultado = math.evaluate(expresion);
          setValorPantalla(String(resultado)); // Mostramos el resultado
          setPrimerNumero(resultado); // Guardamos el resultado como primer número
          setOperador(null); // Reseteamos el operador
          setEsperandoSegundoNumero(false); // No esperamos un segundo número
        } catch (error) {
          setValorPantalla("Error"); // Mostramos "Error" si algo falla
        }
      }
      return;
    }

    if (valor === "+/-") {
      setValorPantalla((num) =>
        num.startsWith("-") ? num.slice(1) : "-" + num
      );
      return;
    }

    if (valor === "<-") {
      setValorPantalla((num) => (num.length === 1 ? "0" : num.slice(0, -1)));
      return;
    }

    // Concatenamos números o el punto decimal
    setValorPantalla((num) => (num === "0" ? valor : num + valor));
  };

  return (
    <div className="calculadora">
      <Pantalla value={valorPantalla} />
      <PanelBotones onButtonClick={manejarClickBoton} />
    </div>
  );
};

export default App;
