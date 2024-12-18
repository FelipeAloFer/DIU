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

// import React, { Component } from "react";
// import "./App.css";
// import ContadorComponent from "./components/ContadorComponent";

// class App extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//       contador: 0,
//     };
//   }

//   increaseContador = () => {
//     this.setState((prevState) => ({
//       contador: prevState.contador + 1,
//     }));
//   };

//   decreaseContador = () => {
//     this.setState((prevState) => ({
//       contador: prevState.contador - 1,
//     }));
//   };

//   resetContador = () => {
//     this.setState({ contador: 0 });
//   };

//   render() {
//     const clase = "incButton";
//     return (
//       <>
//         <h1>Contador</h1>
//         <ContadorComponent
//           contador={this.state.contador}
//           increaseContador={this.increaseContador}
//           decreaseContador={this.decreaseContador}
//           resetContador={this.resetContador}
//           clase={clase}
//         />
//       </>
//     );
//   }
// }

// export default App;
