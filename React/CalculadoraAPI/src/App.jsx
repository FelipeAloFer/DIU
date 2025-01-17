import React, { useState } from "react";
import PanelBotones from "./components/PanelBotones";
import Pantalla from "./components/Pantalla";
import "./App.css";

const App = () => {
  const [valorPantalla, setValorPantalla] = useState("0"); // Pantalla de la calculadora
  const [primerNumero, setPrimerNumero] = useState(null); // Primer número ingresado
  const [operador, setOperador] = useState(null); // Operador seleccionado
  const [resultadoMostrado, setResultadoMostrado] = useState(false); // Indica si el último valor fue un resultado

  const manejarClickBoton = async (valor) => {
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
          const data = await (
            await fetch("http://api.mathjs.org/v4/", {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({ expr: expresion }),
            })
          ).json();
          setValorPantalla(String(data.result));
          setPrimerNumero(data.result);
          setOperador(null);
          setResultadoMostrado(true);
        } catch {
          setValorPantalla("Error");
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
      (num) => (resultadoMostrado ? valor : num === "0" ? valor : num + valor) // Controlamos el reinicio del número tras mostrar un resultado
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
