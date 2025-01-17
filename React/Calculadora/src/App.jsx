import React, { useState } from "react";
import * as math from "mathjs";
import PanelBotones from "./components/PanelBotones";
import Pantalla from "./components/Pantalla";
import "./App.css";

const App = () => {
  const [valorPantalla, setValorPantalla] = useState("0"); // Pantalla de la calculadora
  const [primerNumero, setPrimerNumero] = useState(null); // Primer número ingresado
  const [operador, setOperador] = useState(null); // Operador seleccionado
  const [resultadoMostrado, setResultadoMostrado] = useState(false); // Indica si el último valor fue un resultado

  const manejarClickBoton = (valor) => {
    if (valor === "AC") {
      // Reseteamos todos los estados
      setValorPantalla("0");
      setPrimerNumero(null);
      setOperador(null);
      setResultadoMostrado(false);
      return;
    }

    if (["+", "-", "*", "/"].includes(valor)) {
      // Guardamos el operador y el número actual como primer número
      setOperador(valor);
      setPrimerNumero(valorPantalla);
      setValorPantalla("0");
      setResultadoMostrado(false);
      return;
    }

    if (valor === "=") {
      if (primerNumero !== null && operador !== null) {
        try {
          const expresion = `${primerNumero} ${operador} ${valorPantalla}`;
          const resultado = math.evaluate(expresion); // Usamos math.js para evaluar la expresión
          setValorPantalla(String(resultado)); // Mostramos el resultado
          setPrimerNumero(resultado); // Guardamos el resultado como primer número
          setOperador(null); // Reseteamos el operador
          setResultadoMostrado(true); // Indicamos que se mostró un resultado
        } catch (error) {
          setValorPantalla("Error"); // Mostramos "Error" si algo falla
          setResultadoMostrado(false);
        }
      }
      return;
    }

    if (valor === "+/-") {
      setValorPantalla((num) => String(num * -1));
      return;
    }

    if (valor === "<-") {
      setValorPantalla((num) => (num.length === 1 ? "0" : num.slice(0, -1)));
      return;
    }

    if (valor === "%") {
      setValorPantalla((num) => String(num / 100));
      return;
    }

    setValorPantalla(
      (num) => (resultadoMostrado ? valor : num === "0" ? valor : num + valor) //if para controlar el reinicio del numero tras mostrar un resultado
    );
    setResultadoMostrado(false); // Reiniciamos el indicador después de ingresar un número
  };

  return (
    <div className="calculadora">
      <Pantalla value={valorPantalla} />
      <PanelBotones onButtonClick={manejarClickBoton} />
    </div>
  );
};

export default App;
