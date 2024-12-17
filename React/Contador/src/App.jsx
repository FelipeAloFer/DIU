import { useState } from "react";
import "./App.css";
import ContadorComponent from "./components/ContadorComponent";

function App() {
  const [contador, setContador] = useState(0);

  const increaseContador = () => {
    setContador(contador + 1);
  };

  const decreaseContador = () => {
    setContador(contador - 1);
  };

  const resetContador = () => {
    setContador(0);
  };

  const clase = "incButton";

  return (
    <>
      <h1>Contador</h1>
      <ContadorComponent
        contador={contador}
        increaseContador={increaseContador}
        decreaseContador={decreaseContador}
        resetContador={resetContador}
        clase={clase}
      />
    </>
  );
}

export default App;
